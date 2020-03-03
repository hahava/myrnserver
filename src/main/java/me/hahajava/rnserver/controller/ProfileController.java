package me.hahajava.rnserver.controller;

import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.persistence.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;

	@GetMapping("/profile/{id}")
	public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
		return new ResponseEntity<>(profileRepository.findById(id).get(), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<String> addProfile(@RequestBody @Valid Profile profile, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(br.getAllErrors().get(0).toString(), HttpStatus.BAD_REQUEST);
		}
		profileRepository.save(profile);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
