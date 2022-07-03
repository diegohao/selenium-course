import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
	}

}
