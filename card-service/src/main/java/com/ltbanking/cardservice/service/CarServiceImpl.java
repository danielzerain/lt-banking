package com.ltbanking.cardservice.service;

import com.ltbanking.cardservice.domain.AccountPayload;
import com.ltbanking.cardservice.domain.CardDto;
import com.ltbanking.cardservice.domain.CommonResponse;
import com.ltbanking.cardservice.model.CardEntity;
import com.ltbanking.cardservice.repository.CardRepository;
import com.ltbanking.cardservice.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CardService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

  private CardRepository cardRepository;

  @Override
  public CardDto register(AccountPayload accountPayload) {
    LOGGER.info(String.format("cardApi,payload=%s", accountPayload));
    CardEntity cardEntity = new CardEntity();
    cardEntity.setAccountId(accountPayload.idAccount());
    cardEntity.setCardNumber(Utils.generateCode(16));
    cardEntity.setCardSecurityCode(Integer.valueOf(Utils.generateCode(4)));
    cardRepository.save(cardEntity);
    return new CardDto(cardEntity.getCardNumber(), cardEntity.getCardSecurityCode());
  }

  @Override
  public CommonResponse changeSecurityCode(CardDto cardDto) {
    Optional<CardEntity> cardEntity = cardRepository.findByCardNumber(cardDto.cardNumber());
    if (!cardEntity.isPresent()) {
      return new CommonResponse(99, "The change was not made");
    } else {
      cardEntity.get().setCardSecurityCode(cardDto.cardSecurityCode());
      cardRepository.save(cardEntity.get());
      return new CommonResponse(0, "Code changed correctly");
    }
  }

  @Override
  public CardDto getCardByCardNumber(String cardNumber) {
    Optional<CardEntity> cardEntity = cardRepository.findByCardNumber(cardNumber);
      return cardEntity.map(entity -> new CardDto(entity.getCardNumber(), entity.getCardSecurityCode())).orElse(null);
  }

  @Override
  public CardDto getCardByAccountID(UUID idAccount) {
    LOGGER.info(String.format("getCardByAccountID,payload=%s", idAccount));
    Optional<CardEntity> cardEntity = cardRepository.findByAccountId(UUID.fromString(idAccount.toString()));
    return cardEntity
        .map(entity -> new CardDto(entity.getCardNumber(), entity.getCardSecurityCode()))
        .orElse(null);
  }
}
