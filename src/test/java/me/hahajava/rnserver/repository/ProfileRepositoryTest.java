package me.hahajava.rnserver.repository;

import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.ProfileRepository;
import me.hahajava.rnserver.persistence.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addProfileTest() {
        UserAccount account = userRepository.findById("hahava");

        Profile profile = new Profile();
        profile.setNo(null);
        profile.setPhoneNo("010-1234-5678");
        profile.setUserName("choi");
        profile.setSelfIntroduce("hello I m choi!");
        profile.setUserAccount(account);

        profileRepository.save(profile);
    }

}
