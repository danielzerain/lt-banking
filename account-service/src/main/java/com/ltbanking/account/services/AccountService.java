package com.ltbanking.account.services;

import com.ltbanking.account.domain.AccountDto;
import com.ltbanking.account.domain.UserDataPayload;

public interface AccountService {
  AccountDto createAccount(UserDataPayload userDataPayload);
}
