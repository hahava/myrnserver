package me.hahajava.rnserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hahajava.rnserver.BaseRestDocs;
import me.hahajava.rnserver.model.request.LoginRequestDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest extends BaseRestDocs {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void loginTestWhenSuccess() throws Exception {
        // given
        String id = "hahava";
        String pw = "12345";
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(id, pw);

        // when
        ResultActions result = super.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .characterEncoding(StandardCharsets.UTF_8.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDTO)));

        // then (rest-docs)
        result
                .andDo(print())
                .andExpect(status().isAccepted())
                .andDo(document("auth",
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.STRING).description("사용자 계정"),
                                fieldWithPath("pw").type(JsonFieldType.STRING).description("사용자 계정암호")
                        ),
                        responseFields(
                                fieldWithPath("token").type(JsonFieldType.STRING).description("로그인 성공시 반환하는 `토큰` 입니다. 해당 값을 헤더에 삽입하여 요청합니다.")
                        )
                ));
    }
}
