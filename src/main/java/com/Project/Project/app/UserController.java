package com.Project.Project.app;

import com.Project.Project.Business.UserService;
import com.Project.Project.DataAcess.User;
import com.Project.Project.DTO.UserLoginDTO;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    @RequestMapping("/user/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid User user, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn) {
        if(userService.tokenValdition(tkn)){

            if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new RuntimeException("Acesso Invalido");
            }
            return userService.verifyEmailCpfNameSenha(user.getEmail(), user.getCpf(), user.getName(), user.getSenha(), user);
        }
        throw new RuntimeException("Token invalido ");
    }

    @GetMapping
    @RequestMapping("/users-list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> ListUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(userService.tokenValdition(tkn)){

                if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                    throw new RuntimeException("Acesso Invalido");
                }

            return userService.ListClients();
        }

        throw new RuntimeException("Token invalido ");
    }
    @GetMapping
    @RequestMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User FindId(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(userService.tokenValdition(tkn)){
            if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new RuntimeException("Acesso Invalido");
            }
            return userService.ListUniqClient(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        }
        throw new RuntimeException("Token invalido ");
    }

    @GetMapping
    @RequestMapping("/user/login")
    public UserLoginDTO loginUser(@RequestBody UserLoginDTO userLoginDTO ){
         return userService.loginClient(userLoginDTO);
    }
    @PutMapping("/user/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User Update(@PathVariable("id") Long id, @RequestBody User user, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn) {
        if(userService.tokenValdition(tkn)){
            if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new RuntimeException("Acesso Invalido");
            }
            String pass;
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            pass = passwordEncoder.encode(user.getSenha());
            user.setSenha(pass);
            return userService.UpdateById(id, user);
        }
        throw new RuntimeException("Token invalido ");
    }
    @PatchMapping("/user/updateEmail/{id}/{email}")
    public User UpdateEmail(@PathVariable("id") Long id, @PathVariable("email") String email, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(userService.tokenValdition(tkn)){
            if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new RuntimeException("Acesso Invalido");
            }
            User user = FindId(id, null);
            user.setEmail(email);
            return userService.save(user);
        }
        throw new RuntimeException("Token invalido ");
    }
    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseJSONhandler delete (@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(userService.tokenValdition(tkn)){
            if(userService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || userService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new RuntimeException("Acesso Invalido");
            }
            FindId(id, null);
            return userService.delete(id);
        }
        throw new RuntimeException("Token invalido ");
    }


}
