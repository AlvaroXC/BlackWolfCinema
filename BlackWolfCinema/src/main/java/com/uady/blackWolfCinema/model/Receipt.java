package com.uady.blackWolfCinema.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Receipt_Id")
    private int idReceipt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "Receipt_Date")
    private LocalDate receiptDate;

    @Column(name = "Total")
    private float total;

    @OneToMany(mappedBy = "receipt")
    private List<Ticket> tickets;

    public Receipt(LocalDate receiptDate, float total) {
        this.receiptDate = receiptDate;
        this.total = total;
    }

    public void add(Ticket ticket){
        if(tickets==null){
            tickets= new ArrayList<>();
        }
        tickets.add(ticket);
        ticket.setReceipt(this);
    }

}
