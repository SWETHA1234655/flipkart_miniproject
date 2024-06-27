package project;
import java.util.Scanner;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup
{
	public static WebDriver driver;
	public static String browser;
	public static WebDriver ChromeBrowser()
	{
		//WebDriverManager.chromedriver().setup();
		System.out.println("Loading..........");
		driver=new ChromeDriver();
		return driver;
	}
	public static WebDriver EdgeBrowser()
	{
		//WebDriverManager.edgedriver().setup();
		System.out.println("Loading..........");		
		driver = new EdgeDriver();
		return driver;
	}
	public static WebDriver webDriver() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Select the browser :");
		System.out.println("1.Chrome\n2.Edge");
		browser=sc.nextLine();
		if(browser.equalsIgnoreCase("Chrome")) {
			ChromeBrowser();
		}
		if(browser.equalsIgnoreCase("Edge")) {
			EdgeBrowser();
		}
		return driver;
	}

}
		
