package exersise1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class helionTest {

    public void waitForElementOnPageExists(final WebDriver selenium, final By selector) {
        (new WebDriverWait(selenium, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                return doesWebElementExist(arg0, selector);
            }
        });
    }
    public boolean doesWebElementExist(WebDriver driver, By selector) {
        try {
            driver.findElement(selector);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebDriver driver;

    @BeforeMethod //metoda uruchamiana przed Pierwsza metodą typu test
    @Parameters({ "seleniumHost", "seleniumPort", "browser", "url" })
    public void setUp(String seleniumHost, int seleniumPort, String browser, String url)
            throws MalformedURLException {
//        System.setProperty("webdriver.gecko.driver", "c:\\pliki\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "c:\\pliki\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "c:\\pliki\\MicrosoftWebDriver.exe");
//        System.setProperty("webdriver.ie.driver", "c:\\pliki\\IEDriverServer.exe");
//        driver = new FirefoxDriver();
//        driver = new EdgeDriver();
//        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

//      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
//      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());

        if (browser.equals("firefox")){
            DesiredCapabilities ffCaps = DesiredCapabilities.firefox();
            ffCaps.setCapability(CapabilityType.BROWSER_NAME, browser);
//            ffCaps.setCapability(CapabilityType.VERSION, "54.w10");
//            ffCaps.setCapability(CapabilityType.PLATFORM, "WIN10");
            driver = new RemoteWebDriver
                    (new URL("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),ffCaps);
        }
        else if (browser.equals("chrome")){
            DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
            chromeCaps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//            chromeCaps.setCapability(CapabilityType.VERSION, "59.w10");
            chromeCaps.setCapability(CapabilityType.PLATFORM, "WIN10");
            driver = new RemoteWebDriver
                    (new URL("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),chromeCaps);
        }
        else if (browser.equals("edge")) {
            DesiredCapabilities edgeCaps = DesiredCapabilities.edge();
            edgeCaps.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
//            chromeCaps.setCapability(CapabilityType.VERSION, "20.w10");
//            edgeCaps.setCapability(CapabilityType.PLATFORM, "WIN10");
            driver = new RemoteWebDriver(new URL
                    ("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),edgeCaps);
        }
        else if (browser.equals("ANDROID")) {
            DesiredCapabilities andrCaps = DesiredCapabilities.android();
            andrCaps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            andrCaps.setCapability("platformName", "Android-dev");
            andrCaps.setCapability("deviceName", "HT4BLJT02124");
            andrCaps.setCapability(CapabilityType.PLATFORM, "ANDROID");

            driver = new RemoteWebDriver(new URL
                    ("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),andrCaps);
        }
        else if (browser.equals("ANDROID-SDK")) {
            DesiredCapabilities andrCaps = DesiredCapabilities.android();
            andrCaps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            andrCaps.setCapability("platformName", "Android-sdk");
            andrCaps.setCapability("deviceName", "emulator-5554");
            andrCaps.setCapability(CapabilityType.PLATFORM, "ANDROID");

            driver = new RemoteWebDriver(new URL
                    ("http://"+seleniumHost+":"+seleniumPort+"/wd/hub"),andrCaps);
        }

     //   driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCaps);
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

//   @BeforeTest //metoda uruchamiana przed każdą metodą typu test
//    public void browserStart() {
//        driver.get("http://helion.pl");
//    }

    @AfterClass //metoda uruchamiana na zakończenie klasy (sprztamy po testach)
    public void tearDown() {
        driver.close();
    }

    @Test //metoda będąca testem-- test tytulu strony
    public void helionTitle() {
//        driver.get("http://helion.pl");
        String tytul = driver.getTitle();
        Assert.assertTrue(tytul.contains("Helion"), "testujemy tytul strony");
    }

    @Test //metoda będąca testem - test obecnosci logo
    public void helionLogo() {
//        driver.get("http://helion.pl");
        WebElement logo = driver.findElement(By.cssSelector("p.logo"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test //metoda będąca testem - test dostepnosci ksiazki
    public void helionSelenium() throws IOException, InterruptedException {
//        driver.get("http://helion.pl");
        //    WebElement search = driver.findElement(By.id("inputSearch"));
        WebElement search = driver.findElement(By.cssSelector("input#inputSearch"));
        search.sendKeys("selenium");
        WebElement searchButton = driver.findElement(By.cssSelector(".button"));
        searchButton.click();
        Thread.sleep(10000);
        List<WebElement> wyniki = driver.findElements(By.partialLinkText("Selenium"));
        out.println("ilosc ksiazek: " + wyniki.size());
        Assert.assertTrue(wyniki.size() > 0);
        wyniki.get(1).click();

        List<WebElement> tytul = driver.findElements(By.cssSelector(".title-group"));
        String tytulKsiazki = tytul.get(0).getText();
        Assert.assertTrue(tytulKsiazki.contains("Selenium"));
        out.println("znaleziony tytul: " + tytulKsiazki);
        try {
            Assert.assertTrue(tytulKsiazki.contains("selenium"));
            out.println("tytul ksiazki to: ");
        } catch (Throwable e) {
            out.println("UWAGA: cos jest nie tak");
        }
     //   waitForElementOnPageExists(driver, (By.className("cover")));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement okladka = wait.until(ExpectedConditions.elementToBeClickable(By.className("cover")));
    //    WebElement okladka = driver.findElement(By.className("cover"));
        okladka.click();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("c:\\test\\okladka1.png"));
    }
    //test sterowany danymi
    @DataProvider //zrodlo danych
    public Object[][] dataForSearchTest() {
        return new Object[][] {
                {"Selenium", 2}, {"Java", 21}, {"Kali", 4},
                {"Jenkins", 3}, {"JavaScript", 20}, {"Git", 10}
        };
    }

    @Test (dataProvider = "dataForSearchTest") //wykozystanie danych
    public void helionKsiazkiData(String tytul, int ilosc) throws InterruptedException {
        System.out.println("---------------- Rozpoczynamy nowy test ------------------- ");
        out.println("test dla tytulu: " + tytul + " oraz zalozonej ilosci: " + ilosc);
//        driver.get("http://helion.pl");
        WebElement search = driver.findElement(By.cssSelector("input#inputSearch"));
        search.sendKeys(tytul);
        WebElement searchButton = driver.findElement(By.cssSelector(".button"));
        searchButton.click();
        Thread.sleep(10000);
        List<WebElement> wyniki = driver.findElements(By.partialLinkText(tytul));
        out.println("ilosc ksiazek: " + wyniki.size());
        Assert.assertTrue(wyniki.size() == ilosc);
    }


}
