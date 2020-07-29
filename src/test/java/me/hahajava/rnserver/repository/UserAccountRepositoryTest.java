package me.hahajava.rnserver.repository;

import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.AuthRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAccountRepositoryTest {

	@Autowired
	private AuthRepository authRepository;

	@Ignore
	@Test
	public void addUserProfile() {
		UserAccount userAccount = new UserAccount();
		userAccount.setPw("1234");

		authRepository.save(userAccount);
	}
}
