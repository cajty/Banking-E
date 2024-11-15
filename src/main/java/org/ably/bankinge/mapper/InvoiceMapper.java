package org.ably.bankinge.mapper;

import org.ably.bankinge.domain.dto.InvoiceDTO;
import org.ably.bankinge.domain.entities.Invoice;
import org.ably.bankinge.domain.request.InvoiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {



    @Mapping(target = "userId", source = "user.id")
    InvoiceDTO toDTO(Invoice invoice);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Invoice toEntity(InvoiceRequest invoiceRequest);

    List<InvoiceDTO> toDTOList(List<Invoice> bills);

}
