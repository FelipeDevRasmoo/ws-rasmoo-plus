package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotBlank(message = "atributo obrigatório")
    private String username;

    @NotBlank(message = "atributo obrigatório")
    private String password;

}
