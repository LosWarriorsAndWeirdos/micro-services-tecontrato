package com.example.plumbingservice.service;

import com.example.plumbingservice.client.BudgetClient;
import com.example.plumbingservice.client.PropietarioClient;
import com.example.plumbingservice.entity.Invoice;
import com.example.plumbingservice.entity.InvoiceItem;
import com.example.plumbingservice.model.Budget;
import com.example.plumbingservice.model.Propietario;
import com.example.plumbingservice.repository.InvoiceItemsRepository;
import com.example.plumbingservice.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Qualifier("com.example.plumbingservice.client.PropietarioClient")
    @Autowired
    PropietarioClient propietarioClient;

    @Autowired
    BudgetClient budgetClient;

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach( invoiceItem -> {
            budgetClient.updatePayment( invoiceItem.getBudgetId(), invoiceItem.getPrice());
        });

        return invoiceDB;
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setPropietarioId(invoice.getPropietarioId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {

        Invoice invoice= invoiceRepository.findById(id).orElse(null);
        if (null != invoice ){
            Propietario customer = propietarioClient.getPropietarios(invoice.getPropietarioId()).getBody();
            invoice.setPropietario(customer);
            List<InvoiceItem> listItem=invoice.getItems().stream().map(invoiceItem -> {
                Budget product = budgetClient.getProduct(invoiceItem.getBudgetId()).getBody();
                invoiceItem.setBudget(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
        }
        return invoice ;
    }
}
