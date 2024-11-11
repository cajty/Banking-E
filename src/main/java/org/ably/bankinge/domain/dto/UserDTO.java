package org.ably.bankinge.domain.dto;

import org.ably.bankinge.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private int age;
    private Double monthlyIncome;
    private int creditScore;
    private Role role;
    private List<UUID> accountIds;
    private List<Long> invoiceIds;
    private List<Long> loanIds;
}
