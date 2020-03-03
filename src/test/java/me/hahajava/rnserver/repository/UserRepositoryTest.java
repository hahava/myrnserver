package me.hahajava.rnserver.repository;

import me.hahajava.rnserver.model.User;
import me.hahajava.rnserver.persistence.UserProfileRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Ignore
	@Test
	public void addUserProfile() {
		User user = new User();
		user.setUserId("111");
		user.setUserPw("1234");
		user.setPhoneNo("01012345678");
		user.setToken("35345dgefgsdv4wy245tdsfg");

		userProfileRepository.save(user);
	}
}
