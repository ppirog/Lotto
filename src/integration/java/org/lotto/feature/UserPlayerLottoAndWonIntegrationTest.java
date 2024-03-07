package org.lotto.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.lotto.BaseIntegrationTest;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
public class UserPlayerLottoAndWonIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private NumberGeneratorFacade numberGeneratorFacade;


    @Test
    public void should_user_win_and_system_should_generate_winners() throws Exception {
        /*
         * step 1: external service returns 6 random numbers (1,2,3,4,5,6)
         * step 2: system fetched winning numbers for draw date: 19.11.2022 12:00
         * step 3: user made POST /inputNumbers with 6 numbers (1, 2, 3, 4, 5, 6) at 16-11-2022 10:00 and system returned OK(200) with message: “success” and Ticket (DrawDate:19.11.2022 12:00 (Saturday), TicketId: sampleTicketId)
         * step 4 user made GET /results/notExistingId and system returned 404 (NOT_FOUND) with body: “No ticket found for id: ticketId”
         * step 5: 3 days, 2hrs and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 12:01)
         * step 6: system generated result for TicketId: sampleTicketId with draw date 19.11.2022 12:00, and saved it with 6 hits
         * step 7: 3 hours passed, and it is 1 minute after announcement time (19.11.2022 15:01)
         * step 8: user made GET /results/sampleTicketId and system returned 200 (OK)
         * */

        //step 1 external service returns 6 random numbers (1,2,3,4,5,6)
        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25").willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value()).withHeader("Content-Type", "application/json").withBody("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]")));

        //step 2 system fetched winning numbers for draw date: 19.11.2022 12:00

        LocalDateTime drawDate = LocalDateTime.of(2022, 11, 19, 12, 0);

        await().pollInterval(Duration.ofSeconds(1)).atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                numberGeneratorFacade.retrieveWinningNumbers(drawDate);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        //step 3
        final ResultActions perform = mockMvc.perform(post("/inputNumbers").contentType(MediaType.APPLICATION_JSON).content("""
                {
                    "numbers":[
                        1,
                        2,
                        3,
                        4,
                        5,
                        6
                    ]
                }
                """));

        final MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();

        final String contentAsString = mvcResult.getResponse().getContentAsString();

        final InputNumberResultDto inputNumberResultDto = objectMapper.readValue(contentAsString, InputNumberResultDto.class);
        log.info("inputNumberResultDto: {}", inputNumberResultDto);
        assertAll(() -> assertEquals("success", inputNumberResultDto.message()), () -> assertEquals(drawDate, inputNumberResultDto.ticketDto().drawDate()), () -> assertEquals(Set.of(1, 2, 3, 4, 5, 6), inputNumberResultDto.ticketDto().userNumbers()));

        //step 4 user made GET /results/notExistingId and system returned 404 (NOT_FOUND) with body: “No ticket found for id: ticketId”
        final ResultActions perform1 = mockMvc.perform(get("/results/ticketId"));

        perform1.andExpect(content().json("""
                                {
                                    "message" : "No ticket found for id: ticketId",
                                    "status" : "NOT_FOUND"
                                }
                """.trim()
        ));

        //step 5 3 days, 2hrs and 1 minute passed, and it is 1 minute after the draw date (19.11.2022 12:01)

    }

}
