package com.ltbanking.account.repository;

import com.ltbanking.account.model.AccountBankingEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBankingRepository extends CrudRepository<AccountBankingEntity, UUID> {}
