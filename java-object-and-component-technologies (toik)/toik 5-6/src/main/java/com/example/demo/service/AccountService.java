package com.example.demo.service;

import com.example.demo.dao.AccountDao;

import com.example.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    public AccountService() {}

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> fieldDeliquentAccounts() throws Exception {
        List<Account> deliquentAccounts = new ArrayList<>();
        List<Account> accounts = accountDao.findAll();

        Date thirtyDaysAgo = daysAgo(30);

        for(Account account : accounts) {
            boolean owesMoney = account.getBalance().compareTo(BigDecimal.ZERO) > 0;
            boolean thirtyDaysLate = account.getLastPaidOn().compareTo(thirtyDaysAgo) <= 0;

            if( owesMoney && thirtyDaysLate ) {
                deliquentAccounts.add(account);
            }
        }
        return deliquentAccounts;
    }

    private static Date daysAgo(int days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, -days);
        return gc.getTime();
    }
}
