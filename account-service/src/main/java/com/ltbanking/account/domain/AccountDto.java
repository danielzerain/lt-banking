package com.ltbanking.account.domain;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private String cardNumber;
    private String cardSecurityCode;
}
