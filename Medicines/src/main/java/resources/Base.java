package resources;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver initialiseDriver() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Reach\\Medicines\\src\\main\\java\\resources\\data.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenshot(String img, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\test-output\\screenshots" + img + ".png";
		File destination = new File(filePath);
		FileUtils.copyFile(src, destination);
		System.out.println("The image has been added");
	
		
	}
	

	}

	

