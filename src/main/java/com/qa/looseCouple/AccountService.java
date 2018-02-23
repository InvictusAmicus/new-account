package com.qa.looseCouple;

import com.qa.domain.Account;

public interface AccountService 
{
	public String getAllAccounts();
	
	public Account findAnAccount(String acc);
	
	public String createAnAccount(String acc);
	
	public String updateAnAccount(String accountNumber, String account);
	
	public String deleteAnAccount(String accountNumber);
}
