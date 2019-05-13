package DriverScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import appModules.SearchFlights_Action;



public class Driver_File {
	
	protected static WebDriver driver = null;
	
	@BeforeSuite
	public static void setup() throws Exception
	{
		
		 //This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
		//@BeforeMethod	
        //ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
        String path=System.getProperty("user.dir");
        
        System.out.println(path);
        System.out.println(System.getProperty("os.name"));
        
        //org.apache.commons.lang3.StringUtils.containsIgnoreCase("System.getProperty(\"os.name\"))", "windows");
        
        
        
        if ((System.getProperty("os.name")).toLowerCase().contains("windows"))
        {        
        System.setProperty("webdriver.chrome.driver", path+"\\resources\\chromedriver.exe");
        }
        
         
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\jakrishn\\Documents\\Jai\\Personal\\Selenium\\chromedriver_win32_72\\chromedriver.exe");
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        
        //driver = new ChromeDriver();
 
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        
        driver.get("https://www.makemytrip.com");
        driver.manage().deleteAllCookies();
        //driver.findElement(By.xpath("//a[contains(@class,'primaryBtn font24 latoBlack widgetSearchBtn')]")).click();
        ////driver.manage().deleteAllCookies(); 
        Thread.sleep(3000);
        
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        if(iframes.size()> 0)
        {
        	for(int i=0;i<iframes.size();i++)
        	{
        		if(iframes.get(i).getAttribute("id").compareTo("webklipper-publisher-widget-container-notification-close-div")==0)
        		{
        			driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
        			driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
        			driver.switchTo().defaultContent();
        		}
        	}
        }
       /* if(driver.findElement(By.name("webklipper-publisher-widget-container-notification-frame")).isDisplayed())
        {
        driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
		driver.switchTo().defaultContent();
        }*/
		
		//return driver;
        
	}
	
	//@Test	
	/*public void driver_main() throws Exception
	{
		// TODO Auto-generated method stub
		//Driver_File.beforeMethod();
		
		//SearchFlights_Action.search_Flights();
		//SearchFlights_Action.no_Of_To_Flights();
		//SearchFlights_Action.no_Of_Rt_Flights();
		//SearchFlights_Action.select_Flights_Validate_Total();

	}*/
	
	@Test(priority=0)
	public void verify_Search_Flights()
	{
		SearchFlights_Action.search_Flights();
	}
	
	@Test(priority=1)
	public void get_no_Of_To_Flights()
	{
		SearchFlights_Action.no_Of_To_Flights();
	}
	
	@Test(priority=2)
	public void get_no_Of_Rt_Flights()
	{
		SearchFlights_Action.no_Of_Rt_Flights();
	}
	
	@Test(priority=3)
	public void get_select_Flights_Validate_Total() throws Exception
	{
		SearchFlights_Action.select_Flights_Validate_Total();
	}
	
	@AfterSuite
	public void tear_Down()
	{
		driver.quit();
	}

}
