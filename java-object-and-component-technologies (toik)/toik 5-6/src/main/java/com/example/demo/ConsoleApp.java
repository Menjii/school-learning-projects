package com.example.demo;

import com.example.demo.jdbc.impl.AccountServiceImp;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ConsoleApp {

	public static void main(String[] args) throws Exception {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountServiceImp accountServiceImp = (AccountServiceImp)appCtx.getBean("AccountServiceImp");
		List<Account> deliquentAccounts = accountServiceImp.findAll();
		for(Account a : deliquentAccounts) {
			System.out.println("Niesolidny klient: " + a.getAccountNo());
		}
	}

}
