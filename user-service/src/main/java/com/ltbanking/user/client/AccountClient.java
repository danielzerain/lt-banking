package com.ltbanking.user.client;

import com.ltbanking.user.domain.AccountDto;
import com.ltbanking.user.domain.UserDataPayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-service")
public interface AccountClient {

  @PostMapping(value = "/register")
  AccountDto register(UserDataPayload userDataPayload);
}
