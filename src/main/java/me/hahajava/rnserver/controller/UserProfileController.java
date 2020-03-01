package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.UserProfile;
import me.hahajava.rnserver.persistence.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@GetMapping("/user/profile/{id}")
	public ResponseEntity<UserProfile> getUserProfile(Long id) {
		return new ResponseEntity<>(userProfileRepository.findById(id).get(), HttpStatus.OK);
	}

}
