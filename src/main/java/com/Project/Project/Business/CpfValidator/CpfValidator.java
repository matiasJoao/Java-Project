package com.Project.Project.Business.CpfValidator;

import br.com.caelum.stella.validation.CPFValidator;

public class CpfValidator {
    public boolean Cpfvalido(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        try{ cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
