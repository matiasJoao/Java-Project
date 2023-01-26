package com.Project.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ResponseDTO {
    private String cod;
    private String mensage;
    private HttpStatus httpStatus;
}
