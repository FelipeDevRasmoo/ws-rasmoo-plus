package com.client.ws.rasmooplus.dto;

import com.client.ws.rasmooplus.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentInfoDto {

    private Long id;

    @Size(min = 16, max = 16, message = "deve conter 16 caracteres")
    private String cardNumber;

    @Min(value = 1)
    @Max(value = 12)
    private Long cardExpirationMonth;

    private Long cardExpirationYear;

    @Size(min = 3, max = 3, message = "deve conter 3 caracteres")
    private String cardSecurityCode;

    private BigDecimal price;

    private LocalDate dtPayment = LocalDate.now();

    private Long installments;

    @NotNull(message = "deve ser informado")
    private Long userId;
}
