package coderslab.examples;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static utils.DataFaker.*;

class MyFirstJUnitTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void registerUserTest() {
        driver.get("https://hotel-testlab.coderslab.pl/en/");
        WebElement signInButton = driver.findElement(By.className("user_login"));
        signInButton.click();
        WebElement emailInput = driver.findElement(By.className("account_input"));
        emailInput.sendKeys(createRandomEmail());
        WebElement createAccountButton = driver.findElement(By.name("SubmitCreate"));
        createAccountButton.submit();

        // Adding Xpath code

        WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='customer_lastname']"));
        WebElement emailFormInput = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='passwd']"));

        firstNameInput.sendKeys(createRandomFirstName());
        lastNameInput.sendKeys(createRandomLastName());
        emailFormInput.clear();
        emailFormInput.sendKeys(createRandomEmail());
        password.sendKeys(createRandomPassword());

        WebElement registerButton = driver.findElement(By.xpath("//button[@id='submitAccount']"));
        registerButton.click();

        // Adding CSS code
        WebElement myPersonalInfo = driver.findElement(By.cssSelector("a[title='Information']"));
        WebElement myAdresses = driver.findElement(By.cssSelector("a[title='Addresses']"));

        Assertions.assertTrue(myPersonalInfo.isDisplayed());
        Assertions.assertTrue(myAdresses.isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
