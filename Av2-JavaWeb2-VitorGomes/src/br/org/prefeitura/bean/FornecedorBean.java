package br.org.prefeitura.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.org.prefeitura.dao.DAO;
import br.org.prefeitura.model.Endereco;
import br.org.prefeitura.model.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private String[] ufs = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT",
	"MS","MG","PR","PB","PA","PE","PI","RJ","RN","RS","RO","RR","SC","SE","SP","TO"};
	
	private Fornecedor fornecedor = new Fornecedor();
	private Integer fornecedorId;
		
	public String[] getUfs() {
		return ufs;
	}
	public void setUfs(String[] ufs) {
		this.ufs = ufs;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Integer getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	
	public List<Fornecedor> getFornecedores() {
		return new DAO<Fornecedor>(Fornecedor.class).listaTodos();
	}	
	
	public void carregarFornecedorPeloId() {
		this.fornecedor = new DAO<Fornecedor>(Fornecedor.class).buscaPorId(this.fornecedorId);
	}
	
	public void gravar() {
		
		if(this.fornecedor.getId() == null) {
			new DAO<Endereco>(Endereco.class).adiciona(this.fornecedor.getEndereco());
			new DAO<Fornecedor>(Fornecedor.class).adiciona(this.fornecedor);
		} else {
			new DAO<Endereco>(Endereco.class).atualiza(this.fornecedor.getEndereco());
			new DAO<Fornecedor>(Fornecedor.class).atualiza(this.fornecedor);
		}
		fornecedor = new Fornecedor();
		String msg = "Fornecedor salvo com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}
	
}

