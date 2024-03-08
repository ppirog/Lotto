package org.lotto.infrastructure.numberreceiver.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.lotto.infrastructure.numberreceiver.controller.dto.InputNumberRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@AllArgsConstructor
@Log4j2
public class InputNumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;
    @PostMapping("/inputNumbers")
    public ResponseEntity<InputNumberResultDto> inputNumbers(@RequestBody @Valid InputNumberRequestDto inputNumberRequestDto){
        log.info("Received numbers: {}", inputNumberRequestDto.numbers());
        return ResponseEntity.ok(numberReceiverFacade.inputNumbers(new HashSet<>(inputNumberRequestDto.numbers())));
    }
}
