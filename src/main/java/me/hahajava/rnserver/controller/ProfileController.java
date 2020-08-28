package me.hahajava.rnserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.service.ProfileService;
import me.hahajava.rnserver.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;

	@GetMapping("/profile")
	public ResponseEntity<Profile> getProfile(HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));
		Profile profile = profileService.getUserProfile(userId);
		return new ResponseEntity<>(profile, OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<String> addProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));
		profileService.addUserProfile(newProfile, userId);
		return ResponseEntity.status(OK).build();
	}

	@PutMapping("/profile")
	public ResponseEntity<String> changeProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
		String userId = JwtUtil.decodeJwtToString(request.getHeader(JwtUtil.HEADER_KEY));
		profileService.updateUserProfile(newProfile, userId);
		return ResponseEntity.status(OK).build();
	}
}
