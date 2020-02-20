package Stockaccounting_hybrid_mvn_82.Stockaccounting_hybrid_mvn_82;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibrary {
	
	 public static WebDriver driver;
		
		
		public static  WebDriver startbrowser() throws Throwable{
			
			System.out.println(PropertiesfileUtils.getvalueforkey("Browser"));
			if(PropertiesfileUtils.getvalueforkey("Browser").equalsIgnoreCase("chrome")){
		
	        System.setProperty("webdriver.chrome.driver","D:\\akhil_82\\stockaccounting_hybrid\\drivers\\chromedriver.exe");
			
			driver=new ChromeDriver();
			System.out.println(driver);
		}else if(PropertiesfileUtils.getvalueforkey("Browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "D:\\Batch82\\StockAccounting_Hybrid\\drivers\\geckodriver.exe");
			 driver =new FirefoxDriver();
		}else{
			System.setProperty("webdriver.ie.driver", "D:\\Batch82\\StockAccounting_Hybrid\\drivers\\IEDriverServer.exe");
			 driver =new InternetExplorerDriver();
		}
			return driver;
	}
		
			
	   public static void  openapplication(WebDriver driver) throws Exception	{
	            driver.get(PropertiesfileUtils.getvalueforkey("Url"));
	            driver.manage().window().maximize();
				
	   }
	   
	   
	   public static void waitforelement(WebDriver driver,String locatortype,String locatorvalue, String test_data){
		   
		   WebDriverWait mywait= new WebDriverWait(driver,Integer.parseInt(test_data));
		   
		   if (locatortype.equalsIgnoreCase("id")) {
			   mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		   }
			   if (locatortype.equalsIgnoreCase("xpath")) {
				   mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));  
			   }
				if (locatortype.equalsIgnoreCase("name")) {
					
					mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));  	
				}else 
				{
					System.out.println("unable to locate for waitforElement method with"+locatortype);
					}	
			   
			   }
			   public static void  typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata){
				   if(locatortype.equalsIgnoreCase("id")){
				   driver.findElement(By.id(locatorvalue)).clear();
				   driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
				   
			   }
				   else if(locatortype.equalsIgnoreCase("name")){
					   driver.findElement(By.id(locatorvalue)).clear();
					   driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
					   
				   }
				   else if (locatortype.equalsIgnoreCase("xpath")){
					   driver.findElement(By.id(locatorvalue)).clear();
					   driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
				   
				   }else
				   {
					   System.out.println("unable to locate type action method with"+locatortype);
					   
					     }
	       }
			   public static void clickaction(WebDriver driver,String locatortype,String locatorvalue,String testdata){
				   
				if(locatortype.equalsIgnoreCase("id")){
					driver.findElement(By.id(locatorvalue)).click();
				}
				else if (locatortype.equalsIgnoreCase("name")){
					driver.findElement(By.id(locatorvalue)).click();
				}
				else if (locatortype.equalsIgnoreCase("xpath")){
					driver.findElement(By.id(locatorvalue)).click();
				}
				else{
					System.out.println("unable to locate the click action method");
				}
			 }
			    
				public static void tablevalidation(WebDriver driver,String column ) throws Exception{	
					FileReader fr = new FileReader("D:\\akhil_82\\stockaccounting_hybrid\\capturedata\\capture.txt");
					BufferedReader br = new BufferedReader(fr);
					String Exp_data=br.readLine();
					

					if(driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("searchtextbox"))).isDisplayed()){
						Thread.sleep(5000);
						driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("searchtextbox"))).sendKeys(Exp_data);
						Thread.sleep(3000);
						driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("searchbutton"))).click();
					}else{
						driver.findElement(By.xpath(PropertiesfileUtils.getvalueforkey("searchpanelbutton"))).click();
						Thread.sleep(5000);
						driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("searchtextbox"))).sendKeys(Exp_data);
						Thread.sleep(3000);
						driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("searchbutton"))).click();
					}
				
				WebElement table=driver.findElement(By.id(PropertiesfileUtils.getvalueforkey("suppliertable")));
				
				List<WebElement>rows=table.findElements(By.tagName("tr"));
				
				for(int i=1;i<rows.size();i++){
				       String act_data= driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column+"]/div/span")).getText();	
				       Assert.assertEquals(Exp_data, act_data); 
				       System.out.println(act_data+"   "+Exp_data);
				       break;
				}
				
			}
			
			public static void closeBrowser(WebDriver driver){
				driver.close();
			}
			
			public static String generateDate(){
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
				String requiredDate=sdf.format(d);
				return requiredDate;
			}
			
			
			
			public static void captureData(WebDriver driver,String locatortytpe,
					String locatorvalue) throws Exception{
				
				String supplierdata="";
				
				if(locatortytpe.equalsIgnoreCase("id")){
					supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
				}
				
				else if(locatortytpe.equalsIgnoreCase("xpath")){
					supplierdata=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
				}
				
				else if(locatortytpe.equalsIgnoreCase("name")){
					supplierdata=driver.findElement(By.name(locatorvalue)).getAttribute("value");
				}
				
				FileWriter fw=new FileWriter ("D:\\Batch82\\StockAccounting_Hybrid\\CaptureData\\suppnumber.txt");
				BufferedWriter bw=new BufferedWriter(fw);
				bw.write(supplierdata);
				bw.flush();
				bw.close();	
			}
			
						

}
