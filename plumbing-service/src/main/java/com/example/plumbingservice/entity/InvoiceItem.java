package com.example.plumbingservice.entity;

import com.example.plumbingservice.model.Budget;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_invoce_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    private Double  price;

    @Column(name = "budget_id")
    private Long budgetId;

    @Transient
    private Budget budget;


    public InvoiceItem(){
        this.price=(double) 0;
    }
}
