package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import DataProvider.DataGenerator;

public class GacdwInfoPage extends DataGenerator{
	WebDriver driver;
	
	public void SysregInfopage(WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy(how = How.XPATH, using = "//h2[@class='ibm-alternate']")
	WebElement OStitle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[1]/td[1]")
	WebElement systemID1;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"ibm-content-main\"]/div[7]/div/table/tbody/tr[2]/td[1]")
	WebElement systemID2;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[3]/td[1]")
	WebElement systemID3;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[4]/td[1]")
	WebElement systemID4;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[5]/td[1]")
	WebElement systemID5;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[6]/td[1]")
	WebElement systemID6;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[7]/td[1]")
	WebElement systemID7;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[8]/td[1]")
	WebElement systemID8;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[9]/td[1]")
	WebElement systemID9;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[1]/td[4]")
	WebElement getAppLogicalName1;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[2]/td[4]")
	WebElement getAppLogicalName2;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[3]/td[4]")
	WebElement getAppLogicalName3;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[4]/td[4]")
	WebElement getAppLogicalName4;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[5]/td[4]")
	WebElement getAppLogicalName5;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[6]/td[4]")
	WebElement getAppLogicalName6;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[7]/td[4]")
	WebElement getAppLogicalName7;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[8]/td[4]")
	WebElement getAppLogicalName8;
	@FindBy(how = How.XPATH, using = "//*[@id='ibm-content-main']/div[7]/div/table/tbody/tr[9]/td[4]")
	WebElement getAppLogicalName9;
	
	
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-1']")
	WebElement getLink1;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-3']")
	WebElement getLink2;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-5']")
	WebElement getLink3;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-7']")
	WebElement getLink4;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-9']")
	WebElement getLink5;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-11']")
	WebElement getLink6;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-13']")
	WebElement getLink7;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-15']")
	WebElement getLink8;
	@FindBy(how = How.XPATH, using = "//a[@id='ibm-tooltip-17']")
	WebElement getLink9;
	public WebElement getOSTitle() {
		return OStitle;
	}
	public WebElement getSystemID1() {
		return systemID1;
	}
	public WebElement getSystemID2() {
		return systemID2;
	}
	public WebElement getSystemID3() {
		return systemID3;
	}
	public WebElement getSystemID4() {
		return systemID4;
	}
	public WebElement getSystemID5() {
		return systemID5;
	}
	public WebElement getSystemID6() {
		return systemID6;
	}
	public WebElement getSystemID7() {
		return systemID7;
	}
	public WebElement getSystemID8() {
		return systemID8;
	}
	public WebElement getSystemID9() {
		return systemID9;
	}
	
	public WebElement getAppLogicalName1() {
		return getAppLogicalName1;
	}
	public WebElement getAppLogicalName2() {
		return getAppLogicalName2;
	}
	public WebElement getAppLogicalName3() {
		return getAppLogicalName3;
	}
	public WebElement getAppLogicalName4() {
		return getAppLogicalName4;
	}
	public WebElement getAppLogicalName5() {
		return getAppLogicalName5;
	}
	public WebElement getAppLogicalName6() {
		return getAppLogicalName6;
	}
	public WebElement getAppLogicalName7() {
		return getAppLogicalName7;
	}
	public WebElement getAppLogicalName8() {
		return getAppLogicalName8;
	}
	public WebElement getAppLogicalName9() {
		return getAppLogicalName9;
	}
	public WebElement getLink1() {
		return getLink1;
	}
	
	public WebElement getLink2() {
		return getLink2;
	}
	public WebElement getLink3() {
		return getLink3;
	}
	public WebElement getLink4() {
		return getLink4;
	}
	public WebElement getLink5() {
		return getLink5;
	}
	public WebElement getLink6() {
		return getLink6;
	}
	
	public WebElement getLink7() {
		return getLink7;
	}
	public WebElement getLink8() {
		return getLink8;
	}
	public WebElement getLink9() {
		return getLink9;
	}
	
}
