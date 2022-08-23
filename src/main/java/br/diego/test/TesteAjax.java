package br.diego.test;
import static br.diego.core.DriverFactory.getDriver;
import static br.diego.core.DriverFactory.killDriver;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.diego.core.DSL;

public class TesteAjax {

	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=7c924");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void testAjax() {
		dsl.escrever("j_idt339:name", "Diego");
		dsl.clicarBotao("j_idt339:j_idt343");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt339:display"), "Diego"));
		Assert.assertEquals("Diego", dsl.obterTexto("j_idt339:display"));
	}
}
