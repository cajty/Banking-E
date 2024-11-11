package org.ably.bankinge.service;

import org.ably.bankinge.domain.dto.InvoiceDTO;
import org.ably.bankinge.domain.entities.Invoice;
import org.ably.bankinge.domain.request.InvoiceRequest;
import org.ably.bankinge.mapper.InvoiceMapper;
import org.ably.bankinge.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceDTO save(InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceMapper.toEntity(invoiceRequest);
        return invoiceMapper.toDTO(invoiceRepository.save(invoice));
    }

    public List<InvoiceDTO> findAll() {
        return invoiceMapper.toDTOList(invoiceRepository.findAll());
    }

    public Optional<InvoiceDTO> findById(Long id) {
        return invoiceRepository.findById(id).map(invoiceMapper::toDTO);
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}
