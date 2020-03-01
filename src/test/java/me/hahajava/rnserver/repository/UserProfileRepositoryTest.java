package me.hahajava.rnserver.repository;

import me.hahajava.rnserver.model.UserProfile;
import me.hahajava.rnserver.persistence.UserProfileRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserProfileRepositoryTest {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Ignore
	@Test
	public void addUserProfile() {
		UserProfile userProfile = new UserProfile();
		userProfile.setUserId("111");
		userProfile.setUserPw("1234");
		userProfile.setPhoneNo("01012345678");
		userProfile.setToken("35345dgefgsdv4wy245tdsfg");

		userProfileRepository.save(userProfile);
	}
}
