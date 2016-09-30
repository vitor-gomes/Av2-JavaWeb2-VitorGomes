package br.org.prefeitura.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.org.prefeitura.dao.DAO;
import br.org.prefeitura.dao.FornecedorDAO;
import br.org.prefeitura.model.Endereco;
import br.org.prefeitura.model.Fornecedor;
import br.org.prefeitura.model.Processo;

@ManagedBean
@ViewScoped
public class ProcessoBean {
	
	private Processo processo = new Processo();
	private Integer processoId;	

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Integer getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Integer processoId) {
		this.processoId = processoId;
	}

	public List<Processo> getProcessos() {
		return new DAO<Processo>(Processo.class).listaTodos();
	}	
	
	public void carregarProcessoPeloId() {
		this.processo = new DAO<Processo>(Processo.class).buscaPorId(this.processoId);
	}
	
	public void gravar() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss-");
		String rn = sdf.format(processo.getDataRelato().getTime());
		rn = rn+processo.getFornecedor().getCnpj();
		this.processo.setCodigo(rn);
		
		if(this.processo.getId() == null) {
			new DAO<Processo>(Processo.class).adiciona(this.processo);
			System.out.println("adicionou");
		} else {
			new DAO<Processo>(Processo.class).atualiza(this.processo);
			System.out.println("atualizou");
		}
		processo = new Processo();

		String msg = "Processo salvo com sucesso! Código: "+rn;
		System.out.println(msg);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}
	
	public void buscarFornecedor() {
		String cnpj = this.processo.getFornecedor().getCnpj();
		System.out.println("Buscando pelo CNPJ "+cnpj);
		Fornecedor f = new FornecedorDAO().buscaFornecedorPorCNPJ(cnpj);
		if(f == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fornecedor "+cnpj+" não encontrado!"));
			f = new Fornecedor();
		}
		this.processo.setFornecedor(f);
	}
	
	public void excluir(Processo processo) {
		new DAO<Processo>(Processo.class).remove(processo);
	}
	
	public String editar(Processo processo) {
		this.processo = processo;
		return "processo-form?faces-redirect=true";
	}
}
