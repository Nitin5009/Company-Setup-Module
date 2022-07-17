package pageobjects;

import static reporting.ComplexReportFactory.getTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import utils.PropertiesLoader;
import utils.WebBasePage;

public class DesignationPage extends WebBasePage {
	
	

	WebDriver driver;
	String pattern = "yyMMddHHmmss";
	Date date = new Date();
	SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
	String datevalue = dateformat.format(date);
	
	static String DesignationName;
	
	static String Activestatus;
	static String InActivestatus;
	String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testfiles\\";
	private final static String FILE_NAME = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\testdata.properties";
	private static Properties prop = new PropertiesLoader(FILE_NAME).load();

	public DesignationPage(WebDriver driver) {
		super(driver, "Designation Page");
		this.driver = driver;	
	}

	
	public void clickFullMenuDropDown() {
		click(By.cssSelector("a#navbarDropdownPortfolio"), "Full Menu", 30);
		staticWait(2000);
	}

	public void clickCompanySetupLink() {
		click(By.xpath("//a[contains(text(),'COMPANY SETUP')]"), "Company Setup Link", 30);
		staticWait(2000);
	}

	public void clickCompanySetupPage() {
		click(By.xpath("//a[contains(text(),'Company Setup')]"), " Company Setup Page", 30);
		staticWait(2000);
	}

	public void clickDesignationTab() {
		click(By.xpath("//a[text()='Designation']"), "Designation Tab", 20);
		staticWait(2000);
	}
	
	
	public void AddButton() {
		clickByJavascript(By.id("ancCreateDesignation"), "Add Designation", 25);
	}

	public void Save() {
		clickByJavascript(By.id("btnSave"), "save button", 30);
		staticWait(2500);
	}
	
	
	public void VerifyMandatoryFieldValidation() {

		int i = 0;

		String actualText;
		String expectedText;

		List<WebElement> errorMessageLocator = findMultipleElement(
				By.xpath("//div[@class='modal-content']//span[contains(@class,'invalid-feedback')]"), 20);
		String[] expectedValue = { "Designation Name"};
		for (Object expected : expectedValue) {
			WebElement asterikField = findElementVisibility(By.xpath("//label[text()='" + expected + ":']/span"), 20);

			if (asterikField != null) {
				getTest().log(LogStatus.PASS, "The Asterisk symbol is displayed for" + expected + " field");
				logger.info("The Asterisk symbol is displayed for " + expected + " field");

			} else {
				getTest().log(LogStatus.FAIL, "The Asterisk symbol is not displayed for" + expected + " field");
				logger.info("The Asterisk symbol is not displayed for " + expected + " field");

			}

			List<WebElement> expectedElements = errorMessageLocator;
			for (WebElement element : expectedElements) {
				i++;
				actualText = element.getText();
				expectedText = expected.toString();

				if (actualText.indexOf(expectedText) != -1) {
					getTest().log(LogStatus.PASS,
							"Error message for \"" + expected + "\" field is displayed as expected");
					logger.info("Error message for \"" + expected + "\" field is displayed as expected");
					i = 0;
					break;
				} else if (i == expectedValue.length && !element.getText().contains(expectedText)) {
					getTest().log(LogStatus.FAIL, "Error message for \"" + expected + "\" field is not displayed");
					logger.info("Error message for \"" + expected + "\" field is displayed as expected");
					takeScreenshot(expectedText);
				}
			}

		}
	}
	
	public void cancelButton() {
		clickByJavascript(By.id("ancgotolistscreen"), " Cancel Button", 25);
	}

	
	public void openAndCloseUserGuide() {
		click(By.xpath("//span[@class='user-guide']/a[@class='ancuserguide']"), "Open User Guide", 45);
		click(By.xpath("//span[@class='user-guide']/a[@class='ancuserguide']"), "Close User Guide", 45);
	}
	
	
	 public void enterDesignationName()
	 {
		 
		 DesignationName= prop.getProperty("designationname")+datevalue;
		 enter(By.id("DesignationName"), DesignationName, "Designation Name", 25);
	 }
	 
	 public void designationDescription()
	 {
		String  description = prop.getProperty("Description");
		enter(By.id("DesignationDescription"), description, "Designation Description", 25);
		 
	 }

	 
	 
	 public void SearchDesignation()
		{
			enter(By.id("search"), DesignationName, "Search Field ", 25);
		}
		
		public void searchButton()
		{
			clickByJavascript(By.id("Go"), " Search Button", 25);
			
			verifySearchRecord(By.xpath("//a[@id='ancEditdesignation']"), DesignationName, "Designation Search", 25);
		}
		
		
		

		public void refresh()
		{
			clickByJavascript(By.xpath("//i[@class='fa fa-refresh']")," Refresh Button", 25);
		}
		
		
		public void editDesignation()
		{
			clickByJavascript(By.id("ancEditdesignation"), "Edit Designation", 25);
		}
		
		public void selectcheckbox()
		{
			clickByJavascript(By.xpath("//input[@id='chk_1']"), "Click on checkbox", 25);
		}
		
		public void selectAll()
		{
			clickByJavascript(By.xpath("//input[@id='chkAll_0']"), "Select All", 25);
		}

		public void activeActionButton()
		 {
			 clickByJavascript(By.xpath("//a[@id='ancActInact']//span[contains(text(),' Active')]"),"Active Action Button", 20);
			 String activeText = getText(By.xpath("//a[@id='ancActInact']//span"),24);
			 Activestatus=activeText;
			 
			 
			 
		 }
		 
		 public void inactiveActionButton()
		 {
			 clickByJavascript(By.xpath("//a[@id='ancActInact']//span[contains(text(),'  Inactive')]"),"Inactive Action Button", 20);
		 String inactiveText = getText(By.xpath("//a[@id='ancActInact']//span"),24);
		 InActivestatus=inactiveText;
		 }
		 public void okConfirmButton()
		 {
			 click(By.xpath("//button[@title='OK']"),"ok Button", 20);
			 staticWait(2000);
			 driver.navigate().refresh();
				/*
				 *  WebElement
				 * statuselement
				 * =driver.findElement(By.xpath("//div[@class='cstm-drop-btn']/select")); Select
				 * select = new Select(statuselement); String selectedValue=
				 * select.getFirstSelectedOption().getText(); System.out.println(selectedValue);
				 */
		 }
		 
		 public void verifyActiveStatusValue()
		 {
			 verifySelectedValue(By.xpath("//div[@class='cstm-drop-btn']/select"), "Designation Status Value", prop.getProperty("activeStatus"), 20);
		 }
		 public void verifyInactiveStatusValue()
		 {
			 verifySelectedValue(By.xpath("//div[@class='cstm-drop-btn']/select"), "Designation Status Value", prop.getProperty("inactiveStatus"), 20);
		 }
		 
		 public void deleteButton()
		 { 
			 
			 click(By.id("DeleteMultiple"), "Delete Button",25);
			 
		 }
		
		
}
