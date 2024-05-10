package com.ltbanking.account.controller;

import com.ltbanking.account.domain.AccountDto;
import com.ltbanking.account.domain.UserDataPayload;
import com.ltbanking.account.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
  private AccountService accountService;

  @PostMapping("/register")
  public ResponseEntity<AccountDto> register(@RequestBody UserDataPayload userPayload) {
    try {
      AccountDto accountDto = accountService.createAccount(userPayload);
      return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
