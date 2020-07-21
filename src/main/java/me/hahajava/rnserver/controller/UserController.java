package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccount> getUserProfile(@PathVariable String userId) {
		return new ResponseEntity<>(userRepository.findById(userId), HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<String> addUserProfile(@RequestBody @Valid UserAccount userAccount, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().get(0).toString(), HttpStatus.BAD_REQUEST);
		}

		String cryptPassword = passwordEncoder.encode(userAccount.getPw());
		userAccount.setPw(cryptPassword);

		userRepository.save(userAccount);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

}
