package com.Project.Project.DataAcess;

import com.Project.Project.ResponseHandler.DTO.TokenDTO;
import com.Project.Project.ResponseHandler.DTO.UserLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "Auth", url = "localhost:8081")
public interface FeingRepository {

    @PostMapping(value = "/autentication")
    TokenDTO getUser(@RequestBody UserLoginDTO userLoginDTO);
}
