package spitter.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spitter.mvc.exceptions.UserNotFoundException;
import spitter.mvc.model.User;
import spitter.mvc.repository.UserRepository;

import javax.validation.Valid;

/**
 * Created by on 09.06.2017.
 *
 * @author krsola
 */
@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        User savedUser = userRepository.save(user);
        return "redirect:/userProfile/" + savedUser.getId();
    }

    @GetMapping(value = "/userProfile/{id}" )
    public String userProfile(@PathVariable Long id, Model model) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        model.addAttribute(user);
        return "userProfile";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "dashboard";
    }

}
