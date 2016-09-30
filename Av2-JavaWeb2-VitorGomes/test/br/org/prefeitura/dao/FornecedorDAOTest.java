package br.org.prefeitura.dao;

import org.junit.Test;

import br.org.prefeitura.model.Fornecedor;

public class FornecedorDAOTest {
	
	@Test
	public void deveBuscarFornecedorPorCNPJ() {
		Fornecedor fornecedor = new FornecedorDAO().buscaFornecedorPorCNPJ("01234567891234");
		System.out.println(fornecedor);		
		
	}

}
