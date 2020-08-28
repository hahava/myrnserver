package me.hahajava.rnserver.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@ToString
public class RegisterRequestDTO {
    @NotNull(message = "id must not be null")
    private String id;

    @NotNull(message = "pw must not be null")
    private String pw;

    @NotNull
    @Size(max = 100, message = "selft introduce must be under 100")
    private String selfIntroduce;
}
