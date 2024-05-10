package com.ltbanking.cardservice.controller;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;
import com.ltbanking.cardservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/card")
public class CardController {
  private CardService cardService;

  @PostMapping("/register")
  public ResponseEntity<CardDto> register(@RequestBody AccountPayload accountPayload) {
    try {
      CardDto accountDto = cardService.register(accountPayload);
      return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
