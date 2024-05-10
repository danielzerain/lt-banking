package com.ltbanking.user.domain;

public record PasswordChangeDto(String identificationNumber,String oldPassword,String newPassword) {}
