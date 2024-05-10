package com.ltbanking.user.controller;

import com.ltbanking.user.domain.*;
import com.ltbanking.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PutMapping("/update-username")
  public ResponseEntity<CommonResponse> updateUserName(
      @RequestBody UserNameChangeDto userNameChangeDto) {
    try {
      CommonResponse response = userService.updateUserName(userNameChangeDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @PutMapping("/update-password")
  public ResponseEntity<CommonResponse> updatePassword(
      @RequestBody PasswordChangeDto passwordChangeDto) {
    try {
      CommonResponse response = userService.updatePassword(passwordChangeDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
