package me.hahajava.rnserver.service;

import me.hahajava.rnserver.model.Profile;

public interface ProfileService {
    void addUserProfile(Profile profile, String userId);

    Profile getUserProfile(String userId);

    void updateUserProfile(Profile profile, String userId);
}
