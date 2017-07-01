package exersise1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MojPierwszyTest {

    WebDriver driver;

    @BeforeClass //metoda uruchamiana przed każdą metodą typu test
    public void setUp() {
        //    System.setProperty("webdriver.gecko.driver", "c:\\pliki\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "c:\\pliki\\chromedriver.exe");
        //    System.setProperty("webdriver.edge.driver", "c:\\pliki\\MicrosoftWebDriver.exe");
        //    System.setProperty("webdriver.ie.driver", "c:\\pliki\\IEDriverServer.exe");
        //    driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //    driver = new EdgeDriver();
        //   driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void getMetod() {
        driver.navigate().to("http://192.168.56.3");
    }

    @AfterTest //metoda uruchamiana po każdej metodzie typu test
    public void tearDown() {
        driver.quit();
    }

    @Test //metoda będąca testem
    public void startWebDriver() {
        // driver.navigate().to("http://192.168.1.8");
        Assert.assertTrue(driver.getTitle().startsWith("Selenium kurs"), "strona startuje z tytulem");
    }

    @Test //metoda będąca testem
    public void testDucha() {
        //    driver.get("http://192.168.1.8");
        //   WebElement logo = driver.findElement(By.cssSelector("img#myImage"));
        WebElement logo = driver.findElement(By.id("myImage"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test //metoda będąca testem
    public void testTextu() throws InterruptedException {
        //    driver.get("http://192.168.1.8");
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        WebElement imie = driver.findElement(By.id("Name"));
        imie.sendKeys("Ala");
        WebElement nazwisko = driver.findElement(By.id("Surname"));
        nazwisko.sendKeys("Kowalska");
        WebElement komentarz = driver.findElement(By.name("Komentarz"));
        komentarz.clear();
        komentarz.sendKeys("W tym formularzu to - to pole wymaga komentarza");
        String sprawdzamImie = imie.getAttribute("value");
        System.out.println("podane imie to: " + sprawdzamImie);
        Assert.assertTrue(sprawdzamImie.equals("Ala"));
        String sprawdzamNazwisko = nazwisko.getAttribute("value");
        System.out.println("podane nazwisko to: " + sprawdzamNazwisko);
        Assert.assertTrue(sprawdzamNazwisko.equals("Kowalska"));
        String sprawdzamKomentarz = komentarz.getAttribute("value");
        System.out.println("podany komentarz to: " + sprawdzamKomentarz);
        Assert.assertTrue(sprawdzamKomentarz.contains("to - to"));
    }

    @Test //metoda będąca testem
    public void testRadio() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        //wybieramy a nastepnie zaznaczamy przez klikniecie radio button Kobieta
        WebElement kobieta = driver.findElement(By.cssSelector("input[value='Kobieta']"));
        kobieta.click();
        Thread.sleep(10000);
        //pobieramy liste elementow typu plec (wszystkie radiobuttony)
        List<WebElement> plec = driver.findElements(By.cssSelector("input[name='Plec']"));
        // sprawdzamy jak duza jest tablica plec
        System.out.println("liczba radio buttonow - plec= " + plec.size());
        for (int i = 0; i < plec.size(); i++) {
            //szukamy elementu ktory jest zaznaczony
            if (plec.get(i).isSelected()) {  //sprawdzamy co jest zaznaczone
                String jakaPlec = plec.get(i).getAttribute("value");
                System.out.println("zaznaczony jest element: " + i
                        + " o wartosci: " + jakaPlec);
                Assert.assertTrue(jakaPlec.equals("Kobieta"));
            } else {
                String jakaPlec = plec.get(i).getAttribute("value");
                System.out.println("ten element nie jest zaznaczony: " + i
                        + " o wartosci: " + jakaPlec);
                Assert.assertFalse(jakaPlec.equals("Kobieta"));
            }
        }
    }

    @Test //metoda będąca testem
    public void testWieku() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        //wybieramy a nastepnie zaznaczamy przez klikniecie radio button 30 - 39
        WebElement wiek30 = driver.findElement(By.cssSelector("input[value='30-39']"));
        wiek30.click();
        Thread.sleep(10000);
        //pobieramy liste elementow typu Wiek (wszystkie radiobuttony)
        List<WebElement> wiek = driver.findElements(By.cssSelector("input[name='Wiek']"));
        // sprawdzamy jak duza jest tablica wiek
        System.out.println("liczba radio buttonow - wiek= " + wiek.size());
        for (int i = 0; i < wiek.size(); i++) {
            //szukamy elementu ktory jest zaznaczony
            if (wiek.get(i).isSelected()) {  //sprawdzamy co jest zaznaczone
                String jakiWiek = wiek.get(i).getAttribute("value");
                System.out.println("zaznaczony jest element: " + i
                        + " o wartosci: " + jakiWiek);
                Assert.assertTrue(jakiWiek.equals("30-39"));
            } else {
                String jakiWiek = wiek.get(i).getAttribute("value");
                System.out.println("ten element nie jest zaznaczony: " + i
                        + " o wartosci: " + jakiWiek);
                Assert.assertFalse(jakiWiek.equals("30-39"));
            }
        }
    }

    @Test //metoda będąca testem
    public void testMuzy() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        //wybieramy a nastepnie zaznaczamy przez klikniecie check boxy pop i classic
        WebElement pop = driver.findElement(By.cssSelector("input[value='Pop']"));
        pop.click();
        WebElement classic = driver.findElement(By.cssSelector("input[value='Muzyka powazna']"));
        classic.click();
        Thread.sleep(10000);
        //pobieramy liste elementow typu Muzyka (wszystkie check boxy)
        List<WebElement> muzyka = driver.findElements(By.cssSelector("input[name='Muzyka']"));
        // sprawdzamy jak duza jest tablica muzyka
        System.out.println("liczba check boxow - Muzyka= " + muzyka.size());
        for (int i = 0; i < muzyka.size(); i++) {
            //szukamy elementu ktory jest zaznaczony
            if (muzyka.get(i).isSelected()) {  //sprawdzamy co jest zaznaczone
                String jakaMuza = muzyka.get(i).getAttribute("value");
                System.out.println("zaznaczony jest element: " + i
                        + " o wartosci: " + jakaMuza);
                Assert.assertNotEquals(jakaMuza.equals("Pop"), jakaMuza.equals("Muzyka powazna"));
            } else {  //wypisujemy co nie jest zaznaczone
                String jakaMuza = muzyka.get(i).getAttribute("value");
                System.out.println("ten element nie jest zaznaczony: " + i
                        + " o wartosci: " + jakaMuza);
                Assert.assertEquals(jakaMuza.equals("Pop"), jakaMuza.equals("Muzyka powazna"));
            }
        }
    }

    @Test //metoda będąca testem
    public void testBrowser() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        //wybieramy a nastepnie zaznaczamy przez select - Opera
        Select przegladarka = new Select(driver.findElement(By.name("Browser")));
        //  WebElement browsers = driver.findElement(By.cssSelector("select[name='Browser']"));
        //  Select przegladarka = new Select(browsers);
        przegladarka.selectByVisibleText("Opera");
        Thread.sleep(10000);
        List<WebElement> przegladarki = przegladarka.getOptions();
        System.out.println("liczba przegladarek w selekcie= " + przegladarki.size());
        for (int i = 0; i < przegladarki.size(); i++) {
            //szukamy elementu ktory jest zaznaczony
            if (przegladarki.get(i).isSelected()) {  //sprawdzamy co jest zaznaczone
                String jakaPrzegladarka = przegladarki.get(i).getText();
                System.out.println("zaznaczony jest element: " + i
                        + " o wartosci: " + jakaPrzegladarka);
                Assert.assertTrue(jakaPrzegladarka.equals("Opera"));
            } else { // wypisanie elementow nie zaznaczonych
                String jakaPrzegladarka = przegladarki.get(i).getText();
                System.out.println("ten element nie jest zaznaczony: " + i
                        + " o wartosci: " + jakaPrzegladarka);
                Assert.assertFalse(jakaPrzegladarka.equals("Opera"));
            }
        }
    }

    @Test //metoda będąca testem
    public void testSystem() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("podstawowe"));
        link.click();
        //wybieramy a nastepnie zaznaczamy przez select - Windows, Android
        Select systemo = new Select(driver.findElement(By.name("System operacyjny")));
        systemo.deselectAll();
        systemo.selectByVisibleText("Windows");
        systemo.selectByIndex(3);
        List<WebElement> systemyo = systemo.getOptions();
        System.out.println("liczba systemow w selekcie= " + systemyo.size());
        for (int i = 0; i < systemyo.size(); i++) {
            //szukamy elementu ktory jest zaznaczony
            if (systemyo.get(i).isSelected()) {  //sprawdzamy co jest zaznaczone
                String jakiSystem = systemyo.get(i).getText();
                System.out.println("zaznaczony jest element: " + i
                        + " o wartosci: " + jakiSystem);
                Assert.assertNotEquals(jakiSystem.equals("Windows"), jakiSystem.equals("Android"));
            } else { // wypisanie elementow nie zaznaczonych
                String jakiSystem = systemyo.get(i).getText();
                System.out.println("ten element nie jest zaznaczony: " + i
                        + " o wartosci: " + jakiSystem);
                Assert.assertEquals(jakiSystem.equals("Windows"), jakiSystem.equals("Android"));
            }
        }
    }

    @Test //metoda będąca testem
    public void testAlert() throws InterruptedException {
        WebElement link1 = driver.findElement(By.partialLinkText("dodatkowe"));
        link1.click();
        WebElement link2 = driver.findElement(By.partialLinkText("alerty"));
        link2.click();
        WebElement alert = driver.findElement(By.id("btnAlert"));
        alert.click();
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println(alertMessage);
        Assert.assertTrue(alertMessage.contains("blokowany"));
    }

    @Test //metoda będąca testem
    public void testAlert1() throws InterruptedException {
        WebElement link = driver.findElement(By.partialLinkText("JavaScript"));
        link.click();
        WebElement alert = driver.findElement(By.id("alert"));
        alert.click();
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println(alertMessage);
        Assert.assertTrue(alertMessage.contains("wszystkim"));
    }

    @Test //metoda będąca testem
    public void testConfirm() throws InterruptedException {
        WebElement link1 = driver.findElement(By.partialLinkText("dodatkowe"));
        link1.click();
        WebElement link2 = driver.findElement(By.partialLinkText("alerty"));
        link2.click();
        WebElement alert = driver.findElement(By.id("btnConfirm"));
        alert.click();
        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);
        Assert.assertTrue(alertMessage.contains("Wybierz opcj"));
        driver.switchTo().alert().accept();
        WebElement odpowiedz = driver.findElement(By.id("output"));
        String odpText = odpowiedz.getText();
        System.out.println("przycisk ok: " + odpText);
        Assert.assertTrue(odpText.contains("Potwierdzone"));
        alert.click();
        driver.switchTo().alert().dismiss();
        odpowiedz = driver.findElement(By.id("output"));
        odpText = odpowiedz.getText();
        System.out.println("przycisk cancel: " + odpText);
        Assert.assertTrue(odpText.contains("Odrzucone!"));
    }

    @Test //metoda będąca testem
    public void testPrompt() throws InterruptedException {
        WebElement link1 = driver.findElement(By.partialLinkText("dodatkowe"));
        link1.click();
        WebElement link2 = driver.findElement(By.partialLinkText("alerty"));
        link2.click();
        WebElement alert = driver.findElement(By.id("btnPrompt"));
        alert.click();
        String alertMessage = driver.switchTo().alert().getText();
        System.out.println(alertMessage);
        Assert.assertTrue(alertMessage.contains("najlepsze"));
        driver.switchTo().alert().accept();
        WebElement odpowiedz = driver.findElement(By.id("output"));
        String odpText = odpowiedz.getText();
        System.out.println("przycisk ok: " + odpText);
        Assert.assertTrue(odpText.contains("Selenium"));
        alert.click();
        driver.switchTo().alert().sendKeys("Selenium jest the BEST");
        driver.switchTo().alert().accept();
        odpowiedz = driver.findElement(By.id("output"));
        odpText = odpowiedz.getText();
        System.out.println("przycisk ok po wyslaniu: " + odpText);
        Assert.assertTrue(odpText.contains("BEST"));
    }
}
