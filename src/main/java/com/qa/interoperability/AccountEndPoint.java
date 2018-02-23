package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qa.domain.Account;
import com.qa.looseCouple.AccountService;

@Path("/account")
public class AccountEndPoint
{
	@Inject
	private AccountService service;
	
	@Path("a")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getAllAccounts()
	{
		String s = service.getAllAccounts().toString();
		return s;
	}

	@Path("b/{accountNumber}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Account findAnAccount(@PathParam("accountNumber")String acc)
	{
		Account s = service.findAnAccount(acc);
		return s;
	}

	@Path("c")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String createAnAccount(String acc)
	{
		String s = service.createAnAccount(acc);
		return s;
	}
	
	@Path("d/{accountNumber}")
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String updateAnAccount(@PathParam("accountNumber")String accountNumber, String account)
	{
		String s = service.updateAnAccount(account, accountNumber);
		return s;
		
	}

	@Path("e/{accountNumber}")
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public String deleteAnAccount(@PathParam("accountNumber")String accountNumber)
	{
		String s = service.deleteAnAccount(accountNumber);
		return s;
	}
	
	
}
