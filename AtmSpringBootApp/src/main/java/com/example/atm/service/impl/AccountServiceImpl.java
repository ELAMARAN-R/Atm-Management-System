package com.example.atm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.atm.entity.Account;
import com.example.atm.repository.AccountRepository;
import com.example.atm.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}	
	@Override
	public Account getAccountById(long accountNumber) {
		Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
		return optionalAccount.orElse(null);
	}
	
	@Override
	public List<Account> getAccountByAll(){
		return accountRepository.findAll();
	}
	
	@Override
	public Account updateAccount(long accountNumber,Account updatedAccount) {
		Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
		if(optionalAccount.isPresent()) {
			Account existingAccount = optionalAccount.get();
			existingAccount.setAccountHolderName(updatedAccount.getAccountHolderName());
			existingAccount.setBalance(updatedAccount.getBalance());
			return accountRepository.save(existingAccount);
		}
		return null;
	}
	
	@Override
	public void deleteAccount(long accountNumber) {
		accountRepository.deleteById(accountNumber);
	}
	
	@Override
	public Account login(long accountNumber,int pin) {
		Account account = accountRepository.login(accountNumber,pin);
		if(account != null) {
			return account;
		}
		else {
			throw new RuntimeException("Invalid Account Number or Pin");
		}
	}
	@Override
	public String withdrawAmount(long accountNumber,int pin,double amount)
	{
		Account account = accountRepository.findByAccountNumberAndPin(accountNumber,pin);
		if(account == null) {
			throw new RuntimeException("Invalid AccountNumber or Pin");
		}
		if(account.getBalance()<amount) {
			return "Insufficient Balance";
		}
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
		return "Withdrawal of Rs."+amount+" successfull and your New Balance is:"+account.getBalance();
	}
	
	@Override
	public String depositAmount(long accountNumber,int pin, double amount) {
		Account account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
		if(account != null) {
			double currentBalance = account.getBalance();
			account.setBalance(currentBalance + amount);
		accountRepository.save(account);
		return "Amount "+amount+" deposited Successfully and your New Account Balance is: "+account.getBalance();
		}
		else {
			return "Invalid AccountNumber or Pin";
		}
	}
	
	@Override
	public String checkBalance(long accountNumber,int pin) {
		Account account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
		if(account != null) {
			return "Your account Balance is: "+account.getBalance();
		}
		else {
			return "Invalid AccountNumber and Pin";
		}
	}
}
