package com.uady.blackWolfCinema.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH
    })
    @JoinColumn(name ="username" )
    private User user;

    public Receipt(LocalDate receiptDate, float total, User user) {
        this.receiptDate = receiptDate;
        this.total = total;
        this.user = user;
    }


}
