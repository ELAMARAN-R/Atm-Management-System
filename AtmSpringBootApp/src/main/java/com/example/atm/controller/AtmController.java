package com.example.atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm.entity.Account;
import com.example.atm.service.AccountService;

@RestController
@RequestMapping("/atm")
public class AtmController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@GetMapping("/get/{accountNumber}")
	public Account getAccountById(@PathVariable long accountNumber) {
		return accountService.getAccountById(accountNumber);
	}
	
	@GetMapping("get/all")
	public List<Account> getAccountByAll(){
		return accountService.getAccountByAll();
	}
	
	@PutMapping("/update/{accountNumber}")
	public Account updateAccount(@PathVariable long accountNumber, @RequestBody Account account) {
		return accountService.updateAccount(accountNumber, account);
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public String deleteAccount(@PathVariable long accountNumber) {
		accountService.deleteAccount(accountNumber);
		return "Account Deleted Successfully";
	}
	
	@PostMapping("/login")
		public String login(@RequestBody Account account) {
			Account acc = accountService.login(account.getAccountNumber(), account.getPin());
			return "Login Successfull for account: "+acc.getAccountNumber();
		}	
	@PostMapping("/withdraw")
	
	public String withdrawAmount(@RequestParam long accountNumber,@RequestParam int pin,@RequestParam double amount) {
		return accountService.withdrawAmount(accountNumber, pin, amount);
	}
	
	@PostMapping("/deposit")
	public String depositAmount(@RequestParam long accountNumber,@RequestParam int pin,@RequestParam double amount) {
		return accountService.depositAmount(accountNumber, pin, amount);
	}
	
	@GetMapping("/checkBalance")
	public String checkBalance(@RequestParam long accountNumber,@RequestParam int pin) {
		return accountService.checkBalance(accountNumber, pin);
	}
}