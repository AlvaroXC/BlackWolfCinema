package com.uady.blackWolfCinema.service;

import com.uady.blackWolfCinema.dao.ReceiptDao;
import com.uady.blackWolfCinema.dao.UserDao;
import com.uady.blackWolfCinema.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService{

    private ReceiptDao receiptDao;
    private UserDao userDao;

    @Autowired
    public ReceiptServiceImpl(ReceiptDao receiptDao, UserDao userDao){
        this.receiptDao= receiptDao;
        this.userDao = userDao;
    }

    @Override
    public List<Receipt> getReceiptsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return receiptDao.findReceiptsBetween(startDate, endDate);
    }

    @Override
    public void saveReceipt(Receipt receiptToSave, String username){
        Receipt receipt = new Receipt();
        receipt.setReceiptDate(receiptToSave.getReceiptDate());
        receipt.setTotal(receiptToSave.getTotal());
        receipt.setUser(userDao.findByUserName(username));
        receiptDao.save(receipt);
    }

    /*
     	@Override
	public void save(UserValidation userValidation) {
		User user = new User();

		// assign user details to the user object
		user.setUserName(userValidation.getUserName());
		user.setPassword(passwordEncoder.encode(userValidation.getPassword()));
		user.setName(userValidation.getFirstName());
		user.setLastname(userValidation.getLastName());
		user.setEmail(userValidation.getEmail());

		// give user default role of "employee"
		user.setRole(roleDao.findRoleByName("ROLE_CUSTOMER"));
		// save user in the database
		userDao.save(user);
	}
    */

}
