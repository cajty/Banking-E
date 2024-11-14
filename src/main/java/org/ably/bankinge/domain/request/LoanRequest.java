package org.ably.bankinge.domain.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive value")
    private Double amount;

    @NotNull(message = "Duration cannot be null")
    @Positive(message = "Duration must be a positive value")
    private Double mouthlyPayment;


    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
