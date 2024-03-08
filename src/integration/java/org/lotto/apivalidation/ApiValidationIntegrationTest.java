package org.lotto.apivalidation;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.lotto.BaseIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Log4j2
class ApiValidationIntegrationTest extends BaseIntegrationTest {
    @Test
    void should_throw_bad_request_to_user_when_numbers_are_null() throws Exception {
        final ResultActions perform = mockMvc.perform(post("/inputNumbers").contentType(MediaType.APPLICATION_JSON).content("""
                {                   
                }
                """));
        perform.andExpect(status().isBadRequest());
        perform.andExpect(content().json("""
                                {
                                    "errors": [
                                            "Numbers cannot be empty",
                                            "Numbers cannot be null"
                                        ],
                                    "status" : "BAD_REQUEST"
                                }
                """.trim()
        ));
    }

    @Test
    void should_throw_bad_request_to_user_when_numbers_are_empty() throws Exception {
        final ResultActions perform = mockMvc.perform(post("/inputNumbers").contentType(MediaType.APPLICATION_JSON).content("""
                {      
                "numbers":[                 
                    ]             
                }
                """));
        perform.andExpect(status().isBadRequest());
        perform.andExpect(content().json("""
                                {
                                    "errors": [
                                            "Numbers cannot be empty"
                                        ],
                                    "status" : "BAD_REQUEST"
                                }
                """.trim()
        ));
    }
}
