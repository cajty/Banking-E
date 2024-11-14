package org.ably.bankinge.mapper;

import org.ably.bankinge.domain.dto.TransactionDTO;
import org.ably.bankinge.domain.entities.Transaction;
import org.ably.bankinge.domain.request.TransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "accountOfSender", source = "accountOfSender.id")
    @Mapping(target = "accountOfReceiver", source = "accountOfSender.id")
    @Mapping(target = "type", source = "type")
    TransactionDTO toDTO(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountOfSender.id", source = "accountSenderId")
    @Mapping(target = "accountOfReceiver.id", source = "accountReceiverId")
    Transaction toEntity(TransactionRequest transactionRequest);

    List<TransactionDTO> toDTOList(List<Transaction> transactions);
}