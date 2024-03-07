package org.lotto.infrastructure.numberannouncer.controller;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberannouncer.NumberAnnouncerFacade;
import org.lotto.domain.numberannouncer.dto.ResultDto;
import org.lotto.infrastructure.numberannouncer.controller.dto.ResultAnnouncerResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/results")
class ResultAnnouncerRestController {

    private final NumberAnnouncerFacade numberAnnouncerFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ResultAnnouncerResponseDto> announceResult(@PathVariable String id) {
        final ResultDto resultDto = numberAnnouncerFacade.announceResult(id);
        return ResponseEntity.ok(ResultAnnouncerResponseDto.builder()
                .ticketId(resultDto.ticketId())
                .isWinner(resultDto.isWinner())
                .howManyNumbersWin(resultDto.howManyNumbersWin())
                .winNumbers(resultDto.winNumbers())
                .build());
    }


}
