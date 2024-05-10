package com.ltbanking.user.service;

import com.ltbanking.user.domain.*;

public interface UserService {
  UserAccountDto createUserAccount(UserPayload userPayload) throws Exception;

  CommonResponse updateUserName(UserNameChangeDto userNameChangeDto) throws Exception;

  CommonResponse updatePassword(PasswordChangeDto passwordChangeDto) throws Exception;

}
