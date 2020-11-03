/*The coding challenge is:

Using an automated testing tool of their choice in a language of their choice (though JavaScript is preferred);
Navigate to https://www.medicines.org.uk/emc/browse-companies
For each page of the company browser
Capture details about the first, the third and the last company on the page. The details must include the company name, the logo and all contact information. Do not capture the information about the drugs related to that company
Store the logo as an image in a folder
Add the company details to an internal data structure. Include the filename of the image file
Output the internal data structure of the company details as a JSON or XML file.

All submissions need to made via GitHub, you should send the URL over when you are finished.

Let us know if you have any questions or needs any further clarification.*/

package testCases;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class ValidatePageBrowse extends Base {


	@BeforeTest
	public void initialize() throws IOException {
		driver = initialiseDriver();
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void getBrowsePages() throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;

		List<WebElement> browsePages = driver.findElements(By.xpath("//ul[@class='browse']/li"));
		
		for (int i = 1; i <= browsePages.size(); i++) {
			String str = "//ul[@class='browse']/li[" + i + "]";
			WebElement individualBrowsePage = driver.findElement(By.xpath(str));
			//System.out.println("individual element " + individualBrowsePage.getText());
			if (individualBrowsePage.isEnabled()) {
				individualBrowsePage.click();
				List<WebElement> listPageItems = driver.findElements(By.xpath("//a[@class='key']"));
				//System.out.println("Total no of inner links Available: " + listPageItems.size());
				for (int j = 0; j < 3; j++) {
					if (j == 0 && listPageItems.size() > 0) {
						listPageItems = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement firstcompany = listPageItems.get(0);
						firstcompany.click();
						Assert.assertTrue(true, driver.findElement(By.xpath("//div[@class='col-md-12 title']")).getText());
						File src = ts.getScreenshotAs(OutputType.FILE);
						try {
							FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\test-output\\screenshots1stElement.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						driver.navigate().back();
					}

					if (j == 1 && listPageItems.size() >= 3) {
						listPageItems = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement thirdcompany = listPageItems.get(2);
						thirdcompany.click();
						Assert.assertTrue(true, driver.findElement(By.xpath("//div[@class='col-md-12 title']")).getText());
						File src = ts.getScreenshotAs(OutputType.FILE);
						try {
							FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\test-output\\screenshots3rdElement.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						driver.navigate().back();
					}

					if (j == 2) {
						listPageItems = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement lastcompany = listPageItems.get(listPageItems.size() - 1);
						lastcompany.click();
						Assert.assertTrue(true, driver.findElement(By.xpath("//div[@class='col-md-12 title']")).getText());
						File src = ts.getScreenshotAs(OutputType.FILE);
						try {
							FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\test-output\\screenshotsLastElement.png"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						driver.navigate().back();
					}

				}

			}

		}

	}

	@AfterTest
	public void quiteSession() {
		driver.quit();
	}

}
