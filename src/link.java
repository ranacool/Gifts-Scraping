import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//fetches only links


public class link {
	public String rootURL;
	
	public link(String rootURL) {
		this.rootURL = rootURL;
	}

	public List<String> Get_Links () throws IOException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("disable-gpu");
		
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        String prodURL = "https://gifts.hamropatro.com/product/";
		driver.get(rootURL);
		Duration timeout = Duration.ofSeconds(20);

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("product__2ouTw")));

		List<WebElement> list=driver.findElements(By.xpath("//*[@href]"));
		System.out.println("The number of links are:" + list.size());
		
		List <String> all_prod = new ArrayList<String> ();
		
		
		for (int i=0; i<list.size();i++)
		{
			WebElement E1= list.get(i);
			String link = E1.getAttribute("href");
//			System.out.println(link);
			
			if(!link.startsWith(prodURL)) {
				continue;
			}
			else {
				all_prod.add(link);
			}
			
		}
		
		
		
		for (int i = 0; i < all_prod.size(); i++)
		{
			String E2 = all_prod.get(i);
			System.out.println(E2);
		}
		System.out.println(all_prod.size());
		
		driver.quit();
		return all_prod;

	}
	}

