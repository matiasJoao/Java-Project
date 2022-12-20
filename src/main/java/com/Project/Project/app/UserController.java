package com.Project.Project.app;

import com.Project.Project.Business.UserService;
import com.Project.Project.DataAcess.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    @RequestMapping("/user/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastro(@RequestBody User user) {
        String email = user.getEmail();
        String nome = user.getName();
        String cpf = user.getCpf();
        String senha = user.getSenha();
        return  userService.verifyEmailCpfNameSenha(email, cpf, nome, senha, user);
    }

    @GetMapping
    @RequestMapping("/users-list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> ListUser(){
        return userService.ListClients();
    }


}
