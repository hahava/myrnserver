package me.hahajava.rnserver.repository;

import me.hahajava.rnserver.model.AuthLevel;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    private UserAccount mockAccount;

    @Before
    public void setUp() {
        mockAccount = new UserAccount();
        mockAccount.setPw("{noop}12345");
        mockAccount.setId("havana");
        mockAccount.setAuthLevel(AuthLevel.ADMIN);
    }

    @Test
    public void addProfileTest() {
        Profile profile = Profile.builder()
                .phoneNo("010-1234-5678")
                .userName("havana")
                .selfIntroduce("my name is havana")
                .build();
        profile.setUserAccount(mockAccount);

        profileRepository.save(profile);
    }

}
