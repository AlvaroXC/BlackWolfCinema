package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ReceiptDao;
import com.uady.blackWolfCinema.dao.TicketDao;
import com.uady.blackWolfCinema.model.Receipt;
import com.uady.blackWolfCinema.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    private ReceiptDao receiptDao;
    private TicketDao ticketDao;

    @Autowired
    public ReceiptServiceImpl(ReceiptDao receiptDao, TicketDao ticketDao){
        this.receiptDao= receiptDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public List<Receipt> getReceiptsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return receiptDao.findReceiptsBetween(startDate, endDate);
    }

    @Override
    public void saveReceipt(Receipt receiptToSave){
        // receipt.setUser(userDao.findByUserName(username));
        receiptDao.save(receiptToSave);

        for(Ticket ticket: receiptToSave.getTickets()){
            ticket.setReceipt(receiptToSave);
            ticketDao.save(ticket);
        }

    }

    @Override
    public Receipt getReceiptById(int id){
        return receiptDao.findReceiptByid(id);
    }

}
