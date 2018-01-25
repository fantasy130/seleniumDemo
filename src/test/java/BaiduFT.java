import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BaiduFT {

    private WebDriver driver;



    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.gecko.driver","src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @Test
    public void indexSearch_searchWithBankName_officialSiteInSearchResult() throws Exception
    {
        By inputBox = By.id("kw");
        By searchButton = By.id("su");

        waitForElement(driver, 10, inputBox);
        waitForElement(driver, 10, searchButton);
        driver.findElement(inputBox).sendKeys("兴业银行");
        Thread.sleep(5000);
        driver.findElement(searchButton).click();
        Thread.sleep(5000);

    }


    public void waitForElement(WebDriver driver,int timeOut, final By by) {
        try {
            (new WebDriverWait(driver, timeOut)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(by);
                    return element.isDisplayed();
                }
            });


        } catch (TimeoutException e) {
            Assert.fail("超时L !! " + timeOut + " 秒之后还没找到元素 [" + by + "]", e);
        }
    }
}
