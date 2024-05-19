package com.ltbanking.user.service;

import com.ltbanking.user.client.AccountClient;
import com.ltbanking.user.domain.*;
import com.ltbanking.user.model.UserBankingEntity;
import com.ltbanking.user.repository.UserBankingRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserBankingRepository userBankingRepository;
  private final AccountClient accountClient;

  public UserServiceImpl(UserBankingRepository userBankingRepository, AccountClient accountClient) {
    this.userBankingRepository = userBankingRepository;
    this.accountClient = accountClient;
  }

  @Override
  public UserAccountDto createUserAccount(UserPayload userPayload) throws Exception {
    LOGGER.info(String.format("userApi,payload=%s", userPayload));
    UserAccountDto userAccountDto = new UserAccountDto();
    UserBankingEntity userBanking = new UserBankingEntity();
    userBanking.setCompleteName(userPayload.completeName());
    userBanking.setIdType(userPayload.idType());
    userBanking.setIdentificationNumber(userPayload.identificationNumber());
    userBanking.setUserName(userPayload.userName());
    userBanking.setPassword(userPayload.password());
    userBanking = userBankingRepository.save(userBanking);
    UserDataPayload userDataPayload = new UserDataPayload(userBanking.getId());
    AccountDto accountDto = accountClient.register(userDataPayload);
    userAccountDto.setId(userBanking.getId());
    userAccountDto.setAccountNumber(accountDto.getAccountNumber());
    userAccountDto.setCardNumber(accountDto.getCardNumber());
    userAccountDto.setCardSecurityCode(accountDto.getCardSecurityCode());
    return userAccountDto;
  }

  @Override
  public CommonResponse updateUserName(UserNameChangeDto userNameChangeDto) throws Exception {
    Optional<UserBankingEntity> userBankingVerify =
        userBankingRepository.findUserBankingEntitiesByIdentificationNumber(
            userNameChangeDto.identificationNumber());
    if (userBankingVerify.isEmpty()) {
      throw new Exception(
          String.format(
              "User with identificationNumber(%s) not exists",
              userNameChangeDto.identificationNumber()));
    }
    userBankingVerify.get().setUserName(userNameChangeDto.userName());
    userBankingRepository.save(userBankingVerify.get());
    return new CommonResponse(1, "User name updated successfully");
  }

  @Override
  public CommonResponse updatePassword(PasswordChangeDto passwordChangeDto) throws Exception {
    Optional<UserBankingEntity> userBankingVerify =
        userBankingRepository.findUserBankingEntitiesByIdentificationNumber(
            passwordChangeDto.identificationNumber());
    if (userBankingVerify.isEmpty()) {
      throw new Exception(
          String.format(
              "User with identificationNumber(%s) not exists",
              passwordChangeDto.identificationNumber()));
    }
    if (userBankingVerify.get().getPassword().compareTo(passwordChangeDto.oldPassword()) != 0) {
      throw new Exception(
          String.format(
              "User old password is incorrect. Old password: %s",
              passwordChangeDto.identificationNumber()));
    }
    userBankingVerify.get().setPassword(passwordChangeDto.newPassword());
    userBankingRepository.save(userBankingVerify.get());
    return new CommonResponse(1, "User password updated successfully");
  }
}
