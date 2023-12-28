package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptDao {
    List<Receipt> findReceiptsBetween(LocalDate startDate, LocalDate endDate);
    void save(Receipt receipt);
    Receipt findReceiptByid(int id);
}
