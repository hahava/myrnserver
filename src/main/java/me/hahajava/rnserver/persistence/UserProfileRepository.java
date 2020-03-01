package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
}
