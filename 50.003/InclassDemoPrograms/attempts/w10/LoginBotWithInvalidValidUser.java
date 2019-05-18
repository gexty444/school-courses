package w10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class LoginBotWithInvalidValidUser {

    private static final int N = 10;
    private static String[] emails = {"1@@22.com", "23@33..com", "098@@2222", "lll@@lll", "@@@@@", "111@@@@","!!!", "~~~~", "###@sd.com", "$$$.com", "%%%", " ^^", " ***.coms", "()().."};
    private static String myUsername = "";
    private static String myPassword = "";

    // Although question asked to submit N invalid usernames before the captcha appears, I could not get the captcha to appear
    // even when I tried submitting a large number of invalid usernames. Hence, I am unable to inspect element and find out the identifier
    // for the captcha (and am unable to implement the part where I check if the captcha element is on screen)
    // Thus, what this program does is to submit N invalid usernames (and check if we are allowed to the password page) before finally
    // submitting the correct username.


    public static void main(String[] args) {
        loginBot(N, emails);
    }

    public static void loginBot(int N, String[] emails){
        // set the firefox driver
        System.setProperty("webdriver.gecko.driver","C:\\Users\\It'sMine\\AndroidStudioProjects\\JavaTest\\Javatest\\libs\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // open my webpage
        driver.get("https://gmail.com/");

        for (int i=0; i<N; i++) {
            // get all the links
            java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

            // get the user name field of the account page
            WebElement username = driver.findElement(By.name("identifier"));
            username.clear();

            // send invalid user name to fill up the box
            Random r = new Random();
            username.sendKeys(emails[r.nextInt(emails.length)]);

            // locate the "Next" button in the account page
            WebElement nextButton = driver.findElement(By.id("identifierNext"));
            nextButton.click();

            //explicitly wait until the password field is present in the page
            try {
                WebDriverWait wait = new WebDriverWait(driver, 2);
                // wait only until the password element becomes visible
                wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
                // now locate the password field in the current page
                WebElement password = driver.findElement(By.name("password"));
                // send password
                password.sendKeys(myPassword);
                // login and :)
                nextButton = driver.findElement(By.id("passwordNext"));
                nextButton.click();
            } catch (Exception NoSuchElementException) {
                System.out.println("login name invalid");
            }
        }

        // finally key in the correct details
        WebElement username = driver.findElement(By.name("identifier"));
        username.clear();
        username.sendKeys(myUsername);

        WebElement nextButton = driver.findElement(By.id("identifierNext"));
        nextButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            // wait only until the password element becomes visible
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            // now locate the password field in the current page
            WebElement password = driver.findElement(By.name("password"));
            // send password
            password.sendKeys(myPassword);
            // login and :)
            nextButton = driver.findElement(By.id("passwordNext"));
            nextButton.click();
        } catch (Exception NoSuchElementException) {
            System.out.println("Unable to log in even with correct details :(");
        }
        System.out.println("success");
    }
}
