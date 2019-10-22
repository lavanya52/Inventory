package DataProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Nive
 *
 */
public class ConfigReader {

	private static Properties properties;
	private final static String propertyFilePath= "Data//config.properties" ;
	
	
	protected static final String Local_Folder_GACDW = getMainFolder() + File.separator +loadProperty("LocalFolderGacdw");
	
		
	protected static final String IP_ADDRESS = loadProperty("ServerIP");
	protected static final String COUNTRY_ID = loadProperty("CountryID");
	protected static final String CUSTOMER_NAME = loadProperty("CustomerName");
	
	protected static final String CSV_Quote = loadProperty("CSVQuote");
	protected static final String HOSTNAME_PREFIX = loadProperty("HostnamePrefix");
	protected static final String AppName_PREFIX = loadProperty("AppNamePrefix");
	protected static final String InsName_PREFIX = loadProperty("InsNamePrefix");
	protected static final String[] App_Type = loadProperty("ApplicationType").split(",");
	protected static final String System_Owner = loadProperty("SystemOwner");
	protected static final String Web_Login = loadProperty("WebUserName");
	protected static final String Web_Passwsord = loadProperty("WebPassword");
	protected static final String Web_Password = loadProperty("WebPassword");			
	protected static final String ECM_SSH_Login = loadProperty("ECMSSHLogin");
	protected static final String ECM_SSH_Password = loadProperty("ECMSSHPass");
		

	
	protected static final String GACDW_FILE = loadProperty("GACDWFile");
	protected static final String GACDW_APP_FILE = loadProperty("GACDWAppFile");
	protected static final String GACDW_Separator = loadProperty("GACDWSeparator");

	protected static final String SSH_COMMANDS_FILE = loadProperty("SSHCommandsFile");
	protected static final String SSH_OUTPUT_NAME = loadProperty("SSHOutputName");


	
	static {
		new File(Local_Folder_GACDW).mkdirs();
    }
	
	
	

	public static String loadProperty(String propName) {
		//System.out.println("==========Inside load property=============");
		Properties props = new Properties();
		String sysProp = System.getProperties().getProperty(propName);
		String value = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
			props.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		if (propName != null) {
			value = props.getProperty(propName);
		}
		
		if (sysProp != null) {
			value = System.getProperties().getProperty(propName);
		}
		
		//System.out.println(" Value is : "+value);
		return value;
	}
	
	/**
	* Executes shell commands.
	*/
	public static String execute(String command){
		String OSName = System.getProperty("os.name").toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] commands = new String[]{"/bin/bash", "-c", command};
        if (OSName.indexOf("win") >= 0) {
            commands[0] = "cmd";
            commands[1] = "/c";
        }
        try {
            Process proc = new ProcessBuilder(commands).start();
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                    InputStreamReader(proc.getErrorStream()));

            String s = null;
            while ((s = stdInput.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }

            while ((s = stdError.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(sb);
        return sb.toString();
    }
	
	public static String getDate(Date date, String format){
		SimpleDateFormat sdt = new SimpleDateFormat(format);
		return sdt.format(date).toString();
	}
	
	public static String getDate(String format){
		return getDate(new Date(), format);
	}
	
	public void writeDataToFile(String fileName, String data, boolean append) {
		/*try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
			writer.write(data);	
		} catch (Exception e) {  
			e.printStackTrace();
	    }*/
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append));
					
				writer.write(data);	
			
		}catch (Exception e) {  
			e.printStackTrace();
		}
	}
	
	/**
	* Method for validating downloaded file.
	* @return		file size in bytes.
	*/	
	public static long checkDownloadFile(String filePath) {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			return f.length();
		}
		return 0;
	}
	
	public static String generateRandomString(int length) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";
		characters = "abcdefghijklmnopqrstuvwxyz";
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}
	
	public static String getMainFolder() {
		String OSName = System.getProperty("os.name").toLowerCase();
        if (OSName.indexOf("win") >= 0) {
            return loadProperty("MainFolderWin");
        }
		return loadProperty("MainFolderLin");
	}
	
	public void cleanLocalFolder(String folder) {
		try {
			for (File file : (new File(folder)).listFiles()) {
				file.delete();
			}

			System.out.println("Successfully deleted all the files inside local_folder.");
		} catch (NullPointerException e) {
			System.out.println(folder + " did not exist. Created it newly.");
			System.err.println(folder + " did not exist. Created it newly.");
			new File(folder).mkdirs();
		}
	}

}
