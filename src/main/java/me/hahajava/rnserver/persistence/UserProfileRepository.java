package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<User, Long> {
}
