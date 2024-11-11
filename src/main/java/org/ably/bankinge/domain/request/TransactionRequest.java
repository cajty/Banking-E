package org.ably.bankinge.domain.request;

import org.ably.bankinge.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Amount cannot be null")
    private Double amount;

    @NotNull(message = "Type cannot be null")
    private TransactionType type;

    @NotNull(message = "Account number of that sender   cannot be null")
    private UUID accountSenderId;

    @NotNull(message = "Account number of that receiver  cannot be null")
    private UUID accountReceiverId;
}
