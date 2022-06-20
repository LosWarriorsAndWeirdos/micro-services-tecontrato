package com.example.plumbingservice.repository;

import com.example.plumbingservice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    public List<Invoice> findByBudgetId(Long customerId );
    public Invoice findByNumberInvoice(String numberInvoice);
}
