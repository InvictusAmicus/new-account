package com.qa.mock;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.domain.Account;
import com.qa.looseCouple.AccountService;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Default
public class DBService  implements AccountService
{

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	@Transactional(TxType.REQUIRED)
	public String createAnAccount(String account)
	{
		Account acc = util.getObjectForJSON(account, Account.class);
		manager.persist(acc);
		return "{\"message\": \"account has been sucessfully added\"}";
		
	}

	@Transactional(TxType.REQUIRED)
	public String updateAnAccount(String accountNumber, String accountToUpdate)
	{
		Account updatedAcc = util.getObjectForJSON(accountToUpdate, Account.class);
		Account currentAcc = findAnAccount(accountNumber);
		if (accountToUpdate != null) 
		{
			currentAcc = updatedAcc;
			currentAcc.setFirstName(updatedAcc.getFirstName());
			currentAcc.setSecondName(updatedAcc.getSecondName());
		}
		return "{\"message\": \"account sucessfully updated\"}";
//		return "";
		
	}

	@Transactional(TxType.SUPPORTS)
	public Account findAnAccount(String accNo) 
	{
		// TODO Auto-generated method stub
		return manager.find(Account.class, accNo);
	}

	@Transactional(TxType.REQUIRED)
	public String deleteAnAccount(String accountNumber)
	{
		// TODO Auto-generated method stub
		Account accountInDB = findAnAccount(accountNumber);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	@Transactional(TxType.SUPPORTS)
	public String getAllAccounts() 
	{
		// TODO Auto-generated method stub
		Query query = manager.createQuery("select a FROM Account a");
		Collection<Account> list =  query.getResultList();
		return util.getJSONForObject(list);
	}

}
