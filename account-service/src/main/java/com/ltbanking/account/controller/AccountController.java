package com.ltbanking.account.controller;

import com.ltbanking.account.domain.*;
import com.ltbanking.account.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Tag(name = "AccountService")
public class AccountController {
  private AccountService accountService;

  @PostMapping("/register")
  @Operation(operationId="register",summary = "Registro de Cuentas",description = "Servicio para la creacion de la cuenta")
  public ResponseEntity<AccountDto> register(@RequestBody UserDataPayload userPayload) {
    try {
      AccountDto accountDto = accountService.createAccount(userPayload);
      return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @PutMapping("/change-pin")
  @Operation(operationId="change-pin",summary = "Cambio de PIN",description = "Servicio para el cambio del codigo de securidad de la tarjeta-PIN ")
  public ResponseEntity<CommonResponse> register(@RequestBody CardSecurityCodeChange cardSecurityCodeChange) {
    try {
      CommonResponse response = accountService.changeCardSecurityCode(cardSecurityCodeChange);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  @GetMapping("/account-statement/{id}")
  @Operation(operationId="register",summary = "Reporte Estado Cuenta",description = "Emision del reporte de estado de cuentad el cliente")
  public ResponseEntity<DocumentResponse> accountStatement(
      @PathVariable("id") String accountNumber) {
    try {
      DocumentResponse accountDto = accountService.accountStatement(accountNumber);
      return ResponseEntity.status(HttpStatus.OK).body(accountDto);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
