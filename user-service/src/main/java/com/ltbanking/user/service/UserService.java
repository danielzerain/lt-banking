package com.ltbanking.user.service;

import com.ltbanking.user.domain.UserAccountDto;
import com.ltbanking.user.domain.UserPayload;

public interface UserService {
  UserAccountDto createUserAccount(UserPayload userPayload) throws Exception;
}
