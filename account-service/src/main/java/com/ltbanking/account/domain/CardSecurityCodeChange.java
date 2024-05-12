package com.ltbanking.account.domain;

public record CardSecurityCodeChange(String accountNumber,
    String cardNumber, Integer oldCardSecurityCode, Integer newCardSecurityCode) {}
