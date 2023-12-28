package com.uady.blackWolfCinema.dao;

import com.uady.blackWolfCinema.model.Receipt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

    @Autowired
    private EntityManager entityManager;

    public ReceiptDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Receipt> findReceiptsBetween(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Receipt> theQuery = entityManager.createQuery("FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate", Receipt.class);
        theQuery.setParameter("startDate", startDate);
        theQuery.setParameter("endDate", endDate);

        List<Receipt> receipts = null;
        try {
            receipts = theQuery.getResultList();
        } catch (Exception e) {
            receipts = null;
        }

        return receipts;
    }

    @Override
    @Transactional
    public void save(Receipt receipt) {
        entityManager.merge(receipt);
    }

    @Override
    public Receipt findReceiptByid(int id) {
        TypedQuery<Receipt> theQuery = entityManager.createQuery("from Receipt where idReceipt=:idReceipt", Receipt.class);
		theQuery.setParameter("idReceipt", id);

		Receipt theReceipt = null;
		try {
			theReceipt = theQuery.getSingleResult();
		} catch (Exception e) {
			theReceipt = null;
		}

		return theReceipt;
    }

}
