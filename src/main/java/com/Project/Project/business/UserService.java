package com.Project.Project.business;


import com.Project.Project.business.cpf_validation.CpfValidator;
import com.Project.Project.business.regex.RegexHelpers;
import com.Project.Project.data_acess.User;
import com.Project.Project.data_acess.repositorys.UserInterface;
import com.Project.Project.dto.UserLoginDTO;
import com.Project.Project.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserInterface userInterface;




    public ResponseDTO verifyEmailCpfNameSenha(User user){
           Boolean verifyEmail,  verifyName, verifySenha;
           String pass;
           ResponseDTO responseDTO;
           RegexHelpers regexHelpers = new RegexHelpers();
           verifyName = regexHelpers.nomeValidation(user.getName());
           verifyEmail = regexHelpers.email(user.getEmail());
           verifySenha = regexHelpers.senhaValidation(user.getSenha());


           if (verifyEmail && verifyName && verifySenha) {
               CpfValidator cpfValidator = new CpfValidator();
               Boolean verify = cpfValidator.isCPF(user.getCpf());
               if (verify) {
                   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                   pass = passwordEncoder.encode(user.getSenha());
                   user.setSenha(pass);
                   userInterface.save(user);
                   return responseDTO = new ResponseDTO("201", "Cadastrado", HttpStatus.CREATED);

               } else {
                   return  responseDTO = new ResponseDTO("400", "CPF INVALIDO", HttpStatus.BAD_REQUEST);
               }
           }
           else {
              return responseDTO = new ResponseDTO("400", "USUARIO OU EMAIL OU SENHA INVALIDOS", HttpStatus.BAD_REQUEST);

           }
    }

    public List<User> listClients(){
        return userInterface.findAll();
    }
    public User listUniqClient(Long id){
        return userInterface.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }
    public User updateById(User user){
        String pass;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        pass = passwordEncoder.encode(user.getSenha());
        user.setSenha(pass);
        return userInterface.save(user);
    }
    public void delete(Long id) throws Exception{
            listUniqClient(id);
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
    public User updateEmail (Long id, String email){
        User user = listUniqClient(id);
        user.setEmail(email);
        return userInterface.save(user);

    }


    }



