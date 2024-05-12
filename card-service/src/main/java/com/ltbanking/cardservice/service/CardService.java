package com.ltbanking.cardservice.service;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;
import com.ltbanking.cardservice.domain.CommonResponse;

import java.util.UUID;

public interface CardService {
  CardDto register(AccountPayload accountPayload);

  CommonResponse changeSecurityCode(CardDto cardDto);

  CardDto getCardByCardNumber(String cardNumber);

  CardDto getCardByAccountID(UUID idAccount);
}
