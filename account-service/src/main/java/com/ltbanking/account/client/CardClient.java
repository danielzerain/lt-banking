package com.ltbanking.account.client;

import com.ltbanking.account.domain.AccountPayload;
import com.ltbanking.account.domain.CardDto;
import com.ltbanking.account.domain.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "card-service")
public interface CardClient {
  @PostMapping(value = "/register")
  CardDto register(AccountPayload userDataPayload);

  @GetMapping(value = "/info/{id}")
  CardDto getCardInfoByCardNumber(@PathVariable("id") String cardNumber);

  @GetMapping(value = "/card-info/{id}")
  CardDto getCardInfoIdAccount(@PathVariable("id") UUID idAccount);

  @PutMapping(value = "/change-pin")
  CommonResponse changeSecurityCode(CardDto cardDto);
}
