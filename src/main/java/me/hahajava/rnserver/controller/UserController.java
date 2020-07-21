package me.hahajava.rnserver.controller;

import lombok.AllArgsConstructor;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

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

		UserAccount account = UserAccount.newInstanceForRegister(userAccount);
		try{
			userRepository.save(account);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("success", HttpStatus.OK);
	}

}
