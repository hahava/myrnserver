package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.User;
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

	@GetMapping("/profile/{userId}")
	public ResponseEntity<Profile> getProfile(@PathVariable String userId) {
		return new ResponseEntity<>(profileRepository.findByUserId(userId), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public Map<String, String> addProfile(@RequestBody Profile profile) {
		User user = Optional
			.ofNullable(userRepository.findById(profile.getUser().getId()))
			.orElseThrow(() -> new NoSuchElementException("user 정보를 확인할 수 없습니다."));

		profileRepository.addProfileByUserNo(user.getUserNo(), profile.getPhoneNo(), profile.getPhoneNo());
		return Collections.singletonMap("message", "success");
	}

	@ExceptionHandler(NoSuchElementException.class)
	public String response(Exception e) {
		return e.getMessage();
	}

}
