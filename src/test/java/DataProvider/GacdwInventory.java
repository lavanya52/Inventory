/**
 * 
 */
package DataProvider;

import java.util.Date;
import java.util.Random;

/**
 * @author Nive
 *
 */
public class GacdwInventory extends ConfigReader implements Report {
	
	private String[] inventoryData;
	private Date dateID;
	private int systemStatus;
	private String customerID;
	private String hostName;
	private String IPAddress;
	private int systemID;

	public GacdwInventory(String[] inventoryLoad, Date dateID, int status, int scenario,String ReportedFamily) {
		
		this.inventoryData = inventoryLoad.clone();
		this.dateID=dateID;
		this.systemStatus = status;
		
		customerID = COUNTRY_ID + "_" + CUSTOMER_NAME;
		setField(1,customerID);
		setField(5, customerID);
		setField(8, customerID);
		
		setField(12, COUNTRY_ID);
		setField(13, getDate("yyyy-MM-dd"));
				
		setField(12, COUNTRY_ID);
		
		String ID = getDate(this.dateID, "MMddhhmm") + generateRandomString(2);
		hostName = HOSTNAME_PREFIX + ID;
		setField(17, hostName);
		
		setField(18, ReportedFamily);
		setField(19, ReportedFamily);
		
		this.IPAddress = String.valueOf((new Random()).nextInt(10)) + "." + String.valueOf((new Random()).nextInt(10)) + "." + String.valueOf((new Random()).nextInt(100)) + "." + String.valueOf((new Random()).nextInt(100));
		setField(31, IPAddress);
		
		switch(scenario){
	    case 1 :
	       setField(42,"Windows");		
	       setField(43,"2000");				
	       setField(79, "DISTRIBUTED");		
	       break; 
	    case 2 :
	    	setField(42,"Z/VM");
		    setField(43,"6.3.0.");  
		    setField(79, "MAINFRAME");
	       break; 
	       
	    case 3 :
	    	setField(42,"3COM_OS");
		    setField(43,"3.3.2.S168");
	    	setField(79, "");
	    	break;
	    	
	    case 4 :
	    	setField(42,"DS3400 FW");
		    setField(43,"7.35.66.0");
		    setField(79, "MAINFRAME");
	    	break;
		}
		
		this.systemID = (new Random()).nextInt(100000000);
		setField(53, String.valueOf(systemID));

		setField(55, System_Owner);
		setField(61, System_Owner);
		
	}

	public int getSystemStatus() {
		return systemStatus;
	}
	
	public String getCustomer() {
		return customerID;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public Date getInvDate() {
		return dateID;
	}
	
	public String getIPAddress(){
		return IPAddress;
	}
	public String getSystemID() {
		return String.valueOf(systemID);
	}
	
	public void setField(int index, String value) {
		inventoryData[index] = value ;
	}
	
	public String getField(int index) {
		return inventoryData[index];
	}
	
	public int getSize() {
		return inventoryData.length;
	}
	
	public String getSeparator() {
		return GACDW_Separator;
	}
}
