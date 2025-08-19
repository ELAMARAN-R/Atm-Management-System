package com.example.atm.service;

import java.util.List;

import com.example.atm.entity.Account;

public interface AccountService {
	Account createAccount(Account account);
	Account getAccountById(long accountNumber);
	List<Account> getAccountByAll();
	Account updateAccount(long accountNumber,Account updatedAccount);
	void deleteAccount(long accountNumber);
	Account login(long accountNumber,int pin);
	String withdrawAmount(long accountNumber,int pin,double amount);
	String depositAmount(long accountNumber,int pin,double amount);
	String checkBalance(long accountNumber,int pin);
}
