package com.Project.Project.app;

import com.Project.Project.business.FeingService;
import com.Project.Project.business.UserService;
import com.Project.Project.data_acess.User;
import com.Project.Project.dto.UserLoginDTO;
import com.Project.Project.exception.Forbiden;
import com.Project.Project.exception.Unauthorized;
import com.Project.Project.dto.ResponseDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    FeingService feingService;


    @PostMapping("/user/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid User user ,@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn ) {
        if(feingService.tokenValdition(tkn)){
           if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
                throw new Forbiden();
           }
            ResponseDTO responseDTO = userService.verifyEmailCpfNameSenha(user);
            return ResponseEntity.status(responseDTO.getHttpStatus()).body(responseDTO);

        }
       throw new Unauthorized();
    }

    @GetMapping
    @RequestMapping("/users-list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){

                if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
                    throw new Forbiden();
                }

            return userService.listClients();
        }

        throw new Unauthorized();
    }
    @GetMapping
    @RequestMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findId(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
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
    public User update(@RequestBody User user, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn) {
        if(feingService.tokenValdition(tkn)){
            if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
                throw new Forbiden();
            }
            return userService.updateById( user);
        }
        throw new Unauthorized();
    }
    @PatchMapping("/user/updateEmail/{id}/{email}")
    public User updateEmail(@PathVariable("id") Long id, @PathVariable("email") String email, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
                throw new Forbiden();
            }
           return userService.updateEmail(id, email);
        }
        throw new Unauthorized();
    }
    @DeleteMapping("/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete (@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION)String tkn){
        if(feingService.tokenValdition(tkn)){
            if(!feingService.tokenTypeUser(tkn).equalsIgnoreCase("admin")){
                throw new Forbiden();
            }
            try{
                userService.delete(id);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            catch (Exception e){
                 ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.toString(),"test",HttpStatus.BAD_REQUEST);
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
            }


        }
        throw new Unauthorized();
    }


}
