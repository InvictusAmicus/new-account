package com.qa.business;

public class BlockAccounts 
{
	public boolean isBlocked(String accNo)
	{
		if(accNo.equals("9999"))
		{
			return true;
		}
		return false;
	}
}
