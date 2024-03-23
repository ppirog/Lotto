package org.lotto.apivalidation;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.lotto.BaseIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Log4j2
@WithMockUser
class ApiValidationIntegrationTest extends BaseIntegrationTest {

    @Container
    public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("lotto.number-generator.http.client.config.uri", () -> WIRE_MOCK_HOST);
        registry.add("lotto.number-generator.http.client.config.port", wireMockServer::getPort);
    }
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
