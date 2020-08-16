package me.hahajava.rnserver.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class LoginRequestDTO {
    @NotNull(message = "id must not be null")
    private String id;

    @NotNull(message = "password must not be null")
    private String pw;
}
