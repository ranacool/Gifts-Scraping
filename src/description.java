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

//fetches description

public class description {
	public String rootURL;
	
	public description(String rootURL) {
		this.rootURL = rootURL;
	}
	
	public List<String> Get_Description() throws IOException {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("disable-gpu");
		
        WebDriver driver = new ChromeDriver(chromeOptions);
   
        String prodURL = "https://gifts.hamropatro.com/product/";
		driver.get(rootURL);
		Duration timeout = Duration.ofSeconds(50);

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("product__2ouTw")));

		List<WebElement> list=driver.findElements(By.xpath("//*[@href]"));
		System.out.println("The number of links are:" + list.size());
		
		List <String> all_url = new ArrayList<String> ();
		List <String> all_description = new ArrayList<String> ();
		
		for (int i=0; i<list.size();i++)
		{
			WebElement E1= list.get(i);
			String link = E1.getAttribute("href");
			
			if(!link.startsWith(prodURL)) {
				continue;
			}
			else {
				all_url.add(link);
			}
			
		}
		//try {
		all_url.forEach(links ->{
			String product = links;
			driver.navigate().to(product);
			
		
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("desc")));
			WebElement description = driver.findElement(By.id("desc"));
			WebElement name = driver.findElement(By.className("productName__1bjTU"));
			String prodname = name.getText();

			System.out.println(prodname);
			String descriptions = description.getText();
			

			System.out.println(descriptions);
			System.out.println("*********");
			all_description.add(descriptions);
		});
		

		driver.quit();
		return all_description;

	}

}


