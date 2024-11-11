package com.bankapp.server.domain.dto;

import com.bankapp.server.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    private List<Long> accountIds;
    private List<Long> invoiceIds;
    private List<Long> loanIds;
}
