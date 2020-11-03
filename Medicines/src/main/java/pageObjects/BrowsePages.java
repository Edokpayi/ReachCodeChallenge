package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowsePages {

	public WebDriver driver;
	//Screenshot sc;

	By anchorLinks = By.xpath("//ul[@class='browse']/li");

	public WebElement getHomeBrowsePage() {
		return driver.findElement(anchorLinks);

	}

	public void getAllBrowsePages() {
		//sc = new Screenshot();
		List<WebElement> anchors = driver.findElements(By.xpath("//ul[@class='browse']/li"));
		for (int i = 1; i <= anchors.size(); i++) {
			String str = "//ul[@class='browse']/li[" + i + "]";
			WebElement listItem = driver.findElement(By.xpath(str));
			System.out.println("individual element " + listItem.getText());
			if (listItem.isEnabled()) {
				listItem.click();
				List<WebElement> innerAnchors = driver.findElements(By.xpath("//a[@class='key']"));
				System.out.println("Total no of inner links Available: " + innerAnchors.size());

				for (int j = 0; j < 3; j++) {
					if (j == 0 && innerAnchors.size() > 0) {
						innerAnchors = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement firstcompany = innerAnchors.get(0);
						firstcompany.click();
						//sc.screenCapture(driver);
						driver.navigate().back();
						System.out.println("print 1");
					}

					if (j == 1 && innerAnchors.size() >= 3) {
						innerAnchors = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement thirdcompany = innerAnchors.get(2);
						thirdcompany.click();
						//sc.screenCapture(driver);
						driver.navigate().back();
						System.out.println("Print 3");
					}

					if (j == 2) {
						innerAnchors = driver.findElements(By.xpath("//a[@class='key']"));
						WebElement lastcompany = innerAnchors.get(innerAnchors.size() - 1);
						lastcompany.click();
						//sc.screenCapture(driver);
						driver.navigate().back();
						System.out.println("Print last");
					}

				}

			}
		}
	}
}
