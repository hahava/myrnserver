package me.hahajava.rnserver.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hahajava.rnserver.model.request.LoginRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void noLoginDataException() throws Exception {
        // given
        String id = "11111";
        String pw = "11111";
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(id, pw);

        // when
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)))
        // then
                .andExpect(status()
                        .isForbidden())
                .andExpect(content()
                        .string(ExceptionMessage.NO_LOGIN_DATA.message));
    }

}
