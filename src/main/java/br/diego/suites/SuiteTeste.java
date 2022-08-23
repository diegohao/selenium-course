package br.diego.suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.diego.test.TesteCadastro;
import br.diego.test.TesteCampoTreinamento;
import br.diego.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteCampoTreinamento.class,
	TesteRegrasCadastro.class
})
public class SuiteTeste {

}
