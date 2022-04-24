package com.client.ws.rasmooplus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {

    private Long id;

    @NotBlank(message = "campo name não pode ser nulo ou vazio")
    @Size(min = 5, max = 30)
    private String name;

    @Max(value = 12, message = "campo accessMonths não pode ser maior que 12")
    private Long accessMonths;

    @NotNull(message = "campo price não pode ser nulo")
    private BigDecimal price;

    @NotBlank(message = "campo productKey não pode ser nulo ou vazio")
    @Size(min = 5, max = 15, message = "campo productKey deve ter tamanho entre 5 e 15")
    private String productKey;
}
