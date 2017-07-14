package spitter.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import spitter.mvc.model.User;

/**
 * Created by krystiansola on 13.07.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
