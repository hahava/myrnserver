package me.hahajava.rnserver.service;

import lombok.RequiredArgsConstructor;
import me.hahajava.rnserver.model.UserAccount;
import me.hahajava.rnserver.model.request.RegisterRequestDTO;
import me.hahajava.rnserver.persistence.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    @Override
    public void addUserAccount(RegisterRequestDTO userAccount) {
        UserAccount account = UserAccount.newInstanceForRegister(userAccount);
        final String cryptPassword = passwordEncoder.encode(userAccount.getPw());
        account.setPw(cryptPassword);
        authRepository.save(account);
    }

}
