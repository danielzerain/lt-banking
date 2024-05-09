package com.ltbanking.user.domain;

public record UserPayload(
    String completeName,
    String idType,
    String identificationNumber,
    String userName,
    String password) {}
