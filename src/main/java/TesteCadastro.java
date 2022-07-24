import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveCadastrarComSucesso() {
		dsl.escrever("elementosForm:nome", "Diego");
		dsl.escrever("elementosForm:sobrenome", "Henrique");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Diego"));		
		Assert.assertTrue(dsl.obterTexto("descSobrenome").endsWith("Henrique"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));		
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));		
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Futebol", dsl.obterTexto("descEsportes"));
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		dsl.clicarBotao("elementosForm:cadastrar");		
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		dsl.escrever("elementosForm:nome", "Diego");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		dsl.escrever("elementosForm:nome", "Diego");
		dsl.escrever("elementosForm:sobrenome", "Henrique");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTexto());		
	}
	
	@Test
	public void deveValidarSeEhVegetariano() {
		dsl.escrever("elementosForm:nome", "Diego");
		dsl.escrever("elementosForm:sobrenome", "Henrique");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarRadio("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		dsl.escrever("elementosForm:nome", "Diego");
		dsl.escrever("elementosForm:sobrenome", "Henrique");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTexto());		
	}	
	
}
