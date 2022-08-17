import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrime {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=624fe");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		dsl.clicarRadio(By.xpath("//input[@id='j_idt340:console:1']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt340:console:1"));
		dsl.clicarRadio(By.xpath("//label[.='Option3']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt340:console:2"));
	}

}
