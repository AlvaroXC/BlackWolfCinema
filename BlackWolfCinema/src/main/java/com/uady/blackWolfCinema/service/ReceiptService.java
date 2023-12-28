package com.uady.blackWolfCinema.service;


import com.uady.blackWolfCinema.model.Receipt;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {

    public List<Receipt> getReceiptsBetweenDates(LocalDate startDate, LocalDate endDate);
    void saveReceipt(Receipt receiptToSave);
    Receipt getReceiptById(int id);

}
