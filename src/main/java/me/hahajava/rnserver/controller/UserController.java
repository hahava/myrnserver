package me.hahajava.rnserver.controller;

import lombok.AllArgsConstructor;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.AuthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

	private final AuthRepository authRepository;

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccount> getUserProfile(@PathVariable String userId) {
		return new ResponseEntity<>(authRepository.findById(userId), HttpStatus.OK);
	}


}
