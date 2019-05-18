package w10;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HeaderNameFinder {


    @Test
    public void testTitle(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\It'sMine\\AndroidStudioProjects\\JavaTest\\Javatest\\libs\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // to a given website
        driver.get("http://bing.com");

        // get all the links
        java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        // array to store all the urls
        ArrayList<String> urls = new ArrayList<>();

        // get the urls
        for (WebElement link : links){
            String url = link.getAttribute("href");
            // check if reachable
            if (url != null && !urls.contains(url)){
                urls.add(url);
            }
        }

        // test the urls
        for (String url: urls){
            driver.get(url);
            assertNotNull(driver.getTitle());
        }
    }
}
