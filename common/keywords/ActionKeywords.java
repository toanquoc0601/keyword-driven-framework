package keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import executionEngine.RunTestscript;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.Constants;
import utility.Log;

public class ActionKeywords {

	public static WebDriver driver;

	public static void openBrowser(String locator, String browser) {
		Log.info("Opening Browser");
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				Log.info("Firefox browser started");
			} else if (browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				Log.info("Edge browser started");
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				Log.info("Chrome browser started");
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (Exception e) {
			Log.info("Not able to open the Browser --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void navigate(String locator, String data) {
		try {
			Log.info("Navigating to URL");
			driver.get(Constants.URL);
		} catch (Exception e) {
			Log.info("Not able to navigate " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	public void backToPage(String locator, String data) {
		try {
			Log.info("Back to page");
			driver.navigate().back();
		} catch (Exception e) {
			Log.error("Not Back to page --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	public void forwardToPage(String locator, String data) {
		try {
			Log.info("Forward to page");
			driver.navigate().forward();
		} catch (Exception e) {
			Log.error("Not Forward to page --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	
	public void refreshToPage(String locator, String data) {
		try {
			Log.info("Refresh to page");
			driver.navigate().refresh();
		} catch (Exception e) {
			Log.error("Not Refresh to page --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void click(String locator, String data) {
		try {
			waitToElementClickable(driver, locator);
			Log.info("Clicking on WebElement " + locator);
			driver.findElement(By.xpath(locator)).click();
		} catch (Exception e) {
			Log.error("Not able to click --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	public static void hover(String locator, String data) {
		try {
			Log.info("Hovering on WebElement " + locator);
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(locator))).perform();
		} catch (Exception e) {
			Log.error("Not able to Hover --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void sendkeyToElement(String locator, String data) {
		try {
			Log.info("Entering the text in " + locator);
			waitToElementVisuble(driver, locator);
			driver.findElement(By.xpath(locator)).sendKeys(Keys.CONTROL + "a");
			driver.findElement(By.xpath(locator)).sendKeys(Keys.DELETE);
			driver.findElement(By.xpath(locator)).sendKeys(data);
		} catch (Exception e) {
			Log.error("Not able to Enter --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	
	public static void clearTextToElement(String locator, String data) {
		try {
			Log.info("Clearing the text in " + locator);
			driver.findElement(By.xpath(locator)).sendKeys(Keys.CONTROL + "a");
			driver.findElement(By.xpath(locator)).sendKeys(Keys.DELETE);
			driver.findElement(By.xpath(locator)).clear();;
		} catch (Exception e) {
			Log.error("Not able to Clear --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	
	public void clearTextToElement(String locator) {
		element = getElement(driver, locator);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}
	

	public static void sleep(String locator, String data) throws Exception {
		try {
			Log.info("Wait for "+data+" seconds");
			Thread.sleep(Integer.valueOf(data)*1000);
		} catch (Exception e) {
			Log.error("Not able to Wait --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void selectDropdownList(String locator, String data) throws Exception {
		try {
			waitToElementVisuble(driver, locator);
			Log.info("Select dropdown list");
			WebElement element = driver.findElement(By.xpath(locator));
			Select select = new Select(element);
			select.selectByVisibleText(data);
		} catch (Exception e) {
			Log.error("Can not select item in dropdown or not found item --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void isElementDisplayed(String locator, String data) {
		try {
			Log.info("Check WebElement is displayed " + locator);
			driver.findElement(By.xpath(locator)).isDisplayed();
		} catch (Exception e) {
			Log.error("Control not displayed --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void isElementSelected(String locator, String data) {
		try {
			Log.info("Check WebElement is displayed " + locator);
			driver.findElement(By.xpath(locator)).isSelected();
		} catch (Exception e) {
			Log.error("Control not displayed --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	
	public static void isElementEnable(String locator, String data) {
		try {
			Log.info("Check WebElement is enable " + locator);
			driver.findElement(By.xpath(locator)).isEnabled();
		} catch (Exception e) {
			Log.error("Control not enable --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}

	public static void verifyText(String locator, String expectedText) {
		try {
			Log.info("VerifyText True ---" + locator);
			 Assert.assertEquals(driver.findElement(By.xpath(locator)).getText(), expectedText);
		} catch (Exception e) {
			Log.error("VerifyText False --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
    }
	
	public static void closeBrowser(String locator, String data) {
		try {
			Log.info("Closing the browser");
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			RunTestscript.bResult = false;
		}
	}
	
	public static void waitToElementVisuble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	public static void waitToElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
	
	public WebElement element;
	public static WebDriverWait explicitWait;
}