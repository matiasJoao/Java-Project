package com.Project.Project.app;

import com.Project.Project.Business.FeingService;
import com.Project.Project.Business.UserService;
import com.Project.Project.DataAcess.User;
import com.Project.Project.DTO.UserLoginDTO;
import com.Project.Project.ResponseHandler.Exceptiron.Forbiden;
import com.Project.Project.ResponseHandler.Exceptiron.Unauthorized;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    FeingService feingService;
    @PostMapping
    @RequestMapping("/user/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid User user ,@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn ) {
        if(feingService.tokenValdition(tkn)){
           if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
           }
           return userService.verifyEmailCpfNameSenha(user.getEmail(), user.getCpf(), user.getName(), user.getSenha(), user);
        }
       throw new Unauthorized();
    }

    @GetMapping
    @RequestMapping("/users-list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> ListUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){

                if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                    throw new Forbiden();
                }

            return userService.listClients();
        }

        throw new Unauthorized();
    }
    @GetMapping
    @RequestMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User FindId(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            return userService.listUniqClient(id);
        }
        throw new Unauthorized();
    }

    @GetMapping
    @RequestMapping("/user/login")
    public UserLoginDTO loginUser(@RequestBody UserLoginDTO userLoginDTO ){
         return userService.loginClient(userLoginDTO);
    }
    @PutMapping("/user/update")
    @ResponseStatus(HttpStatus.OK)
    public User Update(@RequestBody User user, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn) {
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            return userService.updateById( user);
        }
        throw new Unauthorized();
    }
    @PatchMapping("/user/updateEmail/{id}/{email}")
    public User UpdateEmail(@PathVariable("id") Long id, @PathVariable("email") String email, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            User user = FindId(id, tkn);
            user.setEmail(email);
            return userService.save(user);
        }
        throw new Unauthorized();
    }
    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete (@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(feingService.tokenTypeUser(tkn).equalsIgnoreCase("cliente") || feingService.tokenTypeUser(tkn).equalsIgnoreCase("fornecedor")){
                throw new Forbiden();
            }
            try{
                userService.delete(id);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            catch (Exception e){
                 ResponseJSONhandler responseJSONhandler = new ResponseJSONhandler(HttpStatus.BAD_REQUEST.toString(),"test",HttpStatus.BAD_REQUEST);
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler);
            }


        }
        throw new Unauthorized();
    }


}
