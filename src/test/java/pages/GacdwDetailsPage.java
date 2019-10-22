package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import DataProvider.DataGenerator;

public class GacdwDetailsPage extends DataGenerator{

	WebDriver driver;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr[1]/td[1]")
	WebElement AppsystemID1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr/td[4]")
	WebElement Appname;
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr/td[5]")
	WebElement Insname;

	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr/td[6]")
	WebElement mtypeHCK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr/td[7]")
	WebElement mtypeIDM;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[1]/div[2]")
	WebElement CusName;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[3]/div[2]")
	WebElement logicalname;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[4]/div/table/tbody/tr/td[3]")
	WebElement Appcatg;
	
	
	
	
	public GacdwDetailsPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getAppsystemID1() {
		return AppsystemID1;
	}
	public WebElement getAppName() {
		return Appname;
	}
	public WebElement getInsName() {
		return Insname;
	}
	
	public WebElement getMtypeHCK() {
		return mtypeHCK;
	}
	
	public WebElement getCusName() {
		return CusName;
	}
	
	
	public WebElement getLogicalName() {
		return logicalname;
	}
	
	public WebElement getMtypeIDM() {
		return mtypeHCK;
	}
	
	public WebElement getAppcatg() {
		return Appcatg;
	}
	
	
}
