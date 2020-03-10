package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findById(String id);
}