package org.ably.bankinge.domain.dto;

import org.ably.bankinge.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private Double amount;
    private TransactionType type;
    private UUID accountOfSender;
    private UUID accountOfReceiver;
}
