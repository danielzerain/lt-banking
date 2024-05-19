package com.ltbanking.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDto {
  private String accountNumber;
  private String cardNumber;
  private Integer cardSecurityCode;
}
