package com.uady.blackWolfCinema.controller;

import com.uady.blackWolfCinema.model.Receipt;
import com.uady.blackWolfCinema.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReceiptController {

    ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService){
        this.receiptService= receiptService;
    }

    @GetMapping("/receipt")
    public String showReceiptForm(){
        return "receiptForm";
    }

    @GetMapping("receipt/generateReceipt")
    public String showReceiptsBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                           Model model) {

        List<Receipt> receiptsBetweenDates = receiptService.getReceiptsBetweenDates(startDate, endDate);

        model.addAttribute("receipts", receiptsBetweenDates);


        return "report";

    }

    




}
