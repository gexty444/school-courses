package w10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

public class MonkeyTestISTD {

    public static void main(String[] args) {
        // set the firefox driver
        System.setProperty("webdriver.gecko.driver","C:\\Users\\It'sMine\\AndroidStudioProjects\\JavaTest\\Javatest\\libs\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // open my webpage
        driver.get("https://istd.sutd.edu.sg");

        while (true) {
            // get all the links
            java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
            Random random = new Random();

            // click any link
            int randomInt = random.nextInt(links.size());

            System.out.println("*** Navigating to" + " " + links.get(randomInt).getAttribute("href"));

            if (links.get(randomInt).getAttribute("href") == null){
                continue;
            }
            else {
                try{
                    driver.navigate().to(links.get(randomInt).getAttribute("href"));
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
