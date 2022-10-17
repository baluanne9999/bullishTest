package utilities;

import apisteps.ApiUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CommonUtilities extends ApiUtils {

    public static WebDriver driver;
    public static Properties properties;
    public static String mini, medium, max;
    public static int minimumWait, mediumWait, maximumWait;

    String cwd = System.getProperty("user.dir");

    public CommonUtilities() {

        try {
            logger.info("Read property file");
            properties = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/config.properties");
            properties.load(ip);

            logger.info("Read wait times");
            mini = properties.getProperty("minimumWait");
            minimumWait = Integer.parseInt(mini);
            medium = properties.getProperty("mediumWait");
            mediumWait = Integer.parseInt(medium);
            max = properties.getProperty("maximumWait");
            maximumWait = Integer.parseInt(max);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("Unable to read the property file");
        }
    }

    /*** This function will open the browser and  open the application the url***/
    public void openBrowser() throws InterruptedException {

        logger.info("Open Browser");
        String browser = properties.getProperty("browserName");
        String url = properties.getProperty("url");
        String os = properties.getProperty("os");

        try {
            if (browser.equalsIgnoreCase("chrome")) {

                if (os.equalsIgnoreCase("mac")) {
                    System.setProperty("webdriver.chrome.driver", cwd + "/drivers/chromedriver");
                }
                if (os.equalsIgnoreCase("windows")) {
                    System.setProperty("webdriver.chrome.driver", cwd + "/drivers/chromedriver.exe");
                }

                ChromeOptions options = new ChromeOptions();
                //options.addArguments("headless");
                options.addArguments("--no-sandbox");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", cwd + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            logger.info("Unable to lauch the browser" +e.getMessage());
        }
        try {
            logger.info("launch the application");
            driver.get(url);
            driver.manage().window().maximize();
            waitForPageToLoad();

        } catch (Exception e) {
            logger.info("Unable to launch the application");
        }

    }

    /*** This function will wait until ui page loads max 30 sec***/
    public static void waitForPageToLoad() {

        try {
            logger.info("wait for page load");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            for (int count = 0; count < 10; count++) {
                String pageLoadStatus = jse.executeScript("return document.readyState").toString();
                if (pageLoadStatus.equalsIgnoreCase("complete")) {
                    break;
                } else {
                    Thread.sleep(3000);
                }
            }

        } catch (Exception e) {
            logger.info("Unable to perform wait for page load" + e.getMessage());
        }
    }


    /************** selenium generic utilities*******************/

    /******** This function will check web element is displaying or not and return boolean value*********/
    public boolean isDisplayElement(By locator, String elementName) {
        boolean isDisplayedMenu = false;
        try {
            logger.info("isDisplayElement");
            waitUtil(maximumWait);
            isDisplayedMenu = driver.findElement(locator).isDisplayed();
            return isDisplayedMenu;
        } catch (Exception e) {
            logger.info("Unable to perform isDisplayElement " + e.getMessage());
        }
        return isDisplayedMenu;
    }

    /************ This function will click on web element ************/

    public void clickElement(By locator, String elementName) {

        try {
            logger.info("clickElement");
            driver.findElement(locator).click();
            waitUtil(maximumWait);
        } catch (Exception e) {
            logger.info("Unable to perform clickElement " + e.getMessage());
        }
    }

    /************ This function will enter the value on text field ************/

    public void setInput(By locator, String inputValue, String elementName) {

        try {
            logger.info("enter value in input field");
            driver.findElement(locator).clear();
            waitUtil(maximumWait);
            driver.findElement(locator).sendKeys(inputValue);
            waitUtil(maximumWait);
        } catch (Exception e) {
            logger.info("Unable to perform setinput " + e.getMessage());
        }
    }

    /************ This function will read data from UI and returns String************/

    public String getText(By locator, String elementName) {

        String txt = null;
        try {
            logger.info("get text from a locator");
            txt = driver.findElement(locator).getText().trim();
            waitUtil(maximumWait);
            return txt;
        } catch (Exception e) {
            logger.info("unable to perform get text for a locator " + e.getMessage());
        }
        return txt;
    }

    /************ This function will wait for the web element as the given time in milli secounds ************/
    public void waitUtil(int milliSecounds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(milliSecounds));
    }

    /************ This function will close the browser ************/
    public void closeBrowser() {
        logger.info("Close browser");
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            logger.info("Unable to close the browser " + e.getMessage());
        }

    }

}
