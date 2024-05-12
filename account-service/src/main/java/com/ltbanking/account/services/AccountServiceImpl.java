package com.ltbanking.account.services;

import com.ltbanking.account.client.CardClient;
import com.ltbanking.account.domain.*;
import com.ltbanking.account.model.AccountBankingEntity;
import com.ltbanking.account.repository.AccountBankingRepository;
import com.ltbanking.account.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

  private AccountBankingRepository accountBankingRepository;
  private CardClient cardClient;

  @Override
  public AccountDto createAccount(UserDataPayload userDataPayload) {
    LOGGER.info(String.format("accountApi,payload=%s", userDataPayload));
    LocalDateTime actualDate = LocalDate.now().atStartOfDay();
    AccountDto accountDto = new AccountDto();
    AccountBankingEntity accountBankingEntity = new AccountBankingEntity();
    String accountNumber = Utils.generateCode(10);
    accountBankingEntity.setAccountNumber(accountNumber);
    accountBankingEntity.setIdUserBanking(userDataPayload.idUser());
    accountBankingEntity.setBalance(100.0);
    accountBankingEntity.setCreationDate(actualDate);
    accountBankingRepository.save(accountBankingEntity);
    accountDto.setAccountNumber(accountNumber);
    AccountPayload accountPayload = new AccountPayload(accountBankingEntity.getId());
    CardDto cardDto = cardClient.register(accountPayload);
    accountDto.setCardNumber(cardDto.cardNumber());
    accountDto.setCardSecurityCode(cardDto.cardSecurityCode());
    return accountDto;
  }

  @Override
  public CommonResponse changeCardSecurityCode(CardSecurityCodeChange cardSecurityCodeChange) {
    LOGGER.info(String.format("changeCardSecurityCode,payload=%s", cardSecurityCodeChange));
    Optional<AccountBankingEntity> accountBankingEntity =
        accountBankingRepository.findByAccountNumber(cardSecurityCodeChange.accountNumber());
    if (accountBankingEntity.isEmpty()) {
      return new CommonResponse(99, "Account number not found");
    }

    CardDto cardDto = cardClient.getCardInfoByCardNumber(cardSecurityCodeChange.cardNumber());
    if (cardDto == null) {
      return new CommonResponse(99, "Card does not exist");
    }
    if (cardDto.cardSecurityCode().compareTo(cardSecurityCodeChange.oldCardSecurityCode()) != 0) {
      return new CommonResponse(99, "The above code is not correct");
    }
    CardDto cardDtoChange =
        new CardDto(
            cardSecurityCodeChange.cardNumber(), cardSecurityCodeChange.newCardSecurityCode());
    return cardClient.changeSecurityCode(cardDtoChange);
  }

  @Override
  public DocumentResponse accountStatement(String accountNumber) {
    LOGGER.info(String.format("accountStatement,payload=%s", accountNumber));
    Optional<AccountBankingEntity> accountBankingEntity =
        accountBankingRepository.findByAccountNumber(accountNumber);
    if (accountBankingEntity.isPresent()) {
      CardDto cardDto = cardClient.getCardInfoIdAccount(accountBankingEntity.get().getId());
      if (cardDto == null) {
        LOGGER.error(
            String.format("Card number not found,payload=%s", accountBankingEntity.get().getId()));
        return new DocumentResponse(99, "Card number not found", null);
      }
      DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String document =
                null;
        try {
            document = Utils.generateAccountExtract(
                accountNumber,
                accountBankingEntity.get().getBalance().toString(),
                cardDto.cardNumber(),
                accountBankingEntity.get().getCreationDate().format(CUSTOM_FORMATTER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new DocumentResponse(0, "Reporte Generado Correctamente", document);
    } else {
      return new DocumentResponse(99, "Account number not found", null);
    }
  }
}
