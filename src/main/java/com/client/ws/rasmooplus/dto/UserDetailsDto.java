package com.client.ws.rasmooplus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    @Email(message = "inválido")
    private String email;

    @NotBlank(message = "atributo inválido")
    private String password;

    private String recoveryCode;

}
