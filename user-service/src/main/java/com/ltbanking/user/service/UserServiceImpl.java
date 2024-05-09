package com.ltbanking.user.service;

import com.ltbanking.user.domain.UserAccountDto;
import com.ltbanking.user.domain.UserPayload;
import com.ltbanking.user.model.UserBankingEntity;
import com.ltbanking.user.repository.UserBankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private UserBankingRepository userBankingRepository;

  @Override
  public UserAccountDto createUserAccount(UserPayload userPayload) throws Exception {
    Optional<UserBankingEntity> userBankingVerify =
        userBankingRepository.findUserBankingEntitiesByIdTypeAndIdentificationNumber(
            userPayload.idType(), userPayload.identificationNumber());
    if (userBankingVerify.isPresent()) {
      throw new Exception(
          String.format(
              "User with idType(%s) and identificationNumber(%s) already exists",
              userPayload.idType(), userPayload.identificationNumber()));
    }
    UserAccountDto accountDto = new UserAccountDto();
    UserBankingEntity userBanking=new UserBankingEntity();
    userBanking.setCompleteName(userPayload.completeName());
    userBanking.setIdType(userPayload.idType());
    userBanking.setIdentificationNumber(userPayload.identificationNumber());
    userBanking.setUserName(userPayload.userName());
    userBanking.setPassword(userPayload.password());
    userBanking=userBankingRepository.save(userBanking);
    accountDto.setId(userBanking.getId());
    return accountDto;
  }
}
