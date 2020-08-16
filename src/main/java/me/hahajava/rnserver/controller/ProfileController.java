package me.hahajava.rnserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.service.ProfileService;
import me.hahajava.rnserver.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;

	@GetMapping("/profile")
	public ResponseEntity<Profile> getProfile(HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));

		try {
			Profile profile = profileService.getUserProfile(userId);
			return new ResponseEntity<>(profile, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/profile")
	public Map<String, String> addProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));

		try {
			profileService.addUserProfile(newProfile, userId);
			return Map.of("message", "success");
		} catch (Exception e) {
			log.error(e.getMessage());
			return Map.of("message", "profile 이 존재합니다.");
		}
	}

	@PutMapping("/profile")
	public Map<String, String> changeProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));

		try {
			profileService.updateUserProfile(newProfile, userId);
			return Collections.singletonMap("message", "succes");
		} catch (Exception e) {
			log.error(e.getMessage());
			return Collections.singletonMap("message", "not changed");
		}
	}
}
