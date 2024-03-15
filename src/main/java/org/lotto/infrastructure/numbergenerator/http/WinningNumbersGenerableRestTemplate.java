package org.lotto.infrastructure.numbergenerator.http;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lotto.domain.numbergenerator.WinningNumbersGenerable;
import org.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Log4j2
public class WinningNumbersGenerableRestTemplate implements WinningNumbersGenerable {


    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public SixRandomNumbersDto generateSixWinningNumbers(final int lowerBand, final int upperBand, final int count) {
        String urlForService = getUrlForService("/api/v1.0/random");

        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//        http://www.randomnumberapi.com/api/v1.0/random?min=1&max=99&count=6
        final String url = UriComponentsBuilder.fromHttpUrl(urlForService)
                .queryParam("min", lowerBand)
                .queryParam("max", upperBand)
                .queryParam("count", count)
                .toUriString();

        try {
            final ResponseEntity<List<Integer>> exchange = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<>() {
                    }
            );
            log.info(exchange.getBody());
            return new SixRandomNumbersDto(new HashSet<>(Objects.requireNonNull(exchange.getBody())));
        } catch (ResourceAccessException e) {
            log.error("Error While fetching data" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
