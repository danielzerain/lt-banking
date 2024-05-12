package com.ltbanking.user.controller;

import com.ltbanking.user.domain.*;
import com.ltbanking.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Tag(name = "UserService")
public class UserController {
  private final UserService userService;

  @PostMapping("/register")
  @Operation(operationId="register",summary = "Registro de cliente",description = "Servicio para la creacion del cliente")
  public ResponseEntity<UserAccountDto> register(@RequestBody UserPayload userPayload) {
    try {
      UserAccountDto userAccountDto = userService.createUserAccount(userPayload);
      return ResponseEntity.status(HttpStatus.CREATED).body(userAccountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @PutMapping("/update-username")
  @Operation(operationId="update-username",summary = "Actualizacion de nombre de usuario",description = "Servicio para la modificacion del nombre de usaurio del cliente")
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
  @Operation(operationId="update-password",summary = "Actualizacion de contrasena de usuario",description = "Servicio para la modificacion de la contrasena del cliente")
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
