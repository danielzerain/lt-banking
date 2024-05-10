package com.ltbanking.cardservice.service;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;
import com.ltbanking.cardservice.model.CardEntity;
import com.ltbanking.cardservice.repository.CarRepository;
import com.ltbanking.cardservice.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CardService {
  private CarRepository carRepository;

  @Override
  public CardDto register(AccountPayload accountPayload) {
    CardEntity cardEntity = new CardEntity();
    cardEntity.setAccountId(accountPayload.idAccount());
    cardEntity.setCardNumber(Utils.generateCode(16));
    cardEntity.setCardSecurityCode(Utils.generateCode(4));
    carRepository.save(cardEntity);
      return new CardDto(cardEntity.getCardNumber(), cardEntity.getCardSecurityCode());
  }
}
