package com.ltbanking.user.controller;

import com.ltbanking.user.domain.UserAccountDto;
import com.ltbanking.user.domain.UserPayload;
import com.ltbanking.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserAccountDto> register(@RequestBody UserPayload userPayload) {
    try {
      UserAccountDto userAccountDto = userService.createUserAccount(userPayload);
      return ResponseEntity.status(HttpStatus.CREATED).body(userAccountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
