package org.ably.bankinge.service;


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

    public Invoice save(InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceMapper.toEntity(invoiceRequest);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}
