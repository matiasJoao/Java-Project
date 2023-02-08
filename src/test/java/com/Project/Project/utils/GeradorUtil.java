package com.Project.Project.utils;


import com.Project.Project.dto.TokenDTO;
import com.Project.Project.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class GeradorUtil {


    RestTemplate restTemplate = new RestTemplate();

    public TokenDTO token(){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("Admin@emailcom");
        userDTO.setSenha("1234aB@8");
        HttpEntity<Object> request = new HttpEntity<Object>(userDTO);
        var res = restTemplate.exchange("http://localhost:8081/login", HttpMethod.GET, request, TokenDTO.class);
       return res.getBody();
    }
}
