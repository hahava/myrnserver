package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.User;
import me.hahajava.rnserver.persistence.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserProfileController {
	@Autowired
	private UserProfileRepository userProfileRepository;

	@GetMapping("/user/profile/{id}")
	public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
		return new ResponseEntity<>(userProfileRepository.findById(id).get(), HttpStatus.OK);
	}

	@PostMapping("/user/profile")
	public ResponseEntity<String> addUserProfile(@RequestBody @Valid User user, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().get(0).toString(), HttpStatus.BAD_REQUEST);
		}
		userProfileRepository.save(user);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

}
