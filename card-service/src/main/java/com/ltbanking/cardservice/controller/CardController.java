package com.ltbanking.cardservice.controller;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;
import com.ltbanking.cardservice.domain.CommonResponse;
import com.ltbanking.cardservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
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

  @GetMapping("/info/{id}")
  public ResponseEntity<CardDto> cardInfoByCardNumber(@PathVariable("id") String cardNumber) {
    try {
      CardDto accountDto = cardService.getCardByCardNumber(cardNumber);
      return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @GetMapping("/card-info/{id}")
  public ResponseEntity<CardDto> cardInfoByIdAccount(@PathVariable("id") UUID idAccount) {
    try {
      CardDto accountDto = cardService.getCardByAccountID(idAccount);
      return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @PutMapping("/change-pin")
  public ResponseEntity<CommonResponse> changeCardSecurityCode(@RequestBody CardDto cardDto) {
    try {
      CommonResponse response = cardService.changeSecurityCode(cardDto);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
