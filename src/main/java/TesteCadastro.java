import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

	@Test
	public void deveCadastrarComSucesso() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Diego");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Futebol");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Diego"));
		Assert.assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Henrique"));
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol", driver.findElement(By.id("descEsportes")).getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Diego");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Diego");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		
		driver.quit();		
	}
	
	@Test
	public void deveValidarSeEhVegetariano() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Diego");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
		driver.quit();
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		System.setProperty("webdriver.gecko.firefox", "/home/diego/eclipse-workspace/SeleniumCourse/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Diego");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Henrique");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Karate");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		
		driver.quit();		
	}	
	
}
