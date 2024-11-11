package org.ably.bankinge.mapper;

import org.ably.bankinge.domain.dto.LoanDTO;
import org.ably.bankinge.domain.entities.Loan;
import org.ably.bankinge.domain.request.LoanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "userId", source = "user.id")
    LoanDTO toDTO(Loan loan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Loan toEntity(LoanRequest loanRequest);

    List<LoanDTO> toDTOList(List<Loan> loans);

}
