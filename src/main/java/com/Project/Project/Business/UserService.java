package com.Project.Project.Business;


import com.Project.Project.Business.CpfValidator.CpfValidator;
import com.Project.Project.Business.Regex.RegexHelpers;
import com.Project.Project.DataAcess.User;
import com.Project.Project.DataAcess.UserInterface;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import com.Project.Project.ResponseHandler.UserValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserInterface userInterface;

    public ResponseJSONhandler verifyEmailCpfNameSenha(String email, String cpf, String name, String senha, User user){
           Boolean verifyEmail, verifyCpf, verifyName, verifySenha;
           String pass;
           ResponseJSONhandler responseJSONhandler;

           RegexHelpers regexHelpers = new RegexHelpers();

           verifyName = regexHelpers.nomeValidation(name);
           verifyEmail = regexHelpers.email(email);
         //  verifyCpf = regexHelpers.cpfValidation(cpf);
           verifySenha = regexHelpers.senhaValidation(senha);


           if (verifyEmail && verifyName && verifySenha) {
               CpfValidator cpfValidator = new CpfValidator();
               Boolean verify = cpfValidator.isCPF(cpf);
               if (verify) {
                   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                   pass = passwordEncoder.encode(user.getSenha());
                   user.setSenha(pass);
                   userInterface.save(user);
                   responseJSONhandler = new ResponseJSONhandler("201", "Cadastrado", HttpStatus.CREATED);
                   return responseJSONhandler;

               } else {
                   responseJSONhandler = new ResponseJSONhandler("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
                   return responseJSONhandler;
               }
           }
           else {
               responseJSONhandler = new ResponseJSONhandler("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);
               return responseJSONhandler;
           }
    }

    public List<User> ListClients(){
        return userInterface.findAll();
    }
    public Optional<User> ListUniqClient(Long id){
        return userInterface.findById(id);
    }
    public User UpdateById(Long id, User user){
        return userInterface.save(user);
    }
    public ResponseJSONhandler delete(Long id){
        userInterface.deleteById(id);
        ResponseJSONhandler responseJSONhandler = new ResponseJSONhandler("200", "Deletado", HttpStatus.OK);
        return responseJSONhandler;
    }
    public User save(User user){
        return userInterface.save(user);
    }
}
