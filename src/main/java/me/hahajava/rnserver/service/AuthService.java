package me.hahajava.rnserver.service;

import me.hahajava.rnserver.model.request.RegisterRequestDTO;

public interface AuthService {

    void addUserAccount(RegisterRequestDTO registerRequestDTO);

}
