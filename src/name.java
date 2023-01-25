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

public class name {
	public String rootURL;
	
	public name(String rootURL) {
		this.rootURL = rootURL;
	}


	List <String> all_price = new ArrayList<String> ();
	List <String> all_name = new ArrayList<String> ();
	List <String> all_image = new ArrayList<String> ();
	
	public void Scraper() throws InterruptedException, IOException {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("disable-gpu");
		
        WebDriver driver = new ChromeDriver(chromeOptions);
		
		driver.get(rootURL);
		
		System.out.println(driver.getTitle());
		
		Duration timeout = Duration.ofSeconds(30);

		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product__2ouTw")));

		List<WebElement> list = driver.findElements(By.className("product__2ouTw"));
		

		System.out.println(list.size());
		
		
		for(int i = 0; i < list.size(); i++)
		{

			WebElement E0 = list.get(i);
			
		
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product__2ouTw")));

				WebElement Prod_Img = E0.findElement(By.className("product-img__78dZi"));
				String prod_img = Prod_Img.getAttribute("src");
				String prod_alt = Prod_Img.getAttribute("alt");
				
				WebElement Prod_Price = E0.findElement(By.className("product-price-sell__322Vk"));
				String prod_price = Prod_Price.getText();
				
				System.out.println(prod_price);
				all_price.add(prod_price);
				
				System.out.println(prod_img);
				all_image.add(prod_img);
				
				System.out.println(prod_alt);
				all_name.add(prod_alt);
				

	}
		driver.quit();	
		
	}
	
	public List<String> getAllPrice() {
        return all_price;
    }

    public List<String> getAllImage() {
        return all_image;
    }

    public List<String> getAllName() {
        return all_name;
    }
	
}

