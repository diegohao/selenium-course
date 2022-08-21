import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {

	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=7c924");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void testAjax() {
		dsl.escrever("j_idt339:name", "Diego");
		dsl.clicarBotao("j_idt339:j_idt343");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt339:display"), "Diego"));
		Assert.assertEquals("Diego", dsl.obterTexto("j_idt339:display"));
	}
}
