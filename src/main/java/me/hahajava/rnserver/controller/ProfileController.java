package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.ProfileRepository;
import me.hahajava.rnserver.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	@ExceptionHandler(NoSuchElementException.class)
	public Map<String, String> wrongParameterResponse(Exception e) {
		return Collections.singletonMap("message", e.getMessage());
	}

	@GetMapping("/profile/{userId}")
	public ResponseEntity<Profile> getProfile(@PathVariable String userId) {
		return new ResponseEntity<>(profileRepository.findByUserAccountId(userId), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public Map<String, String> addProfile(@RequestBody Profile newProfile) {
		UserAccount userAccount = Optional
			.ofNullable(userRepository.findById(newProfile.getUserAccount().getId()))
			.orElseThrow(() -> new NoSuchElementException("user 정보를 확인할 수 없습니다."));

		Profile profile = profileRepository.findByUserAccountId(userAccount.getId());
		if (profile == null) {
			profileRepository.addProfileByUserNo(userAccount.getNo(), newProfile.getPhoneNo(), newProfile.getPhoneNo());
			return Collections.singletonMap("message", "success");
		}

		return Collections.singletonMap("message", "profile 이 존재합니다.");
	}

	@PutMapping("/profile")
	public Map<String, String> changeProfile(@RequestBody Profile newProfile) {
		Profile profile = Optional
			.ofNullable(profileRepository.findByUserAccountId(newProfile.getUserAccount().getId()))
			.orElseThrow(() -> new NoSuchElementException("User 정보를 확인 할 수 없습니다."));

		Optional.ofNullable(newProfile.getPhoneNo()).ifPresent(profile::setPhoneNo);
		Optional.ofNullable(newProfile.getUserName()).ifPresent(profile::setUserName);

		profileRepository.save(profile);
		return Collections.singletonMap("message", "succes");
	}

}
