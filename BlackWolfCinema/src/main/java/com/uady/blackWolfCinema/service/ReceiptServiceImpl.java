package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ReceiptDao;
import com.uady.blackWolfCinema.dao.TicketDao;
import com.uady.blackWolfCinema.model.Receipt;
import com.uady.blackWolfCinema.model.Ticket;

import com.uady.blackWolfCinema.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    private final ReceiptRepository receiptRepository;
    private final TicketDao ticketDao;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, TicketDao ticketDao){
        this.receiptRepository= receiptRepository;
        this.ticketDao = ticketDao;
    }

    @Override
    public List<Receipt> getReceiptsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return receiptRepository.findByReceiptDateBetween(startDate, endDate);
    }

    @Override
    public void saveReceipt(Receipt receiptToSave){
        // receipt.setUser(userDao.findByUserName(username));
        receiptRepository.save(receiptToSave);

        for(Ticket ticket: receiptToSave.getTickets()){
            ticket.setReceipt(receiptToSave);
            ticketDao.save(ticket);
        }

    }

    @Override
    public Receipt getReceiptById(int id){
        Optional<Receipt> result = receiptRepository.findById(id);
        Receipt receipt = null;

        if(result.isEmpty()){
            return null;
        }

        receipt = result.get();

        return receipt;
    }

}
