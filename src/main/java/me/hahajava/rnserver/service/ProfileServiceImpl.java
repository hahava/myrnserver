package me.hahajava.rnserver.service;

import lombok.RequiredArgsConstructor;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.AuthRepository;
import me.hahajava.rnserver.persistence.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final AuthRepository authRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void addUserProfile(Profile newProfile, String userId) {
        Optional<UserAccount> userAccount = Optional.of(authRepository.findById(userId));
        newProfile.setUserAccount(userAccount.get());
        Profile profile = Profile.newInstanceAsProfile(newProfile);

        if (notExistsProfile(profile)) {
            profileRepository.save(profile);
        }

    }

    /**
     * Optional 객체의 사용으로 {@link NullPointerException} 발생 할 수 있음
     *
     * @param userId
     * @throws NullPointerException
     */
    @Override
    public Profile getUserProfile(String userId) throws NullPointerException {
        return Optional.of(profileRepository.findByUserAccountId(userId)).get();
    }

    /**
     * Optional 객체의 사용으로 {@link NullPointerException} 발생 할 수 있음
     *
     * @throws NullPointerException
     */
    @Override
    public void updateUserProfile(Profile newProfile, String userId) throws NullPointerException {
        Optional<Profile> oldProfile = Optional.of(getUserProfile(userId));
        Profile.updateInstanceAsProfile(newProfile, oldProfile.get());
        profileRepository.save(oldProfile.get());
    }

    private boolean notExistsProfile(Profile profile) {
        return profileRepository.findByUserAccountId(profile.getUserAccount().getId()) == null;
    }
}
