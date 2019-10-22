/**
 * 
 */
package DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.runtime.options.LoggingOption.LoggerInfo;

/**
 * @author Nive
 *
 */
public class DataGenerator extends ConfigReader {

	// private List reports = new ArrayList<>();
	private List<Report> reports = new ArrayList<Report>();
	private Date dateID = new Date();

	private static String GACDWFileName;
	private static String GACDWAPPFileName;

	static GacdwInventory prodSystem7, prodSystem8, prodSystem9, transSystem7, transSystem8, transSystem9,
			deploySystem7, deploySystem8, deploySystem9;
	static GacdwApplication prodAppSystem7, prodAppSystem8, prodAppSystem9, transAppSystem7, transAppSystem8,
			transAppSystem9, deployAppSystem7, deployAppSystem8, deployAppSystem9;

	// static SysregInventory inv;

	private String[] GACDWFile;
	private String[] GACDWAppFile;

	public void generateGacdwReports() throws Exception {
		System.out.println("GACDW Generating Starts");

		GACDWFile = getRowFromCSV(GACDW_FILE).split(GACDW_Separator, -1);

		prodSystem7 = new GacdwInventory(GACDWFile, dateID, 1, 1, "SERVER");
		prodSystem8 = new GacdwInventory(GACDWFile, dateID, 1, 1, "NETWORK DEVICE");
		prodSystem9 = new GacdwInventory(GACDWFile, dateID, 1, 1, "STORAGE");

		transSystem7 = new GacdwInventory(GACDWFile, dateID, 2, 1, "SERVER");
		transSystem8 = new GacdwInventory(GACDWFile, dateID, 2, 1, "NETWORK DEVICE");
		transSystem9 = new GacdwInventory(GACDWFile, dateID, 2, 1, "STORAGE");

		deploySystem7 = new GacdwInventory(GACDWFile, dateID, 3, 1, "SERVER");
		deploySystem8 = new GacdwInventory(GACDWFile, dateID, 3, 1, "NETWORK DEVICE");
		deploySystem9 = new GacdwInventory(GACDWFile, dateID, 3, 1, "STORAGE");

		reports.add(prodSystem7);
		reports.add(prodSystem8);
		reports.add(prodSystem9);

		reports.add(transSystem7);
		reports.add(transSystem8);
		reports.add(transSystem9);

		reports.add(deploySystem7);
		reports.add(deploySystem8);
		reports.add(deploySystem9);

		GACDWAppFile = getRowFromCSV(GACDW_APP_FILE).split(GACDW_Separator, -1);

		prodAppSystem7 = new GacdwApplication(GACDWAppFile, prodSystem7, "A");
		prodAppSystem8 = new GacdwApplication(GACDWAppFile, prodSystem8, "A");
		prodAppSystem9 = new GacdwApplication(GACDWAppFile, prodSystem9, "A");

		transAppSystem7 = new GacdwApplication(GACDWAppFile, transSystem7, "A");
		transAppSystem8 = new GacdwApplication(GACDWAppFile, transSystem8, "A");
		transAppSystem9 = new GacdwApplication(GACDWAppFile, transSystem9, "A");

		deployAppSystem7 = new GacdwApplication(GACDWAppFile, deploySystem7, "A");
		deployAppSystem8 = new GacdwApplication(GACDWAppFile, deploySystem8, "A");
		deployAppSystem9 = new GacdwApplication(GACDWAppFile, deploySystem9, "A");

		reports.add(prodAppSystem7);
		reports.add(prodAppSystem8);
		reports.add(prodAppSystem9);

		reports.add(transAppSystem7);
		reports.add(transAppSystem8);
		reports.add(transAppSystem9);

		reports.add(deployAppSystem7);
		reports.add(deployAppSystem8);
		reports.add(deployAppSystem9);

	}

	public void createGacdwReport() {
		System.out.println("========Begin creating GACDW reports=============");

		boolean error = false;
		StringBuffer resultReport;
		StringBuffer GACDWReport = new StringBuffer();
		StringBuffer GACDWAppReport = new StringBuffer();
		String lineSep = System.getProperty("line.separator");

		for (Report item : reports) {
			if (item instanceof GacdwInventory) {
				resultReport = GACDWReport;
			} else {
				resultReport = GACDWAppReport;
			}
			for (int i = 0; i < item.getSize() - 1; i++) {
				resultReport.append(item.getField(i));
				resultReport.append(item.getSeparator());
			}

			resultReport.append(item.getField(item.getSize() - 1));
			resultReport.append(lineSep);
		}

		System.out.println("GACDW generated data:");
		System.out.println(GACDWReport.toString());

		System.out.println("GACDW APP generated data:");
		System.out.println(GACDWAppReport.toString());

		String GACDWGzName = execute("plink " + IP_ADDRESS + " -l " + ECM_SSH_Login + " -pw " + ECM_SSH_Password
				+ " \"ls -t /ecm/archive/gacdw | grep -i '^GACDW\\.' | head -n1\"");

		String GACDWAppGzName = execute("plink " + IP_ADDRESS + " -l " + ECM_SSH_Login + " -pw " + ECM_SSH_Password
				+ " \"ls -t /ecm/archive/gacdw | grep -i '^GACDW_APP' | head -n1\"");

		GACDWFileName = downloadInventoryGACDW(GACDWGzName);
		GACDWAPPFileName = downloadInventoryGACDW(GACDWAppGzName);

		GACDWAPPFileName = matchToInventoryGACDW(GACDWAPPFileName);

		error = error || !writeReportToFile(getGACDWFilePath("GACDW"), GACDWReport, true, "GACDW");
		error = error || !writeReportToFile(getGACDWFilePath("GACDW_APP"), GACDWAppReport, true, "GACDW APP");

		// logTestResult("Record GACDW reports in files complete", !error);
	}

	public String matchToInventoryGACDW(String oldFileName) {
		File fileFromECM = new File(Local_Folder_GACDW + File.separator + oldFileName);
		String newFileName = oldFileName.split("\\.")[0] + "." + GACDWFileName.split("\\.")[1] + ".csv";
		System.out.println("Old File Name : " + oldFileName);
		System.out.println("New File Name(Changed) : " + newFileName);
		File matchForInv = new File(Local_Folder_GACDW + File.separator + newFileName);
		fileFromECM.renameTo(matchForInv);
		return newFileName;
	}

	public String downloadInventoryGACDW(String inventoryGzName) {
		String CSVName = inventoryGzName.substring(0, inventoryGzName.indexOf(".gz"));
		execute("plink " + IP_ADDRESS + " -l " + ECM_SSH_Login + " -pw " + ECM_SSH_Password
				+ " \"gzip -dc </ecm/archive/gacdw/" + CSVName + ".gz> /home/" + ECM_SSH_Login + "/Documents/" + CSVName
				+ "\"");
		execute("pscp -pw " + ECM_SSH_Password + " " + ECM_SSH_Login + "@" + IP_ADDRESS + ":/home/" + ECM_SSH_Login
				+ "/Documents/" + CSVName + " \"" + Local_Folder_GACDW + "\"");
		execute("plink " + IP_ADDRESS + " -l " + ECM_SSH_Login + " -pw " + ECM_SSH_Password + " \"rm /home/"
				+ ECM_SSH_Login + "/Documents/" + CSVName + "\"");
		return CSVName;
	}

	public static String getGACDWFilePath(String fileType) {
		String fileName = new String();
		switch (fileType) {
		case "GACDW":
			fileName = GACDWFileName;
			break;
		case "GACDW_APP":
			fileName = GACDWAPPFileName;
			break;
		case "GACDW_IP":
			// fileName = SESDRIPFileName;
			break;
		}
		return Local_Folder_GACDW + File.separator + fileName;
	}

	public boolean writeReportToFile(String fileName, StringBuffer reportData, boolean append, String reportName) {
		String lineSep = System.getProperty("line.separator");
		writeDataToFile(fileName, reportData.toString(), append);
		if (checkDownloadFile(fileName) > 0) {
			System.out.println(reportName + " reports successfully written to the file" + lineSep + "Filename: "
					+ new File(fileName).getName() + lineSep + "Size: " + (checkDownloadFile(fileName) / 1024) + " kb");
		} else {
			System.out.println("Error while writing " + reportName + " report to file");
			return false;
		}
		return true;
	}

	private static String getRowFromCSV(String file) {

		String row = "";

	/*	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				row = line;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	*/	try {
		BufferedReader br;
		br= new BufferedReader(new FileReader(file));
		for (String line; (line = br.readLine()) != null;) {
			row = line;
			break;
		}
	}catch (Exception e) {
		e.printStackTrace();
	} 


		
		
		return row;
	}

	
	  
	 

}
