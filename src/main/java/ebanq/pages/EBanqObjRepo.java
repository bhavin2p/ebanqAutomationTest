package ebanq.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EBanqObjRepo {
	WebDriver driver;

	@FindBy(css = "input[type='email']")
	WebElement userName;

	@FindBy(css = "input[type='password']")
	WebElement password;

	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	WebElement signInBtn;

	@FindBy(xpath = "//div[@class='greeting']/span")
	WebElement welcomeMsg;

	@FindBy(xpath = "//div/label[contains(text(),'Transfers')]")
	WebElement menuTransfer;

	@FindBy(xpath = "//div[text()='Transfer Between Accounts']")
	WebElement transferBtnAccounts;

	@FindBy(xpath = "//div[contains(text(),'Select a user')]/following-sibling::div/input")
	WebElement selectUserDropDown;

	@FindBy(xpath = "//div[@class='ng-option ng-option-marked']/span[contains(text(),'mjohnson')]")
	WebElement selectUser;

	@FindBy(xpath = "//app-account-select[@ng-reflect-select-label='Debit from *']/div//div")
	WebElement debitFromDropDown;

	@FindBy(xpath = "//div[@class='ng-option ng-option-marked']")
	WebElement debitAcc;

	@FindBy(xpath = "//app-account-select[@ng-reflect-select-label='Credit to *']/div//div[@class='ng-select-container']")
	WebElement creditToDropDown;

	@FindBy(xpath = "//div/span[contains(text(),'Checking')]")
	WebElement creditAcc;
	
	@FindBy(css = "input[formcontrolname='outgoingAmount']")
	WebElement amountField;

	@FindBy(css = "#description")
	WebElement descriptionField;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement continueBtn;

	@FindBy(xpath = "//button[contains(text(),'Confirm')]")
	WebElement confirmBtn;
	
	@FindBy(xpath = "//div[contains(text(),'Transfer has been executed successfully.')]")
	WebElement confirmationMsg;

	
	/**
	 * @param driver
	 */
	public EBanqObjRepo(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param userId
	 */
	public void userNameTxt(String userId) {
		userName.sendKeys(userId);
	}
	
	/**
	 * @param pwd
	 */
	public void passwordTxt(String pwd) {
		password.sendKeys(pwd);
	}
	
	/*To login application by username and password
	 * @param userId
	 * @param pwd
	 */
	public void loginEBanq(String userId, String pwd) {
		userNameTxt(userId);
		passwordTxt(pwd);
		signInBtn.click();
	}
	

	
	/**
	 * To navigate to Transfers menu 
	 */
	public void navigate_TransfersPage() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(menuTransfer));
		menuTransfer.click();
	}
	
	/**
	 * Returns the welcome message with username
	 * @return
	 */
	public String returnWelcomeMessageAndUserName() {
		return welcomeMsg.getText();
	}

	
	/**
	 * To navigate on Transfers between accounts page
	 */
	public void navigateToTransfersBetweenAccounts() {
		transferBtnAccounts.click();
	}
	
	
	/**
	 * Fill the details on Transfers Between account form
	 * @param amount
	 * @param description
	 */
	public void fillTheTransfersBeweenAccountForm(String amount, String description){
		selectUserDropDown.sendKeys("mjohnson");
		//Thread.sleep(2000);
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(selectUser));
		selectUser.click();
		debitFromDropDown.click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(debitAcc));
		debitAcc.click();
		
		creditToDropDown.click();
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(creditAcc));
		creditAcc.click();
		
		amountField.sendKeys(amount);
		
		descriptionField.sendKeys(description);
		
	}
	
	
	/**
	 * to click on Continue button
	 */
	public void clickOnContinue() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
	}
	
	/**
	 * to click on Confirm button
	 */
	public void clickConfirm() {
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(confirmBtn));
		confirmBtn.click();
	}
	
	/**
	 * To Retrieve success message
	 */
	public String getSuccessMsg() {
		
		return confirmationMsg.getText();
	}
	
}
