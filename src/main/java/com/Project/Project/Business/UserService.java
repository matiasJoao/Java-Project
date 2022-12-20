package com.Project.Project.Business;

import com.Project.Project.Business.Regex.RegexHelpers;
import com.Project.Project.DataAcess.User;
import com.Project.Project.DataAcess.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserInterface userInterface;

    public String verifyEmailCpfNameSenha(String email, String cpf, String name, String senha, User user){

        Boolean verifyEmail, verifyCpf, verifyName, verifySenha;
        String msg;

        RegexHelpers regexHelpers = new RegexHelpers();

        verifyName = regexHelpers.nomeValidation(name);
        verifyEmail = regexHelpers.email(email);
        verifyCpf = regexHelpers.cpfValidation(cpf);
        verifySenha = regexHelpers.senhaValidation(senha);


        if(verifyEmail && verifyCpf && verifyName && verifySenha){
            msg = "Cadastrado com sucesso";
            userInterface.save(user);
        }
        else {
            msg = "Email invalido ou Cpf invalido ou Nome invalido ou padrao de senha incorreta \n tem que conter um simbolo especia uma letra maiuscula e um numero";
        }

        return msg;
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
    public String  delete(Long id){
        userInterface.deleteById(id);
        String msg = "deletado com sucesso";
        return msg;
    }
}
