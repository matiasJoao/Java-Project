package com.Project.Project.ResponseHandler.DTO;

import com.Project.Project.DataAcess.Depart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private String name;
    private String email;
    private String senha;
    private Depart depart;
}
