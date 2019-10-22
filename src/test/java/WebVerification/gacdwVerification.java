/**
 * 
 */
package WebVerification;

import org.testng.annotations.Test;

import DataProvider.DataGenerator;
import DataProvider.DataUploader;


/**
 * @author Nive
 *
 */
public class gacdwVerification {
	
DataGenerator dg = new DataGenerator();
	
	DataUploader du = new DataUploader();

	
	@Test(priority=1)
	public void generate() throws Exception
	{
		System.out.println("====Inside DataGenerator====");
		dg.generateGacdwReports();
		System.out.println("========Invnetory Data Created============");
	}
	
	@Test(priority= 2)
	public void create()
	{
		dg.createGacdwReport();
		System.out.println("========Inventory file Created============");
	}
	
	@Test(priority = 3)
	public void upload()
	{
		du.uploadGacdwReport(DataGenerator.getGACDWFilePath("GACDW"),DataGenerator.getGACDWFilePath("GACDW_APP"));
		System.out.println("========Inventory file uploaded===========");		
		
	}
	
	@Test(priority = 4)
	public void execute() throws Exception
	{
		du.executeGacdwScripts();
		System.out.println("========Inventory file executed===========");
		
	}

}
