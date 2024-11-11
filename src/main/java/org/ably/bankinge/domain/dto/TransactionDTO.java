package org.ably.bankinge.domain.dto;

import org.ably.bankinge.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private Double amount;
    private TransactionType type;
    private AccountDTO accountOfSender;
    private AccountDTO accountOfReceiver;
}
