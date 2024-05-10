package com.ltbanking.account.client;

import com.ltbanking.account.domain.AccountPayload;
import com.ltbanking.account.domain.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="account-service", url= "http://localhost:8007/card")
public interface CardClient {
    @PostMapping(value = "/register")
    CardDto register(AccountPayload userDataPayload);

}
