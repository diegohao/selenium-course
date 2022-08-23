package br.diego.test;
import static br.diego.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.diego.core.DSL;
import br.diego.core.DriverFactory;

public class TestePrime {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=624fe");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt340:console:1']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt340:console:1"));
		dsl.clicarRadio(By.xpath("//label[.='Option3']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt340:console:2"));
	}
	
	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=e66e4");
		dsl.selecionarComboPrime("Option2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt339:option_label"));
	}

}
