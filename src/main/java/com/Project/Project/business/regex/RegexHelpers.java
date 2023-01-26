package com.Project.Project.business.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexHelpers {
        public Boolean email(String email){
            Pattern pattern = Pattern.compile( "^(.+)@(.+)$");
            Matcher matcher = pattern.matcher(email);
            boolean verify;
            if(matcher.matches()){
                verify = true;
            }
            else{
                verify = false;
            }
            return verify;

        }
        public Boolean cpfValidation(String cpf){
            Pattern pattern = Pattern.compile("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)");
            Matcher matcher = pattern.matcher(cpf);
            Boolean verify;
            if(matcher.matches()){
                verify = true;
            }
            else{
                verify = false;
            }
            return verify;
        }

        public Boolean nomeValidation(String name){
            Pattern pattern = Pattern.compile("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$");
            Matcher matcher = pattern.matcher(name);
            Boolean verify;
            if(matcher.matches()){
                verify = true;
            }
            else{
                verify = false;
            }
            return verify;
        }

        public Boolean senhaValidation(String senha){
            Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$");
            Matcher matcher = pattern.matcher(senha);
            Boolean verify;
            if(matcher.matches()){
                verify = true;
            }
            else{
                verify = false;
            }
            return verify;
        }


}
