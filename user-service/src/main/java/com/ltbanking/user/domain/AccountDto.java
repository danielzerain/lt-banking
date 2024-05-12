package com.ltbanking.user.domain;

import lombok.Data;

@Data
public class AccountDto {
    private String accountNumber;
    private String cardNumber;
    private Integer cardSecurityCode;
}
