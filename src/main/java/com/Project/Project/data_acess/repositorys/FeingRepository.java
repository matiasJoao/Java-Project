package com.Project.Project.data_acess.repositorys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "Auth", url = "localhost:8081")
public interface FeingRepository {

   // @GetMapping(value = "/login")
  //  UserLoginDTO getUser(@RequestBody UserLoginDTO userLoginDTO);

    @GetMapping(value = "/validation")
     Boolean tokenValidation(@RequestHeader(HttpHeaders.AUTHORIZATION) String tkn);

    @GetMapping(value = "/type")
    String tokenTypeUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn);
}
