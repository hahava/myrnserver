package me.hahajava.rnserver.service;

import lombok.RequiredArgsConstructor;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.ProfileRepository;
import me.hahajava.rnserver.persistence.AuthRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final AuthRepository authRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public void addUserProfile(Profile newProfile, String userId) {
        Optional<UserAccount> userAccount = Optional.of(authRepository.findById(userId));
        newProfile.setUserAccount(userAccount.get());
        Profile profile = Profile.newInstanceAsProfile(newProfile);
        profileRepository.save(profile);
    }

    @Override
    public Profile getUserProfile(String userId) throws NullPointerException {
        return Optional.of(profileRepository.findByUserAccountId(userId)).get();
    }

    @Transactional
    @Override
    public void updateUserProfile(Profile newProfile, String userId) {
        Optional<Profile> oldProfile = Optional.of(getUserProfile(userId));
        Profile.updateInstanceAsProfile(newProfile, oldProfile.get());
        profileRepository.save(oldProfile.get());
    }
}
