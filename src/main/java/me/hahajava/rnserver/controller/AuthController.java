package me.hahajava.rnserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hahajava.rnserver.model.request.LoginRequestDTO;
import me.hahajava.rnserver.model.request.RegisterRequestDTO;
import me.hahajava.rnserver.service.AuthServiceImpl;
import me.hahajava.rnserver.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginRequestDTO loginRequestDTO, BindingResult br) {

        if (br.hasErrors()) {
            final String errMsg = br.getAllErrors().get(0).getDefaultMessage();
            log.error(errMsg + "\t" + loginRequestDTO.toString());
            return ResponseEntity.status(BAD_REQUEST).body(errMsg);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequestDTO.getId(), loginRequestDTO.getPw());
        Authentication authentication = authenticationManager.authenticate(auth);
        if (authentication.isAuthenticated() == false) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }

        final String token = JwtUtil.encodeStringToJwt(loginRequestDTO.getId());
        return ResponseEntity.status(ACCEPTED).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUserAccount(@RequestBody @Valid RegisterRequestDTO registerRequestDTO, BindingResult br) {

        if (br.hasErrors()) {
            final String errMsg = br.getAllErrors().get(0).getDefaultMessage();
            log.error(errMsg + "\t" + registerRequestDTO.toString());
            return ResponseEntity.status(BAD_REQUEST).body(errMsg);
        }

        authService.addUserAccount(registerRequestDTO);
        return ResponseEntity.status(OK).build();
    }

}
