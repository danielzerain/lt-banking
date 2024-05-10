package com.ltbanking.cardservice.service;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;

public interface CardService {
  CardDto register(AccountPayload accountPayload);
}
