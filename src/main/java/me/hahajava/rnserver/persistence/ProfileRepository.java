package me.hahajava.rnserver.persistence;

import me.hahajava.rnserver.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
	Profile findByUserId(String userId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,
		value = "INSERT INTO PROFILE (USER_NO, PHONE_NO, USER_NAME ) VALUES(:userNo, :phoneNo, :userName)")
	void addProfileByUserNo(
		@Param("userNo") Long userNo,
		@Param("phoneNo") String phoneNo,
		@Param("userName") String userName
	);
}
