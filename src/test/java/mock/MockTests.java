package mock;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class MockTests
{
	//EntityManager em;
	
	@Mock
	DBService dbs;

	@Test
	public void addToDatabaseTest()
	{
		dbs.createAnAccount(new Account("Declan", "Cordial", "4"));
		dbs.createAnAccount(new Account("Kennedy", "Bowers", "5"));
		Assert.assertEquals(true, false);
	}

	@Test
	public void updateDatabaseTest()
	{
		dbs.updateAnAccount();
		Assert.assertEquals(dbs.findAnAccount("4").getLastName(), "Horlick");
	}

	@Test
	public void removeFromDatabaseTest()
	{
		dbs.deleteAccount("5");
		Assert.assertNull(dbs.findAnAccount("5"));
	}

	@Test
	public void readAllTest()
	{
		ArrayList<Account> expectedList = new ArrayList<>();
		expectedList.add(new Account("Declan", "Horlick", "4"));
		ArrayList<Account> list = dbs.getAllAccounts();
		Assert.assertEquals(expectedList,list);
		
	}
	
	@Test
	public void readOneTest()
	{
		Account expected = new Account("Declan", "Horlick", "4");
		Account acc = dbs.findAnAccount(expected.getAccountNumber());
		Assert.assertEquals("4", acc.getAccountNumber());
	}

}
