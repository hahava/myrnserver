package me.hahajava.rnserver.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordCryptoTest {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void passwordEncodingTest() {
		String encodedPw = passwordEncoder.encode("1111");
		System.out.println(passwordEncoder.matches("1111", encodedPw));
	}

}
