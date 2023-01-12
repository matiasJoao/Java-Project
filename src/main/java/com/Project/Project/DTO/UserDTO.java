package com.Project.Project.DTO;
import com.Project.Project.DataAcess.Depart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String senha;
}
