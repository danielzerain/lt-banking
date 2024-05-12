package com.ltbanking.account.services;

import com.ltbanking.account.domain.*;

import java.util.UUID;

public interface AccountService {
  AccountDto createAccount(UserDataPayload userDataPayload);

  CommonResponse changeCardSecurityCode(CardSecurityCodeChange cardSecurityCodeChange);

  DocumentResponse accountStatement(String accountNumber);
}
