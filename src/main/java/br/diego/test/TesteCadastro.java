package br.diego.test;
import static br.diego.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.diego.core.BaseTeste;
import br.diego.core.DSL;
import br.diego.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTeste {
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Test
	public void deveCadastrarComSucesso() {
		page.setNome("Diego");
		page.setSobrenome("Henrique");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Futebol");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Diego", page.obterNomeCadastro());		
		Assert.assertEquals("Henrique", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());	
		Assert.assertEquals("Pizza", page.obterComidaCadastro());		
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Futebol", page.obterEsporteCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		page.setNome("Diego");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome("Diego");
		page.setSobrenome("Henrique");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTexto());		
	}
	
	@Test
	public void deveValidarSeEhVegetariano() {
		page.setNome("Diego");
		page.setSobrenome("Henrique");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTexto());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("Diego");
		page.setSobrenome("Henrique");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTexto());		
	}	
	
}
