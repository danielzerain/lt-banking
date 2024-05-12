package com.ltbanking.cardservice.domain;

public record SecurityCodeChangeDto(String cardNumber, Integer OldCardSecurityCode,Integer newCardSecurityCode) {}
