package com.ltbanking.cardservice.repository;

import java.util.Optional;
import java.util.UUID;

import com.ltbanking.cardservice.model.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, UUID> {
    Optional<CardEntity> findByCardNumber(String cardNumber);

}
