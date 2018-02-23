package com.qa.mock;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;

import com.qa.business.BlockAccounts;
import com.qa.domain.Account;
import com.qa.looseCouple.AccountService;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
@Transactional(TxType.SUPPORTS)
public class MapService  implements AccountService
{
	
	private static final Logger LOGGER = Logger.getLogger(MapService.class);
	private Map<Integer, Account> hashmap = new HashMap<>();
	private static int count = 0;
	
	@Override
	public String getAllAccounts() 
	{
		
		LOGGER.info("MapService / getAllAccounts / map.values ----- " + hashmap.values().toString());
		JSONUtil util = new JSONUtil();
		return util.getJSONForObject(hashmap.values());
	}

	@Override
	public Account findAnAccount(String acc) 
	{
		LOGGER.info("MapService / findAnAccount / acc ----- " + acc);
		Account account;
		for(int i = 1; i < hashmap.size()+1; i++)
		{
			account = hashmap.get(i);
			LOGGER.info("MapService / findAnAccount / account ----- " + account);
			LOGGER.info("MapService / findAnAccount / account.accno ----- " + account.getAccountNumber());
			if(account.getAccountNumber().equals(acc))
			{
				LOGGER.info("MapService / findKey / i ----- " + i);	
				return account;
			}
		}
		return null;
	}
	
	public int findKey(String acc)
	{
		LOGGER.info("MapService / findKey / acc ----- " + acc);
		JSONUtil util = new JSONUtil();
		//Account accno = util.getObjectForJSON(acc, Account.class);
		Account account;
		for(int i = 1; i < hashmap.size()+1; i++)
		{
			
			account = hashmap.get(i);

			LOGGER.info("MapService / findKey / account ----- " + account);
			LOGGER.info("MapService / findKey / account.getno ----- " + account.getAccountNumber());
			
			if(account.getAccountNumber().equals(acc))//.getAccountNumber()))
			{
				LOGGER.info("MapService / findKey / i ----- " + i);
				return i;
			}
		}
		return -1;
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public String createAnAccount(String acc) 
	{
		
		BlockAccounts blockCheck = new BlockAccounts();
		LOGGER.info("MapService / createAnAccount / acc ----- " + acc);
		count++;
		JSONUtil util = new JSONUtil();
		Account account = util.getObjectForJSON(acc, Account.class);
		if(blockCheck.isBlocked(account.getAccountNumber()) == false)
		{
			LOGGER.info("MapService / createAnAccount / account ----- " + account);
			hashmap.put(count, account);
			return "{\"message\": \"Successful Insertion\"}";
		}
		else
		{
			return "{\"message\": \"Account is blocked\"}";
		}
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public String updateAnAccount(String account, String accountNumber) 
	{

		LOGGER.info("MapService / updateAnAccount / account ----- " + account);
		LOGGER.info("MapService / updateAnAccount / accountNumber ----- " + accountNumber);
		//Account acc;
		JSONUtil util = new JSONUtil();
		//acc = findAnAccount(accountNumber);
		int key = findKey(accountNumber);

		LOGGER.info("MapService / updateAnAccount / key ----- " + key);
		//acc = util.getObjectForJSON(account, Account.class);
		//hashmap.replace(key, acc);
		Account newAccount = util.getObjectForJSON(account, Account.class);
		LOGGER.info("MapService / updateAnAccount / newAcc ----- " + newAccount);
		hashmap.put(key, newAccount);
		return "{\"message\": \"Successful Updation\"}";
	}

	@Override
	@Transactional(TxType.REQUIRED)
	public String deleteAnAccount(String accountNumber) 
	{
		int key = findKey(accountNumber);
		
		hashmap.remove(key);
		return "{\"message\": \"Successful Deletion\"}";
	}
	
}
