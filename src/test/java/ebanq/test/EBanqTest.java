package ebanq.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ebanq.pages.EBanqObjRepo;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EBanqTest{

	public WebDriver driver;
	public EBanqObjRepo objRepo;
	public FileInputStream fis;
	public Properties prop;
	public String welcomeMsg, amount, desc, transferSuccessMsg;
	
	@BeforeMethod
	public void initLogin() throws IOException, InterruptedException {
	
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
		prop = new Properties();
		prop.load(fis);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		objRepo = PageFactory.initElements(driver, EBanqObjRepo.class);
		objRepo.loginEBanq(prop.getProperty("userID"), prop.getProperty("password"));
		Thread.sleep(3000);
	}
	
	@Test(priority=2, description = "verifies the login with welcome message")
	public void verifyWelcomeMsg(){
		welcomeMsg = prop.getProperty("welcomeMessage");
		System.out.println("Expected Welcome Message = " +welcomeMsg);
		System.out.println("Actual welcome message = " +objRepo.returnWelcomeMessageAndUserName());
		Assert.assertTrue(objRepo.returnWelcomeMessageAndUserName().contains(welcomeMsg));
		
	}
	
	@Test(priority=3, description = "verifies the amount transfer successfully with success message")
	public void verifySuccessMsg() throws InterruptedException{
		amount = prop.getProperty("amount");
		desc = prop.getProperty("description");
		transferSuccessMsg = prop.getProperty("transferSuccess");
		Thread.sleep(3000);
		objRepo.navigate_TransfersPage();
		objRepo.navigateToTransfersBetweenAccounts();
		objRepo.fillTheTransfersBeweenAccountForm(amount, desc + " " + amount);
		objRepo.clickOnContinue();
		objRepo.clickConfirm();
		System.out.println("Actual success message = " +objRepo.getSuccessMsg());
		Assert.assertTrue(objRepo.getSuccessMsg().contains(transferSuccessMsg));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
