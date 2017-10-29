package br.com.javaparaweb.teste;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UsuarioBean {

	private String nome;
	private String email;
	private String senha;
	private String confirmaSena;
	
	public String novo() {
		return "Usuário";
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(!this.senha.equalsIgnoreCase(this.confirmaSena)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada está ERRADA", ""));
			return "Usuário";
		}
		return "Mostra Usuário";
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSena() {
		return confirmaSena;
	}
	public void setConfirmaSena(String confirmaSena) {
		this.confirmaSena = confirmaSena;
	}
	
	
}
