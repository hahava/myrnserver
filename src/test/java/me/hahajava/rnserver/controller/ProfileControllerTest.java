package me.hahajava.rnserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.hahajava.rnserver.BaseRestDocs;
import me.hahajava.rnserver.model.Profile;
import me.hahajava.rnserver.util.JwtUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileControllerTest extends BaseRestDocs {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getProfile() throws Exception {
        // when
        ResultActions result = super.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/profile")
                        .characterEncoding(StandardCharsets.UTF_8.toString())
                        .header(JwtUtil.HEADER_KEY, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYWhhdmEiLCJleHAiOjE1OTkwNTg4MDB9.IVIH0Uu3livtsdObgLfFnIdl7KMlM0LcuZjjacdaRRkzXWldJPSTT26ztRMiNJOnkZmJKkcrbAgC14GHjChbIA")
                        .contentType(MediaType.APPLICATION_JSON));

        // then (rest-docs)
        result
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("profile",
                        requestHeaders(
                                headerWithName(JwtUtil.HEADER_KEY).description("로그인 성공 후 반환하는 토큰을 헤더에 설정합니다.")
                        ),
                        responseFields(
                                fieldWithPath("no").type(JsonFieldType.NUMBER).description("단순히 데이터의 순서를 의미합니다."),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("사용자의 이름"),
                                fieldWithPath("phoneNo").type(JsonFieldType.STRING).description("사용자의 전화번호"),
                                fieldWithPath("selfIntroduce").type(JsonFieldType.STRING).description("자기소개")
                        )
                ));
    }

    @Test
    public void addProfile() throws Exception {
        // given
        Profile profile = Profile.builder()
                .userName("hahava")
                .phoneNo("010-1234-5678")
                .selfIntroduce("Hi. I' m a hahava ")
                .build();

        // when
        ResultActions result = super.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/profile")
                        .characterEncoding(StandardCharsets.UTF_8.toString())
                        .header(JwtUtil.HEADER_KEY, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYWhhdmEiLCJleHAiOjE1OTkwNTg4MDB9.IVIH0Uu3livtsdObgLfFnIdl7KMlM0LcuZjjacdaRRkzXWldJPSTT26ztRMiNJOnkZmJKkcrbAgC14GHjChbIA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile))
        );

        // then (rest-docs)
        result.
                andDo(print())
                .andExpect(status().isOk())
                .andDo(document("profile/save",
                        requestHeaders(
                                headerWithName(JwtUtil.HEADER_KEY).description("로그인 성공 후 반환하는 토큰을 헤더에 설정합니다.")
                        )
                ));
    }
}
