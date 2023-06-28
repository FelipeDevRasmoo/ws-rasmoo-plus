package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
