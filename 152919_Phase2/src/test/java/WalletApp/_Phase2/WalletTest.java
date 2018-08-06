package WalletApp._Phase2;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Customer;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;
import com.cg.service.IWalletService;
import com.cg.service.WalletServiceImpl;

public class WalletTest {

	
	
	private IWalletService service=null;

	@Before
	public void init() throws WalletException {
		service = new WalletServiceImpl();
	}
	
	@Test
	public void checkMobileNoCreateAccount() {
		Customer cust = new Customer();
		cust.setMobileNo("900400");
		cust.setName("shalini");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(444));
		cust.setWallet(wallet);
		try {
			service.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number must contain only 10 digits", e.getMessage());
		}
	}
	@Test
	public void checkMobileNoEmptyCreateAccount() {
		Customer cust = new Customer();
		cust.setMobileNo("");
		cust.setName("shalini");
		Wallet wallet= new Wallet();
		wallet.setBalance(new BigDecimal(454));
		cust.setWallet(wallet);
		try {
			service.createAccount(cust);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("The mobile number cannot be left empty.", e.getMessage());
		}
	}
	 @Test
	 public void checkNameEmptyCreateAccount() {
			Customer cust = new Customer();
			cust.setMobileNo("9004009009");
			cust.setName("");
			Wallet wallet= new Wallet();
			wallet.setBalance(new BigDecimal(3434.34));
			cust.setWallet(wallet);
			try {
				service.createAccount(cust);
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				assertEquals("The name cannot be left empty.", e.getMessage());
			}
		 
	 }
	 
	 @Test
	 public void checkNameCreateAccount() {
			Customer cust = new Customer();
			cust.setMobileNo("9004009009");
			cust.setName("23shu");
			Wallet wallet= new Wallet();
			wallet.setBalance(new BigDecimal(454));
			cust.setWallet(wallet);
			try {
				service.createAccount(cust);
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				assertEquals("The name should contain only alphabets,minimum of 2 and maximum of 12", e.getMessage());
			}
		 
	 }

	 
	 @Test
	 public void checkWallet1CreateAccount() {
			Customer cust = new Customer();
			cust.setMobileNo("9004009009");
			cust.setName("shu");
			Wallet wallet= new Wallet();
			wallet.setBalance(new BigDecimal(-1));
			cust.setWallet(wallet);
			try {
				service.createAccount(cust);
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				assertEquals("The balance cannot be less than or zero", e.getMessage());
			}
		 
	 }
	 @Test
	 public void checkWallet2CreateAccount() {
			Customer cust = new Customer();
			cust.setMobileNo("9004009009");
			cust.setName("shu");
			Wallet wallet= new Wallet();
			wallet.setBalance(new BigDecimal(0));
			cust.setWallet(wallet);
			try {
				service.createAccount(cust);
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				assertEquals("The balance cannot be less than or zero", e.getMessage());
			}
	 }
	 
	 
			 @Test
			 public void validCreateAccount() {
				 Customer cust = new Customer();
					cust.setMobileNo("9008004004");
					cust.setName("abc");
					Wallet wallet= new Wallet();
					wallet.setBalance(new BigDecimal(576.8));
					cust.setWallet(wallet);
					try {
						assertNotNull(service.createAccount(cust));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 }

			 
			 @Test
			 public void phoneShowBal() {
				String mobile="9585";
					try {
						assertNotNull(service.showBal(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number must contain only 10 digits", e.getMessage());
					}
	 }
			 @Test
			 public void phoneEmptyShowBal() {
				String mobile="";
					try {
						assertNotNull(service.showBal(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number cannot be left empty.", e.getMessage());
					}
	 }
			 @Test
			 public void phoneNotExistShowBal() {
				String mobile="9008004004";
					try {
						assertNotNull(service.showBal(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The entered mobile number does not exists.", e.getMessage());
					}
	 }
			 
			 @Test
			 public void validShowBal() {
				String mobile="6457918002";
					try {
						assertNotNull(service.showBal(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 }
			 @Test
			 public void phoneDeposit() {
				 String mobile="9585";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.deposit(mobile, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number must contain only 10 digits", e.getMessage());
					}
	 }	 
			 @Test
			 public void phoneEmptyDeposit() {
				 String mobile="";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.deposit(mobile, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number cannot be left empty.", e.getMessage());
					}
	 }	 	 
			 @Test
			 public void phoneNotExistDeposit() {
				String mobile="9008004004";
				 BigDecimal amt=new BigDecimal(23);
					try {
						service.deposit(mobile, amt);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The entered mobile number does not exists.", e.getMessage());
					}
	 }
			 @Test
			 public void checkWallet1Deposit() {
				 String mobileNo="9004009009";
					BigDecimal d= new BigDecimal(-1);
					
					try {
						service.deposit(mobileNo, d);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The deposited amount must be greater than 0.", e.getMessage());
					}
				 
			 }
			 @Test
			 public void checkWallet2Deposit() {
					String mobileNo="9004009009";
					BigDecimal d= new BigDecimal(0);
					
					try {
						service.deposit(mobileNo, d);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The deposited amount must be greater than 0.", e.getMessage());
					}
			 }
			 @Test
			 public void validDeposit() {
				String mobile="6457918002";
				BigDecimal d= new BigDecimal(546);
					try {
						assertNotNull(service.deposit(mobile, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 }
			 
			 
			
			 
			 @Test
			 public void phoneFundTransfer() {
				 String mobile1="9585";
				 String mob2="6457918002";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number must contain only 10 digits", e.getMessage());
					}
	 }	 
			 @Test
			 public void phoneEmptyFundTransfer() {
				 String mobile1="";
				 String mob2="6457918002";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, amt));
					}catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number cannot be left empty.", e.getMessage());
					}
	 }	 	 
			
			 
			 @Test
			 public void checkWallet1FundTransfer() {
				 String mobile1="9008004004";
				 String mob2="6457918002";
					BigDecimal d= new BigDecimal(-1);
					
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be greater than 0.", e.getMessage());
					}
				 
			 }
			 @Test
			 public void checkWallet2FundTranfer() {
				 String mobile1="9008004004";
				 String mob2="6457918002";
					BigDecimal d= new BigDecimal(0);
					
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be greater than 0.", e.getMessage());
					}
			 }
			 
			 @Test
			 public void checkWallet3FundTranfer() {
				 String mobile1="9008004004";
				 String mob2="6457918002";
					BigDecimal d= new BigDecimal(45678);
					
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be less than available balance.", e.getMessage());
					}
			 }
			 @Test
			 public void validFundTranfer() {
				String mobile1="6457918002";
				 String mob2="9008004004";
				BigDecimal d= new BigDecimal(546);
					try {
						assertNotNull(service.fundTransfer(mobile1,mob2, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 }
			 
			 @Test
			 public void phoneWithdraw() {
				 String mobile="9585";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.withdraw(mobile, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number must contain only 10 digits", e.getMessage());
					}
	 }	 
			 @Test
			 public void phoneEmptyWithdraw() {
				 String mobile="";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.withdraw(mobile, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number cannot be left empty.", e.getMessage());
					}
	 }	 	 
			 @Test
			 public void phoneNotExistWithdraw() {
				String mobile="9008004004";
				 BigDecimal amt=new BigDecimal(23);
					try {
						assertNotNull(service.withdraw(mobile, amt));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The entered mobile number does not exists.", e.getMessage());
					}
	 }
			 
			 @Test
			 public void checkWallet1Withdraw() {
				 String mobileNo="9004009009";
					BigDecimal d= new BigDecimal(-1);
					
					try {
						service.withdraw(mobileNo, d);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be greater than 0.", e.getMessage());
					}
				 
			 }
			 @Test
			 public void checkWallet2Withdraw() {
					String mobileNo="9004009009";
					BigDecimal d= new BigDecimal(0);
					
					try {
						service.withdraw(mobileNo, d);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be greater than 0.", e.getMessage());
					}
			 }
			 
			 @Test
			 public void checkWallet3Withdraw() {
					String mobileNo="6457918002";
					BigDecimal d= new BigDecimal(45678);
					
					try {
						service.withdraw(mobileNo, d);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The withdraw amount must be less than available balance.", e.getMessage());
					}
			 }
			 @Test
			 public void validWithdaw() {
				String mobile="6457918002";
				BigDecimal d= new BigDecimal(546);
					try {
						assertNotNull(service.withdraw(mobile, d));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 }
			 @Test
			 public void phonePrintTransactions() {
				String mobile="9585";
					try {
						assertNotNull(service.printTransactionDetails(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number must contain only 10 digits", e.getMessage());
					}
	 }
			 @Test
			 public void phoneEmptyPrintTransaction() {
				String mobile="";
					try {
						assertNotNull(service.printTransactionDetails(mobile));
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						assertEquals("The mobile number cannot be left empty.", e.getMessage());
					}
	 }
			
			
			 
}
