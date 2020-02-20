package Stockaccounting_hybrid_mvn_82.Stockaccounting_hybrid_mvn_82;



import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class AppTest {
  @Test
  public void f () throws Throwable {
	  
	   ExtentReports report;
	     ExtentTest test;
	     WebDriver driver = null;
	    
	
		
			
			Exelfileutilities exel = new Exelfileutilities();
			for (int i = 0; i <= exel.rowCount("MasterTestCases"); i++) {
				
				String moduleStatus="";
				
				if (exel.getData("masterTestCases", i, 2).equalsIgnoreCase("Y")) {
					
					String TCModule = exel.getData("MasterTestCases", i, 1);
					report= new ExtentReports("D:\\akhil_82\\Stockaccounting_hybrid_mvn_82\\Reports\\rr"+TCModule+FunctionLibrary.generateDate()+".html");
					test=report.startTest(TCModule);
					for (int j = 1; j < exel.rowCount(TCModule); j++) {
						
						String Description=exel.getData( TCModule,j,0);
						String Function_name=exel.getData(TCModule, j, 1);
						String locator_type=exel.getData(TCModule, j, 2);
						String locator_value=exel.getData(TCModule, j, 3);
						String Test_data=exel.getData(TCModule, j, 4);
						try{
							
							if(Function_name.equalsIgnoreCase("startbrowser")){
								driver = FunctionLibrary.startbrowser();
								test.log(LogStatus.INFO,Description);
								
							}else if (Function_name.equalsIgnoreCase("openapplication")){
								
								FunctionLibrary.openapplication(driver);
								test.log(LogStatus.INFO, Description);
							}
							else if (Function_name.equalsIgnoreCase("waitforelement")){
								 FunctionLibrary.waitforelement(driver,locator_type,locator_value,Test_data);
								test.log(LogStatus.INFO, Description);
						}
							
						
							else if (Function_name.equalsIgnoreCase("typeAction")){
								FunctionLibrary.typeAction(driver,locator_type,locator_value,Test_data);
	                              test.log(LogStatus.INFO, Description);
					}
							else if (Function_name.equalsIgnoreCase("clickaction")){
								FunctionLibrary.clickaction(driver,locator_type,locator_value,Test_data);
	                              test.log(LogStatus.INFO, Description);
					}
						
							else if (Function_name.equalsIgnoreCase("closebrowser")){
								FunctionLibrary.closeBrowser(driver);
	                              test.log(LogStatus.INFO, Description);
					}	
					
							else if (Function_name.equalsIgnoreCase("captureData")){
								FunctionLibrary.captureData(driver,locator_type,locator_value);
	                              test.log(LogStatus.INFO, Description);
					}
							
					
					     exel.setData(TCModule,j,5,"PASS");
					     moduleStatus="PASS";
					     test.log(LogStatus.PASS, Description);
					     
						}catch(Exception e){
							System.out.println("the exception is");
							e.printStackTrace();
							exel.setData(TCModule, j, 5, "FAIL");
							moduleStatus="FAIL";
							File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(srcFile, new File("D:\\akhil_82\\Stockaccounting_hybrid_mvn_82\\screnshots\\Screenshots\\"+Description+FunctionLibrary.generateDate()+".png"));
							
							
							test.log(LogStatus.FAIL, Description);
							test.log(LogStatus.INFO, test.addScreenCapture("D:\\akhil_82\\Stockaccounting_hybrid_mvn_82\\screnshots"+Description+FunctionLibrary.generateDate()+"png"));
							break;
						}		
					}
					
					if(moduleStatus.equalsIgnoreCase("PASS")){
						exel.setData("MasterTestCases", i, 3, "PASS");
					}else{
						exel.setData("MasterTestCases", i, 3, "FAIL");
					}
					
					report.endTest(test);
	               report.flush();
					
				}else{
					exel.setData("MasterTestCases", i, 3, "Not Executed");
				}
				

  }
		}
  }
