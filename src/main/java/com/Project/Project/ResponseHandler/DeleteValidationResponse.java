package com.Project.Project.ResponseHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class DeleteValidationResponse {
    private String cod;
    private String mensage;
    private HttpStatus httpStatus;
}
