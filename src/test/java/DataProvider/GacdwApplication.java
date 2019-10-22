/**
 * 
 */
package DataProvider;

import java.util.Random;

/**
 * @author Nive
 *
 */
public class GacdwApplication extends ConfigReader implements Report{

	private String[] appData;
	private GacdwInventory inventoryData;
	private String instName;
	private String appName;
	private int appSubSystemID;
	
	public GacdwApplication(String[] appLoad, GacdwInventory inv, String HCMatch)
	{
		this.appData = appLoad.clone();
		this.inventoryData = inv;
		
		this.instName = CUSTOMER_NAME + InsName_PREFIX + generateRandomString(2);
		setField(2, instName);
		
		this.appName = CUSTOMER_NAME + AppName_PREFIX + generateRandomString(2);
		setField(3, appName);
		
		setField(4, App_Type[0]);
		
		switch (HCMatch) {
		case "A":
			setField(30, "APPLICATION");
			setField(31, "APPLICATION");
			break;
		case "I":
			setField(30, "INSTANCE");
			setField(31, "INSTANCE");
			break;
		case "AI":
			setField(30, "APPLICATION");
			setField(31, "INSTANCE");
			break;
		case "IA":
			setField(30, "INSTANCE");
			setField(31, "APPLICATION");
			break;	
		
		}
		
		this.appSubSystemID = (new Random()).nextInt(100000000);
		setField(33, getSubSystemID());
		
		setField(34, getDate(inventoryData.getInvDate(), "yyyy-MM-dd-hh.mm.ss.SSSSSS"));
		
		setField(40, inventoryData.getSystemID());
	}

	private String getSubSystemID() {
		return String.valueOf(appSubSystemID);
	}
	
	public void setField(int index, String value) {
		appData[index] = value ;
	}

	public String getField(int index) {
		return appData[index];
	}

	public int getSize() {
		return appData.length;
	}

	public String getSeparator() {
		return GACDW_Separator;
	}
}
