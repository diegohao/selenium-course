package br.diego.core;

import static br.diego.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTeste {
	
	@After
	public void finaliza() {
		killDriver();
	}

}
