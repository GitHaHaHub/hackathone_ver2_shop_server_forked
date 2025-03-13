package com.kimh.spm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc // 컨트롤러 뿐만아니라 @Repository, @Service까지 메모리에 다 올린다.
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void SignUpTest() throws Exception {
        String RequestBody = "{\"id\":\"test\",\"password\":\"1234\",\"email\":\"test@a.b\"}";
        mockMvc.perform(post("/SignUp").contentType(MediaType.APPLICATION_JSON).content(RequestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

}
