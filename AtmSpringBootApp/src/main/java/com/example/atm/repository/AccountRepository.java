package com.example.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.atm.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	@Query("Select a from Account a where a.accountNumber = :accountNumber and a.pin = :pin")
	Account login(@Param("accountNumber")Long accountNummber,@Param("pin") int pin);
	Account findByAccountNumberAndPin(long accountNumber,int pin);
}
