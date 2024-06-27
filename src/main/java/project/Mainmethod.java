package project;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class Mainmethod {
	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Shopping method=new Shopping();
		driver=method.browserSetup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		method.excel();
		method.openUrl(driver);
		method.maximize(driver);
		method.search(driver);
		method.product1(driver);
		method.product2(driver);
		method.cartTotal(driver);
		Shopping.extentClose();
		method.closeBrowser(driver);
		

	}

}
