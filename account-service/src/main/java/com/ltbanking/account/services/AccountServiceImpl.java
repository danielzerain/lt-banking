package com.ltbanking.account.services;

import com.ltbanking.account.client.CardClient;
import com.ltbanking.account.domain.AccountDto;
import com.ltbanking.account.domain.AccountPayload;
import com.ltbanking.account.domain.CardDto;
import com.ltbanking.account.domain.UserDataPayload;
import com.ltbanking.account.model.AccountBankingEntity;
import com.ltbanking.account.repository.AccountBankingRepository;
import com.ltbanking.account.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

  private AccountBankingRepository accountBankingRepository;
  private CardClient cardClient;

  @Override
  public AccountDto createAccount(UserDataPayload userDataPayload) {
    AccountDto accountDto = new AccountDto();
    AccountBankingEntity accountBankingEntity = new AccountBankingEntity();
    String accountNumber = Utils.generateCode(10);
    accountBankingEntity.setAccountNumber(accountNumber);
    accountBankingEntity.setIdUserBanking(userDataPayload.idUser());
    accountBankingRepository.save(accountBankingEntity);
    accountDto.setAccountNumber(accountNumber);
    AccountPayload accountPayload = new AccountPayload(accountBankingEntity.getId());
    CardDto cardDto = cardClient.register(accountPayload);
    accountDto.setCardNumber(cardDto.cardNumber());
    accountDto.setCardSecurityCode(cardDto.cardSecurityCode());
    return accountDto;
  }
}
