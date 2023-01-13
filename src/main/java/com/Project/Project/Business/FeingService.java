package com.Project.Project.Business;

import com.Project.Project.DataAcess.Repositorys.FeingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeingService {
    @Autowired
    private FeingRepository feingRepository;
    public Boolean tokenValdition(String tkn){
        return feingRepository.tokenValidation(tkn);
    }
    public String tokenTypeUser(String tkn){
        return feingRepository.tokenTypeUser(tkn);
    }
}
