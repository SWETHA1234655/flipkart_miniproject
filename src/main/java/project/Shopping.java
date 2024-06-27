package project;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Shopping {
	public static WebDriver driver;
	String price1,price2,Total;
	String urlexpected,urlactual;
	int sum,cartamount;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver browserSetup()
	{
		htmlReporter=new ExtentSparkReporter("C:\\Users\\2318398\\eclipse-workspace\\Selenium_Mini\\HtmlExtentReport\\Report.html");//specify location of the report
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("Extent Report"); // TiTle of report
		htmlReporter.config().setReportName("Online Shopping Automation"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		driver=DriverSetup.webDriver();
		return driver;
	}
	
	public void excel() {
		
		ExcelDataFile.ExcelData();
	}
	
	public void openUrl(WebDriver driver) throws Exception {
		
		ExtentTest t1=extent.createTest("web page is open or not");
		
		//Opening, URL
		urlexpected="https://www.flipkart.com/";
		driver.get(urlexpected);
		
		//Check if the home page of the application is loaded
		urlactual = driver.getCurrentUrl();
		ExcelDataFile.setExcelValue(urlexpected,urlactual);
		if(urlactual.equals(urlexpected)) 
		{
			System.out.println("Web page is loaded");
		}
		else 
		{
			System.out.println("Web page is not loaded");
		}
		
		t1.info("verify webpage is open or not");
		t1.pass("webpage is opened successfully");
		
	}
	public void maximize(WebDriver driver) {
		//maximize window
		driver.manage().window().maximize();
	}
	public void search(WebDriver driver)throws Exception
	{
		ExtentTest t2=extent.createTest("Search the product");
		//search the product
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys(ExcelDataFile.Search);
		search.sendKeys(Keys.ENTER);
		
		t2.info("Search the product Homeappliances");
		t2.pass("Homeappliances products are searched successfully");
		
		screenShots(driver,"\\searchPage");
	}
	public void product1(WebDriver driver) throws Exception 
	{
		ExtentTest t3=extent.createTest("Click the first product and add to cart");
		
		List<WebElement> product1 = driver.findElements(By.xpath("//a[@class='VJA3rP']"));
		product1.get(0).click();
		
		//window handle
		Set<String> window1= driver.getWindowHandles();
		List<String> first = new ArrayList<String>(window1);
		driver.switchTo().window(first.get(1));
		
		//product 1 price
		price1 = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
		screenShots(driver,"\\price1");
		System.out.println("First product price:"+price1);
		
		//scroll and add to cart product1
		WebElement element1=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
        element1.click();
        
        screenShots(driver,"\\product1");
        driver.switchTo().window(first.get(1)).close();
		driver.switchTo().window(first.get(0));
		
        t3.info("Click the first product and add to cart");
		t3.pass("First product is clicked and added to cart successfully");
		
	}
	public void product2(WebDriver driver) throws Exception 
	{
		ExtentTest t4=extent.createTest("Click the second product and add to cart");
		
		List<WebElement> product2 = driver.findElements(By.xpath("//a[@class='VJA3rP']"));
		product2.get(1).click();
		
		//window handle
		Set<String> window2= driver.getWindowHandles();
		List<String> second = new ArrayList<String>(window2);
		driver.switchTo().window(second.get(1));
		
		//product 2 price
		price2 = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
		System.out.println("Second product price:"+price2);
		screenShots(driver,"\\price2");
		
		//scroll and add to cart product2
		WebElement element2=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element2);
        element2.click();
        
        screenShots(driver,"\\product2");
		t4.info("Click the Second product and add to cart");
		t4.pass("First product is clicked and added to cart successfully");
		
		//Total amount in cart
		Total=driver.findElement(By.xpath("//div[@class='PWd9A7 xvz6eC']")).getText();
		System.out.println("Total amount"+Total);
		screenShots(driver,"\\Cart");
	
	}
	
	public void cartTotal(WebDriver driver) throws Exception
	{
		
		price1 = price1.replace("₹", "");
		price2 = price2.replace("₹", "");
		
		price1 = price1.replace(",", "");
		price2 = price2.replace(",", "");
		
		int p1 = Integer.parseInt(price1);
		int p2 = Integer.parseInt(price2);
		
		//Adding price of product1 and product2
		sum = p1+p2;
		System.out.println("First product price+Second product price:"+sum);
		
		Total=Total.replace("₹", "");
		Total=Total.replace(",", "");
		cartamount=Integer.parseInt(Total);
		
		if(cartamount==sum)
		{
			System.out.println("Actual and Expected amount are correct");
		}
		else
		{
			System.out.println("Actual and Expected amount are incorrect");
		}
		
	}
	
	public void screenShots(WebDriver driver,String name) throws Exception {
			
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File src=ts.getScreenshotAs(OutputType.FILE);
			
			File tar=new File("C:\\Users\\2318398\\eclipse-workspace\\Selenium_Mini\\screenshots"+name+".png");
			
			FileUtils.copyFile(src, tar);
			
			
		}
	
	public static void extentClose() {
		extent.flush();
	}
	public void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
		

}
		