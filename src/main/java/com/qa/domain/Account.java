package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Account {

	private String firstName;
	private String secondName;
	@Id //@GeneratedValue(strategy = GenerationType.AUTO)
	private String accountNumber;

	public Account() {}
	
	public Account(String firstName, String secondName, String accountNumber) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountNumber = accountNumber;
	}

	public String toString()
	{
		return firstName + " " + secondName + " " + accountNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
