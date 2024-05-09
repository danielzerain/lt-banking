package com.ltbanking.user.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class UserAccountDto {
    UUID id;
    String accountNumber;
    String cardNumber;
    String cardSecurityCode;
}
