package com.example.demo.dao;

import com.example.demo.model.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAll() throws Exception;
}
