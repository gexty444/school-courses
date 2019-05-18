import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenLinkName {
	
	public static void main(String[] args) {		
		
		// set the firefox driver
		System.setProperty("webdriver.gecko.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2019/Test/newGecko/geckodriver");
		//System.setProperty("webdriver.chrome.driver","/Users/sudiptac/sudiptac/teaching/SUTD/50.003@2018/Test/chromedriver");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();

		// open my webpage
		driver.get("https://sudiptac.bitbucket.io/");
				
		// click the link with name "press release"
		driver.findElement(By.linkText("ASSET Research Group")).click();
		
		// click the link name "Event"
		driver.findElement(By.linkText("Sakshi Udeshi")).click();
		
		// click the link name "Newsletter"
		driver.findElement(By.linkText("facebook")).click();

	}
}
