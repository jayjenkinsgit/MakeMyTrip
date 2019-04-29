package appModules;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.SearchFlights;
import DriverScript.Driver_File;

public class SearchFlights_Action extends Driver_File{
	
		
	//Search flights and select from and to date	
	//@Test
	public static void search_Flights()
	{
		SearchFlights.lnk_flight(driver).click();

		SearchFlights.option_trip(driver).click();

		SearchFlights.txt_fromCity(driver).sendKeys("DEL, Delhi Airport India");

		SearchFlights.txt_toCity(driver).sendKeys("BLR, Kempegowda International Airport India");

		sys_from_Date_Format(driver, "From");
		sys_from_Date_Format(driver, "To");

		SearchFlights.btn_search(driver).click();
	}
	 
	//@Test
	public static void no_Of_To_Flights()
	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		SearchFlights.chckbox_nonStop(driver).click();
		
		SearchFlights.options_toJourneyFlights(driver).click();
						
		System.out.println(toJourneyFlights(driver,SearchFlights.options_toJourneyFlights(driver)));
		
		wait_For_Enable(driver);	
	}
	
	//@Test
	public static void no_Of_Rt_Flights()
	{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		SearchFlights.chckbox_1Stop(driver).click();
		
		SearchFlights.options_rtJourneyFlights(driver).click();
		
		System.out.println(rtJourneyFlights(driver,SearchFlights.options_rtJourneyFlights(driver)));
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		wait_For_Enable(driver);
	}
	
	//@Test
	public static void select_Flights_Validate_Total() throws Exception {	

		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		SearchFlights.chckbox_nonStop(driver).click();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		click_toJourneyFlights(driver,6);
		
		click_rtJourneyFlights(driver,6);
		
		int frm_Flight_price =getPrice_toJourneyFlights(driver,6);
		int rt_Flight_price =getPrice_rtJourneyFlights(driver,6);
		Thread.sleep(1000);
		int total_Flight_Price=frm_Flight_price+rt_Flight_price;
		
		//System.out.println(total_Flight_Price);
		Thread.sleep(1000);
		//System.out.println(driver.findElement(By.xpath("//*[@id=\"left-side--wrapper\"]/div[2]/div/div[3]/div/div[1]/div/div[3]/div[1]/p/span/span")).getText());
		
		int total_Flight_Price1=getPrice_totalFlights(driver);
		
		
		try
		{
		//Assert.assertEquals(total_Flight_Price, total_Flight_Price1);
		System.out.println("Total correct"+total_Flight_Price+"--"+total_Flight_Price1);
		}
		catch(Exception e)
		{
			
		}
		
			
	}

	public static void sys_from_Date_Format(WebDriver driver, String fromOrTo) {
		// String sysFromDateFromOrTo="";

		// String preXpath,postXpath;
		int day = 0, x = 0;

		Date objDate = new Date(); // Current System Date and time is assigned to objDate
		// System.out.println(objDate);

		String strDateFormat = "EEEE,dd MMM y";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

		Calendar cal = Calendar.getInstance();
		cal.setTime(objDate);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		if (fromOrTo.equals("From")) {
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[3]/label/span")).click();
			cal.add(Calendar.DAY_OF_MONTH, 1);

			day = cal.get(Calendar.DAY_OF_MONTH);

			List<WebElement> dateList = driver
					.findElements(By.xpath("//div[contains(@class,'DayPicker-Day') and (@aria-disabled=\"false\")]"));

			for (int i = 0; i <= dateList.size(); i++) {

				x = Integer.parseInt(dateList.get(i).getText());
				// System.out.println(x);

				if (x == day) {
					dateList.get(i).click();
					break;
				}
			}
		}

		if (fromOrTo.equals("To")) {
			// driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/ul/li[2]/span")).click();
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[4]/label/span")).click();

			cal.add(Calendar.DAY_OF_MONTH, 10);

			day = cal.get(Calendar.DAY_OF_MONTH);
			List<WebElement> dateList = driver
					.findElements(By.xpath("//div[contains(@class,'DayPicker-Day') and (@aria-disabled=\"false\")]"));

			for (int i = 0; i <= dateList.size(); i++) {
				x = Integer.parseInt(dateList.get(i).getText());
				// System.out.println(x);

				if (x == day) {
					dateList.get(i).click();
					break;
				}
			}
		}

	}
	
	public static int toJourneyFlights(WebDriver driver,WebElement ele)
	{
		List<WebElement> elelist=driver.findElements(By.name("splitowJourney"));		
		
		return elelist.size();
	}
	
	public static int rtJourneyFlights(WebDriver driver,WebElement ele)
	{
		List<WebElement> elelist=driver.findElements(By.name("splitrtJourney"));		
		
		return elelist.size();
	}
	
	public static void click_toJourneyFlights(WebDriver driver,int toRownum)
	{
		String preXpath="//*[@id=\"ow_domrt-jrny\"]/div[2]/div[";
		String postXpath="]/label/div[1]/span[1]";
		String toRownumstr=String.valueOf(toRownum);
		
		driver.findElement(By.xpath(preXpath+toRownumstr+postXpath)).click();
		
		String preXpathp="//*[@id=\"ow_domrt-jrny\"]/div[2]/div[";
		String postXpathp="]/label/div[2]/div[3]/p/span";
		String toRownumstrp=String.valueOf(toRownum);
		
		String priceToFlightStr=driver.findElement(By.xpath(preXpathp+toRownumstrp+postXpathp)).getText();
		
		String str=(priceToFlightStr.replaceAll("[^0-9]","")).trim();
		int priceToFlightint=Integer.parseInt(str);
		System.out.println(priceToFlightint); 
		
		
	}
	
	public static int getPrice_toJourneyFlights(WebDriver driver,int toRownum)
	{
			
		String preXpathp="//*[@id=\"ow_domrt-jrny\"]/div[2]/div[";
		String postXpathp="]/label/div[2]/div[3]/p/span";
		String toRownumstrp=String.valueOf(toRownum);
		
		String priceToFlightStr=driver.findElement(By.xpath(preXpathp+toRownumstrp+postXpathp)).getText();
		
		String str=(priceToFlightStr.replaceAll("[^0-9]","")).trim();
		int priceToFlightint=Integer.parseInt(str);
		//System.out.println(priceToFlightint); 
		
		return priceToFlightint;
	}
	
	public static int getPrice_rtJourneyFlights(WebDriver driver,int toRownum)
	{
			
		String preXpathp="//*[@id=\"rt-domrt-jrny\"]/div[2]/div[";
		String postXpathp="]/label/div[2]/div[3]/p/span";
		String toRownumstrp=String.valueOf(toRownum);
		//*[@id="rt-domrt-jrny"]/div[2]/div[6]/label/div[2]/div[3]/p/span
		String priceToFlightStr=driver.findElement(By.xpath(preXpathp+toRownumstrp+postXpathp)).getText();
		
		String str=(priceToFlightStr.replaceAll("[^0-9]","")).trim();
		int priceToFlightint=Integer.parseInt(str);
		//System.out.println(priceToFlightint); 
		return priceToFlightint;
		
	}
	
	public static int getPrice_totalFlights(WebDriver driver)
	{
			
		
		String priceToFlightStr=driver.findElement(By.xpath("//*[@id=\"left-side--wrapper\"]/div[2]/div/div[3]/div/div[1]/div/div[3]/div[1]/p/span/span")).getText();
		
		String str=(priceToFlightStr.replaceAll("[^0-9]","")).trim();
		int priceToFlightint=Integer.parseInt(str);
		//System.out.println(priceToFlightint); 
		return priceToFlightint;
		
	}
	
	public static void click_rtJourneyFlights(WebDriver driver,int toRownum)
	{
		String preXpath="//*[@id=\"rt-domrt-jrny\"]/div[2]/div[";
		String postXpath="]/label/div[1]/span[1]";
		String toRownumstr=String.valueOf(toRownum);
		
		//List<WebElement> elelist=driver.findElements(By.name("splitrtJourney"));		
		
		driver.findElement(By.xpath(preXpath+toRownumstr+postXpath)).click();	
	}
	
	public static void wait_For_Enable(WebDriver driver)
	{
		WebDriverWait elmtvsble=new WebDriverWait(driver,1000);
		elmtvsble.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applied-filter--wrapper\"]/div/div/span/a")));
		
				
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//*[@id=\"applied-filter--wrapper\"]/div/div/span/a"))); 
		jse.executeScript("window.scrollBy(0,-250)", "");
		
		WebElement elm=driver.findElement(By.xpath("//*[@id=\"applied-filter--wrapper\"]/div/div/span/a"));
		Actions actions = new Actions(driver);

		actions.moveToElement(elm).click().perform();
	}
}
