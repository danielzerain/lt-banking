package com.ltbanking.user.repository;

import com.ltbanking.user.model.UserBankingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserBankingRepository extends CrudRepository<UserBankingEntity, UUID> {

  Optional<UserBankingEntity> findUserBankingEntitiesByIdTypeAndIdentificationNumber(
      String idType, String identificationNumber);

  Optional<UserBankingEntity> findUserBankingEntitiesByIdentificationNumber(
      String identificationNumber);
}
