package com.Project.Project.Business;


import com.Project.Project.Business.CpfValidator.CpfValidator;
import com.Project.Project.Business.Regex.RegexHelpers;
import com.Project.Project.DataAcess.User;
import com.Project.Project.DataAcess.Repositorys.UserInterface;
import com.Project.Project.DataAcess.Repositorys.FeingRepository;
import com.Project.Project.DTO.UserLoginDTO;
import com.Project.Project.ResponseHandler.ResponseJSONhandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserInterface userInterface;




    public ResponseEntity verifyEmailCpfNameSenha(String email, String cpf, String name, String senha, User user){
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
                   return ResponseEntity.status(HttpStatus.CREATED).body(responseJSONhandler);

               } else {
                   responseJSONhandler = new ResponseJSONhandler("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
                   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler);
               }
           }
           else {
               responseJSONhandler = new ResponseJSONhandler("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJSONhandler);
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
    public void delete(Long id){
        userInterface.deleteById(id);
    }
    public User save(User user){
        return userInterface.save(user);
    }

    public UserLoginDTO loginClient(UserLoginDTO userLoginDTO){
        User user = userInterface.findByEmail(userLoginDTO.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean verifyPassword = passwordEncoder.matches(userLoginDTO.getSenha(), user.getSenha());

        if(user.getEmail() != null && verifyPassword ){
            UserLoginDTO userLoginDTO1 = new UserLoginDTO(user.getName(), user.getEmail(), user.getSenha(), user.getDepart());
            return userLoginDTO1;

        }
       throw  new RuntimeException("deu ruim");

    }


    }



