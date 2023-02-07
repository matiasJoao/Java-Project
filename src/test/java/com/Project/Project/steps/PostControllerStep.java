package com.Project.Project.steps;

import com.Project.Project.data_acess.Depart;
import com.Project.Project.data_acess.User;
import io.cucumber.java.pt.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class PostControllerStep {
    User usuario;
    Integer port = 8082;
    RestTemplate restTemplate = new RestTemplate();
    String beginUrl = "http://localhost:";
    @Dado(": Que eu dou um post na api com o valor do usuario")
    public void que_chamo_a() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsb2NhbGhvc3Q6ODA4MSIsInN1YiI6IkFkbWluQGVtYWlsY29tIiwiZGVwYXJ0Ijp7ImlkIjozLCJkZXBhcnQiOiJhZG1pbiJ9LCJpYXQiOjE2NzU3ODAxMDgsImV4cCI6MzQ3NTc4MDEwOH0.QNEE4jAYYGQVKUZNEXNqUidGcJTf6ZxLXskiV2qIUrw";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",token);
        usuario = new User();
        usuario.setEmail("mateu9610@uorak.com");
        usuario.setCpf("74468563001");
        usuario.setName("jao");
        usuario.setSenha("12345678aA@");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> request = new HttpEntity<Object>(usuario, headers);
        var response = restTemplate.exchange(beginUrl + port + "/user/cadastro", HttpMethod.POST, request , ResponseEntity.class);
        assertNull(response);
    }
    @Entao(": Retorna o status {string}")
    public void retorna_o_status(String status) {

    }
}
