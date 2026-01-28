package com.uady.blackWolfCinema.repository;

import com.uady.blackWolfCinema.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findByReceiptDateBetween(LocalDate startDate, LocalDate endDate);
}
