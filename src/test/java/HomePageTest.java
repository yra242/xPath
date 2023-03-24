import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class HomePageTest {
    private long IMPLICITLY_WAIT_SECONDS = 10L;
    private String BASE_URL = "https://demo.opencart.com/";
    WebDriver driver;
    @Before
    public void startDriver() {
        WebDriverManager.chromedriver().setup();
        //
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //WebDriver driver = new ChromeDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        //driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // presentationSleep(); // For Presentation ONLY
        //
        driver.get(BASE_URL);
    }
    @After
    public void closeDriver(){
        driver.quit();
    }
    @Test
    public void checkSearch() throws InterruptedException {

        driver.findElement(By.xpath("//span[@class='d-none d-md-inline' and text()='Currency']")).click();
        driver.findElement(By.xpath("//a[@class='dropdown-item' and @href='EUR' and text()='€ Euro']")).click();
        driver.findElement(By.xpath("//a[@class='nav-link dropdown-toggle' and text()='Desktops']")).click();
        driver.findElement(By.xpath("//a[@class='nav-link' and text()='Mac (1)']")).click();
        driver.findElement(By.xpath("//h4/a[text()='iMac']"));
        //driver.findElement(By.xpath("//div/span[@class='price-new']"));
        Assert.assertEquals("111.55€", driver.findElement(By.xpath("//*[@id=\"product-list\"]/div/form/div/div[2]/div[1]/div/span[1]")).getText(), "Product iMac price is not 111.55 euros");
        Thread.sleep(10000L);

}}
