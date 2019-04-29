package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchFlights {
	
	static WebElement element = null;
	
	public static WebElement lnk_flight(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div/nav/ul/li[1]/a/span[2]"));       
		return element;

        }
	
	public static WebElement option_trip(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/ul/li[2]/span"));       
		return element;

        }

	public static WebElement txt_fromCity(WebDriver driver){
		 
        element = driver.findElement(By.id("fromCity"));       
		return element;

        }
	
	//DEL, Delhi Airport India
	
	public static WebElement txt_toCity(WebDriver driver){
		 
        element = driver.findElement(By.id("toCity"));       
		return element;

        }
	
	//BLR, Kempegowda International Airport India
	
	public static WebElement txt_frmDate(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div[1]/div[3]/label/span"));       
		return element;

        }
	
	public static WebElement txt_returnDate(WebDriver driver){
		 
        element = driver.findElement(By.id("return"));       
		return element;

        }
	
	
	public static WebElement btn_search(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//a[@class=\"primaryBtn font24 latoBlack widgetSearchBtn \"]"));       
		return element;

        }
	//*[@id="fli_filter__stops"]/span[1]/label/span[1]/span
	public static WebElement chckbox_nonStop(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"fli_filter__stops\"]/span[1]/label/span[2]"));       
		return element;

        }
	
	public static WebElement lnk_ClearFilter(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"applied-filter--wrapper\"]/div/div/span/a"));       
		return element;

        }
	
	
	public static WebElement chckbox_1Stop(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"fli_filter__stops\"]/span[2]/label/span[2]"));       
		return element;

        }
	
	public static WebElement options_toJourneyFlights(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"ow_domrt-jrny\"]/div[2]"));       
		return element;

        }
	
	public static WebElement options_rtJourneyFlights(WebDriver driver){
		 
        element = driver.findElement(By.xpath("//*[@id=\"rt-domrt-jrny\"]/div[2]"));       
		return element;

        }
	
	
	
	
}
