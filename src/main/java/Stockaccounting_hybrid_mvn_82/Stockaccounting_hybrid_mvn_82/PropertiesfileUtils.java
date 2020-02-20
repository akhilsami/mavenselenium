package Stockaccounting_hybrid_mvn_82.Stockaccounting_hybrid_mvn_82;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesfileUtils {
	
	public static String getvalueforkey(String Key) throws Exception{
		Properties p =new Properties();
		FileInputStream fis = new FileInputStream("D:\\akhil_82\\stockaccounting_hybrid\\propertiesfile\\Environment.properties");
		
		p.load(fis);
		return p.getProperty(Key);
		
	}

}
