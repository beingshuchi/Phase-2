package WalletApp._Phase2;

import java.math.BigDecimal;

import java.util.List;


import org.junit.*;

import com.cg.bean.Customer;

import com.cg.bean.Transactions;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
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
    
    static IWalletService walletService;
    @Before
	public void init()  {
		walletService = new WalletServiceImpl();
	
	}

    @org.junit.Test
	public void testPhoneCreateAcc() {
		Customer cust = new Customer();
		cust.setMobileNo("1234");
		cust.setName("Shweta");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(300));
		/*Deposit d= new Deposit();
		d.setDate(Date.valueOf(LocalDate.now()));
		d.setCredit(new BigDecimal(400));
		List<Deposit> deposit = new ArrayList<Deposit>();
		deposit.add(d);
		Withdraw w= new Withdraw();
		w.setDate(Date.valueOf(LocalDate.now()));
		w.setDebit(new BigDecimal(400));
		List<Withdraw> withdraw = new ArrayList<Withdraw>();
		withdraw.add(w);
		Transactions trans= new Transactions(wallet,deposit,withdraw);
		List<Transactions> transaction= new ArrayList<Transactions>();
		transaction.add(trans);
		wallet.setTransaction(transaction);*/
		cust.setWallet(wallet);
		
		try {
			walletService.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}
    }
    
    @org.junit.Test
	public void testPhoneDoesNotExistCreateAcc() {
		
		
		Customer cust = new Customer();
		cust.setMobileNo("9546123011");
		cust.setName("Shweta");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(300));
		cust.setWallet(wallet);
		
		try {
			walletService.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}
    @org.junit.Test
	public void testNameCreateAcc() {
    	Customer cust = new Customer();
		cust.setMobileNo("9123872300");
		cust.setName("Shital45");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(300));
		cust.setWallet(wallet);
		
		try {
			walletService.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals(
					"The name should contain only alphabets,minimum of 2 and maximum of 12",
					e.getMessage());
		}

	}
    @org.junit.Test
	public void testBalCreateAcc() {
    	Customer cust = new Customer();
		cust.setMobileNo("9123872300");
		cust.setName("Shital");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(-23));
		cust.setWallet(wallet);
		try {
			walletService.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The balance cannot be less than or zero",
					e.getMessage());
		}
	}

    @org.junit.Test
	public void testNameEmptyCreateAcc() {
    	Customer cust = new Customer();
		cust.setMobileNo("9123872300");
		cust.setName("Shital45");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(-23));
		cust.setWallet(wallet);
		try {
			walletService.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The name cannot be left empty.", e.getMessage());
		}

	}

	@org.junit.Test
	public void testCreateAcc() {
		Customer cust = new Customer();
		cust.setMobileNo("9124872300");
		cust.setName("Shweta");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(230));
		cust.setWallet(wallet);
		
		try {
			Customer s = walletService.createAccount(cust);
			assertNotNull(s);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@org.junit.Test
	public void testPhoneShowBal() {

		try {
			walletService.showBal("93547");
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}
	@org.junit.Test
	public void testPhoneDoesNotExistShowBal() {

		try {
			walletService.showBal("9358877700");
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}
	@org.junit.Test
	public void testShowBal() {

		try {
			Wallet bal = walletService.showBal("9876545005");
			assertNotNull(bal.getBalance());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
	@org.junit.Test
	public void testPhonDeposit() {
		try {
			walletService.deposit("12345", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}

	@org.junit.Test
	public void testPhoneDoesNotExistDeposit() {
		try {
			walletService.deposit("9546123011", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}

	@org.junit.Test
	public void testAmtNegDeposit() {

		try {
			walletService.deposit("9876545005", new BigDecimal(-1));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The deposited amount must be greater than 0.",
					e.getMessage());
		}
	}

	@org.junit.Test
	public void testDeposit() {

		try {
			Customer cust = walletService.deposit("9876545005", new BigDecimal(300));
			assertNotNull(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@org.junit.Test
	public void testPhonWithdraw() {
		try {
			walletService.withdraw("12345", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}

	@org.junit.Test
	public void testPhoneDoesNotExistWithdraw() {
		try {
			walletService.withdraw("9546123011", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}

	@org.junit.Test
	public void testAmtNegWithdraw() {

		try {
			walletService.withdraw("9876545005", new BigDecimal(-1));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The amount to be withdrawn must be less than the"
					+ " available balance and greater than 0.", e.getMessage());
		}
	}

	@org.junit.Test
	public void testAmtWithdraw() {

		try {
			walletService.withdraw("9876545005", new BigDecimal(500));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The amount to be withdrawn must be less than the"
					+ " available balance and greater than 0.", e.getMessage());
		}
	}

	@org.junit.Test
	public void testWithdraw() {

		try {
			Customer cust = walletService.deposit("9876545005", new BigDecimal(300));
			assertNotNull(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@org.junit.Test
	public void testPhone1FundTransfer1() {
		try {
			walletService.fundTransfer("12345","9876545005", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}
	@org.junit.Test
	public void testPhoneFundTransfer2() {
		try {
			walletService.fundTransfer("9876545005","12345", new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}

	/*@Test
	public void testPhoneDoesNotExistFundTransfer1() {
		try {
			walletService.fundTransfer("9546123011","9876545005",new BigDecimal(300));
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}
	}*/
	
	@org.junit.Test
	public void testPhoneDoesNotExistFundTransfer2() {
		try {
			walletService.fundTransfer("9876545005","9546123011",new BigDecimal(300));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}
	
	

	@org.junit.Test
	public void testAmtFundTransfer() {

		try {
			walletService.fundTransfer("9542120221","9876545005", new BigDecimal(5000));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The amount to be withdrawn must be less than the"
					+ " available balance and greater than 0.", e.getMessage());
		}
	}
	
	@org.junit.Test
	public void testAmtNegFundTranfer() {

		try {
			walletService.fundTransfer("9542120221","9876545005", new BigDecimal(-1));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The deposited amount must be greater than 0.",
					e.getMessage());
		}
	}
	
	@org.junit.Test
	public void testFundTransfer() {

		try {
			boolean b = walletService.fundTransfer("9876545005","9542120221", new BigDecimal(300));
			assertTrue(b);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@org.junit.Test
	public void testPhonePrintTransaction() {
		String mobileNum="1234";
		try {
			walletService.printTransactionDetails(mobileNum);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits",
					e.getMessage());
		}

	}

	@org.junit.Test
	public void testPhoneDoesNotExistPrintTrans() {
		String mobileNum="9546123011";
		try {
			walletService.printTransactionDetails(mobileNum);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The entered mobile number does not exists.",
					e.getMessage());
		}

	}
	@org.junit.Test
	public void testPrintTransacrion() {

		try {
			List<Transactions> trans= walletService.printTransactionDetails("9876545005");
			assertNotNull(trans);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	

}
