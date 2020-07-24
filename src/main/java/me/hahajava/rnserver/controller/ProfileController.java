package me.hahajava.rnserver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.persistence.ProfileRepository;
import me.hahajava.rnserver.persistence.UserRepository;
import me.hahajava.rnserver.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.Optional.ofNullable;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileController {

	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;

	@ExceptionHandler(NoSuchElementException.class)
	public Map<String, String> wrongParameterResponse(Exception e) {
		log.error(e.getMessage());
		return Map.of("message", "error");
	}

	@GetMapping("/profile")
	public ResponseEntity<Profile> getProfile(HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));
		return new ResponseEntity<>(profileRepository.findByUserAccountId(userId), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public Map<String, String> addProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));
		UserAccount userAccount = ofNullable(userRepository.findById(userId)).orElseThrow(NoSuchElementException::new);
		newProfile.setUserAccount(userAccount);

		try {
			profileRepository.save(Profile.newInstanceAsProfile(newProfile));
		} catch (Exception e) {
			log.error(e.getMessage());
			return Map.of("message", "profile 이 존재합니다.");
		}

		return Map.of("message", "success");
	}

	@PutMapping("/profile")
	public Map<String, String> changeProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));

		Profile profile = ofNullable(profileRepository.findByUserAccountId(newProfile.getUserAccount().getId()))
			.orElseThrow(() -> new NoSuchElementException("User 정보를 확인 할 수 없습니다."));

		ofNullable(newProfile.getPhoneNo()).ifPresent(profile::setPhoneNo);
		ofNullable(newProfile.getUserName()).ifPresent(profile::setUserName);

		profileRepository.save(profile);
		return Collections.singletonMap("message", "succes");
	}

}
