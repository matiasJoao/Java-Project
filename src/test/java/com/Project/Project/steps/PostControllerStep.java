package com.Project.Project.steps;

import com.Project.Project.data_acess.Depart;
import com.Project.Project.data_acess.User;
import com.Project.Project.dto.TokenDTO;
import com.Project.Project.utils.GeradorUtil;
import io.cucumber.java.pt.*;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class PostControllerStep {

    GeradorUtil geradorUtil = new GeradorUtil();
    User usuario;
    Integer port = 8082;
    RestTemplate restTemplate = new RestTemplate();
    String beginUrl = "http://localhost:";
    Integer response;

    @Dado("Que eu tenho um usuario qualquer com role de admin")
    public void que_eu_tenho_um_usuario_qualquer_com_role_de_admin() {
        usuario = new User();
        usuario.setEmail("mateu9610@uorak.com");
        usuario.setCpf("74468563001");
        usuario.setName("jao");
        usuario.setSenha("12345678aA@");
        usuario.setDepart(Depart.builder().id(3L).depart("ADMIN").build());
    }
    @Quando("Faco um requisicao de cadastro")
    public void faco_um_requisicao_de_cadastro() {
        TokenDTO token = geradorUtil.token();
        String tkn = String.format("%s %s", token.getType(), token.getToken());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",tkn );
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> request = new HttpEntity<Object>(usuario, headers);
        var res   = restTemplate.exchange(beginUrl + port + "/user/cadastro", HttpMethod.POST, request , String.class);
        response = res.getStatusCode().value();
    }
    @Entao("Devo receber o status {string}")
    public void devo_receber_o_status(String status) {
        assertEquals(status, String.valueOf(response));
    }

}
