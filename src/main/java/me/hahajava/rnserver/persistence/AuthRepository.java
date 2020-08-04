package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<UserAccount, Long> {
	UserAccount findById(String id);
}
