package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
