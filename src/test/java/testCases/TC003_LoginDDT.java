package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pagaeObjects.HomePage;
import pagaeObjects.LoginPage;
import pagaeObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*

Data is valid - login success - test pass - logout
Data is valid - login failed - test fail

Data is invalid - login success - test fail - logout
Data is invalid -- login failed - test pass

*/
public class TC003_LoginDDT  extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven") // getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("**** Starting TC003_LoginDDT ******");
		
		try
		{
		// HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		// LoginPage		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
				
		// MyAccount 		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
			
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);	
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);	
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("**** Finished TC003_LoginDDT ******");
				
	}
	

}
