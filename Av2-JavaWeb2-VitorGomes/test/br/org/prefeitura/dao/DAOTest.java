package br.org.prefeitura.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.org.prefeitura.model.Endereco;
import br.org.prefeitura.model.Fornecedor;
import br.org.prefeitura.model.Processo;

public class DAOTest {
	
	@Test
	public void deveInserirUmFornedor() {
		Endereco endereco = new Endereco();
		endereco.setComplemento("Bloco 9, Ap 303");
		endereco.setNumero("2303");
		endereco.setLogradouro("Av. Chrisostomo Pimentel de Oliveira");
		endereco.setCep("21650001");
		endereco.setBairro("Pavuna");
		endereco.setMunicipio("Rio de Janeiro");
		endereco.setUf("RJ");
		new DAO<Endereco>(Endereco.class).adiciona(endereco);
		
		Processo processo = new Processo();
		processo.setDataRelato(Calendar.getInstance());
		processo.setFiscalResponsavel("Fulano de Tal");
		processo.setRelatoFiscalizacao("Alguma coisa");
		new DAO<Processo>(Processo.class).adiciona(processo);
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.getProcessos().add(processo);
		fornecedor.setEndereco(endereco);
		fornecedor.setCnpj("11.111.111/1111-11");
		fornecedor.setInscricaoMunicipal("");
		fornecedor.setRazaoSocial("");
		fornecedor.setReceitaBruta(new BigDecimal(20000));
		
		new DAO<Fornecedor>(Fornecedor.class).adiciona(fornecedor);
	}
	
	@Test
	public void deveTrazerUmaListaDeFornecedores() {
		List<Fornecedor> fornecedores = new DAO<Fornecedor>(Fornecedor.class).listaTodos();
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}
}
