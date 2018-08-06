package WalletApp._Phase2;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;

import com.cg.bean.Customer;
import com.cg.bean.Transaction;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;
import com.cg.repo.WalletRepoImpl;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest 
extends TestCase
{
/**
 * Create the test case
 *
 * @param testName name of the test case
 */
public AppTest( String testName )
{
    super( testName );
}

/**
 * @return the suite of tests being tested
 */
public static Test suite()
{
    return new TestSuite( AppTest.class );
}

/**
 * Rigourous Test :-)
 */
public void testApp()
{
    assertTrue( true );
}

private IWalletService service=null;

@Before
public void setUp() throws Exception {
	service = new WalletServiceImpl();
}


@org.junit.Test
public final void testCheckPhoneForCreateAccount() {
	Customer cust = new Customer();
	cust.setMobileNo("9876510");
	cust.setName("abc");
	Wallet wallet= new Wallet();
	wallet.setBalance(new BigDecimal(3434.34));
	try {
		assertNotNull(service.createAccount(cust));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



@org.junit.Test
public final void testAllValidForCreateAccount() {
	Customer cust = new Customer();
	cust.setMobileNo("9876543210");
	cust.setName("3454");
	Wallet wallet= new Wallet();
	wallet.setBalance(new BigDecimal(454.76));
	try {
		assertNotNull(service.createAccount(cust));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@org.junit.Test
public final void testPhoneShowBalance() {
	try {
		assertNotNull(service.showBal("566789"));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



@org.junit.Test
public final void testSourcePhoneFundTransfer() {
	BigDecimal amount = new BigDecimal(987.98);
	try {
		service.fundTransfer("9876", "1234567890", amount);
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		assertEquals("The mobile number must contain only 10 digits",
				e.getMessage());
	}
}

@org.junit.Test
public final void testTargetPhoneFundTransfer() {
	BigDecimal amount = new BigDecimal(987.98);
	try {
		assertNotNull(service.fundTransfer("1234567890", "9876", amount));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



@org.junit.Test
public final void testAllValidFundTransfer() {
	Customer cust = new Customer();
	cust.setMobileNo("1234");
	cust.setName("Shweta");
	Wallet wallet= new Wallet();
	wallet.setBalance(new BigDecimal(500.59));
	try {
		service.createAccount(cust);
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		assertEquals("The mobile number must contain only 10 digits",
				e.getMessage());
	}
}

@org.junit.Test
public final void testPhoneDepositAmount() {
	BigDecimal bigDecimal = new BigDecimal(9876.44);
	try {
		service.deposit("9877", bigDecimal);
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		assertEquals("The mobile number must contain only 10 digits",
				e.getMessage());
	}
}


@org.junit.Test
public final void testAllValidDepositAmount() {
	BigDecimal bigDecimal = new BigDecimal(12345.00);
	try {
		service.deposit("9876543211", bigDecimal);
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		assertEquals("Exhausted Resultset", e.getMessage());
	}
}

@org.junit.Test
public final void testPhoneWithdrawAmount()  {
	BigDecimal amount = new BigDecimal(12345.00);
	try {
		service.withdraw("98767", amount);
	} catch (WalletException e) {
		assertEquals("The mobile number must contain only 10 digits",
				e.getMessage());
		
	}
}

@org.junit.Test
public final void testAmountWithdrawAmount() {
	BigDecimal amount = new BigDecimal(0);
	try {
		assertNotNull(service.withdraw("9876543212", amount));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		assertEquals("The withdraw amount must be greater than 0.",e.getMessage());
	}
}

/*@org.junit.Test
public final void testWithdrawAmountIsSufficient() throws WalletException {
	BigDecimal amount = new BigDecimal(12345.00);
	if (amount.doubleValue() > service.showBal("9876543210").getBalance().doubleValue()) {
		fail("Cannot transfer fund");
	}
	fail("Cannot transfer fund");
}*/


}
