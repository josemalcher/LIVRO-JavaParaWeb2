# Resumo Livro Java para Web 2ed.

---

## <a name="indice">Índice</a>

- [Preparação do ambiente de desenvolvimento](#parte1)
- [Primeiros passos com o JavaServer Faces](#parte2)
- [Introdução a Hibernate e SQL com Java](#parte3)
- [Desenvolvimento do cadastro de usuários](#parte4)
- [Administração de usuários com DataTable](#parte5)
- [Proteção do aplicativo com Spring Security](#parte6)
- [Cadastro de contas com Ajax](#parte7)
- [Definição da identidade visual com Facelets e CSS](#parte8)
- [Cadastro de categorias usando PrimeFaces](#parte9)
- [Registro de lançamentos com tags personalizadas](#parte10)
- [Cadastro de cheques multi-idiomas](#parte11)
- [Como trabalhar com gráficos](#parte12)
- [Gerenciamento de investimentos na bolsa de valores](#parte13)
- [Envio de email](#parte14)
- [Relatórios com iReport e JasperReports](#parte15)
- [Fornecimento e consumo de web services](#parte16)
- [Finalização e empacotamento do projeto](#parte17)
- [Apêndices](#parte18)

---

## <a name="parte1">Preparação do ambiente de desenvolvimento</a>

[Voltar ao Índice](#indice)

---

## <a name="parte2">Primeiros passos com o JavaServer Faces</a>

Ajsutes iniciais:

web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
							http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" 
	version="3.1">
  <display-name>teste</display-name>
  <servlet>
    <servlet-name>Faces Servlet</servlet-name>
    <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>Faces Servlet</servlet-name>
    <url-pattern>*jsf</url-pattern>
  </servlet-mapping>
  <context-param>
		<param-name>javax.faces.PROJECT_STAGE</param-name> 
		<param-value>Development</param-value>
	</context-param>
	<context-param>
		<param-name>javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL</param-name> 
		<param-value>true</param-value>
	</context-param>
	<context-param>
		<param-name>javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE</param-name> 
		<param-value>true</param-value>
	</context-param>
  <!-- <welcome-file-list>
    <welcome-file>index.jsf</welcome-file>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
  </welcome-file-list> --> <!-- Removido por falha com o apache 8.5 e 9 ||| ADICIONADO NOVAMENTE DEPOIS QUE ADICIONADO UM ARQUIVOS index.xhtml o bug parou! -->
</web-app>
```

pom.xml
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>javaparaweb</groupId>
  <artifactId>teste</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <name>Teste</name>
  <description>Primeiro projeto com Javaserver Faces</description>
  <build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-resources-plugin</artifactId>
				<version>2.7</version>
				<configuration>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>
		</plugins>
	</build>
	<repositories>
		<repository>
			<id>jvnet-nexus-releases</id>
			<name>jvnet-nexus-releases</name>
			<url>https://maven.java.net/content/repositories/releases/</url>
		</repository>
	</repositories>
	<dependencies>
		<dependency>
			<groupId>javax</groupId>
			<artifactId>javaee-api</artifactId>
			<version>7.0</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.glassfish</groupId>
			<artifactId>javax.faces</artifactId>
			<version>2.2.10</version>
		</dependency>
	</dependencies>
</project>
```

UsuarioBean.java
```java
package br.com.javaparaweb.teste;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {

	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;
	
	public String novo() {
		return "usuario";
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(!this.senha.equalsIgnoreCase(this.confirmaSenha)) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada est� ERRADA", ""));
			return "usuario";
		}
		return "mostrausuario";
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
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
}

```

index.xhtml
```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:h="http://xmlns.jcp.org/jsf/html"
	  xmlns:f="http://xmlns.jcp.org/jsf/core"	>
<h:head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Página Inícial</title>
</h:head>
<h:body>
	<h1>Página Principal</h1>
	<hr />
		<h:form>
			<h:commandLink action="#{usuarioBean.novo}">
				Novo Usuário
			</h:commandLink>
		</h:form>
	<hr />
</h:body>
</html>
```
usuario.xhtml
```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:h="http://xmlns.jcp.org/jsf/html"
	  xmlns:f="http://xmlns.jcp.org/jsf/core"	>
<h:head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Página de Cadastro de Usuário</title>
</h:head>
<h:body>
	<h1>Cadastro de Usuários</h1>
	<hr />
	<h:form acceptcharset="charset=UTF-8"> 
		<h:messages /> 
		<h:panelGrid columns="2"> 
			<h:outputLabel value="Nome:" for="nome" /> 
			<h:inputText id="nome" label="Nome" value="#{usuarioBean.nome}" required="true" /> 
			<h:outputLabel value="e-Mail:" for="email" />
			<h:inputText id="email" label="e-Mail" value="#{usuarioBean.email}" />
			<h:outputLabel value="Senha:" for="senha" />
			<h:inputSecret id="senha" label="Senha" value="#{usuarioBean.senha}" required="true" />
			<h:outputLabel value="Confirmar Senha:" for="confirmaSenha" />
			<h:inputSecret id="confirmaSenha" label="Confirmar Senha" value="#{usuarioBean.confirmaSenha}" required="true" />
			<h:outputText /> 
			<h:commandButton action="#{usuarioBean.salvar}" value="Salvar" /> 
		</h:panelGrid>
	</h:form>
	<hr />
</h:body>
</html>
```
mostrausuario.xhtml
```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:h="http://xmlns.jcp.org/jsf/html"
	  xmlns:f="http://xmlns.jcp.org/jsf/core"	>
<h:head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Usuários Cadastrados</title>
</h:head>
<h:body>
	<h1>Usuários Cadastrados</h1>
	<hr />
	Nome: <h:outputText value="#{usuarioBean.nome}" /> <br/>
	email: <h:outputLink value="mailto:#{usuarioBean.email}">
				<h:outputText value="#{usuarioBean.email}" />
		   </h:outputLink><br/>
	Senha: <h:outputText value="#{usuarioBean.senha}" /> <br/>
	<hr/>
	
	<h:form>
		<h:commandLink action="index" value="Início" />
	</h:form>
	
	<hr />
</h:body>
</html>
```

[Voltar ao Índice](#indice)

---

## <a name="parte3">Introdução a Hibernate e SQL com Java</a>

Exemplo JDBC
```java
package com.javaparaweb.cap3.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
	public static void main(String[] args) {
		Connection conexao = null;
		try {
			// Registrando a classe JDBC, e os parámetros de conexão em tempo de execução
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "adminjose";
			String senha = "adminadmin";

			conexao = DriverManager.getConnection(url, usuario, senha); //(_1_)
			System.out.println("Conectou!");
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao criar a conexão. Erro: " + e.getMessage());
		}
	}
}
```

---

```java
package com.javaparaweb.cap3.crudjdbc;

import java.sql.Date;

public class Contato {
	
	private Integer codigo;
	private String nome;
	private String telefone;
	private String email;
	private Date dataCadastro;
	private String observacao;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
```

```java
package com.javaparaweb.cap3.crudjdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ContatoCrudJDBC {
	public void salvar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		String sql = "insert into contato (nome, telefone, email, dt_cad, obs) values (?, ?, ?, ?, ?)";
		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			insereSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir contato. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operações de inserção. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public void atualizar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement atualizaSt = null;

		// Aqui não atualizamos o campo data de cadastro
		String sql = "update contato set nome=?, telefone=?, email=?, obs=? where codigo=?";

		try {
			atualizaSt = conexao.prepareStatement(sql);
			atualizaSt.setString(1, contato.getNome());
			atualizaSt.setString(2, contato.getTelefone());
			atualizaSt.setString(3, contato.getEmail());
			atualizaSt.setString(4, contato.getObservacao());
			atualizaSt.setInt(5, contato.getCodigo());
			atualizaSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar contato. Mensagem: " + e.getMessage());
		} finally {
			try {
				atualizaSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operações de atualização. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public void excluir(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement excluiSt = null;

		String sql = "delete from contato where codigo = ?";

		try {
			excluiSt = conexao.prepareStatement(sql);
			excluiSt.setInt(1, contato.getCodigo());
			excluiSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao excluir contato. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operações de exclusão. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public List<Contato> listar() {
		Connection conexao = this.geraConexao();
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato";
		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao(resultado.getString("obs"));
				contatos.add(contato);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do contato. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operações de consulta. Mensagem: "
								+ e.getMessage());
			}
		}
		return contatos;
	}

	public Contato buscaContato(int valor) {
		Connection conexao = this.geraConexao();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;

		String sql = "select * from contato where codigo = ?";

		try {
			consulta = conexao.prepareStatement(sql);
			consulta.setInt(1, valor);
			resultado = consulta.executeQuery();

			if (resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao(resultado.getString("obs"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do contato. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar operações de consulta. Mensagem: "
								+ e.getMessage());
			}
		}
		return contato;
	}

	public Connection geraConexao() {
		Connection conexao = null;

		try {
			// Registrando a classe JDBC no sistema em tempo de execução
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "adminjose";
			String senha = "adminadmin";
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException e) {
			System.out
					.println("Classe não encontrada. Erro: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro de SQL. Erro: "
					+ e.getMessage());
		}
		return conexao;
	}

	public static void main(String[] args) {
		ContatoCrudJDBC contatoCRUDJDBC = new ContatoCrudJDBC();

		// Criando um primeiro contato
		Contato beltrano = new Contato();
		beltrano.setNome("Beltrano Solar");
		beltrano.setTelefone("(47) 5555-3333");
		beltrano.setEmail("beltrano@teste.com.br");
		beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
		beltrano.setObservacao("Novo cliente");
		contatoCRUDJDBC.salvar(beltrano);

		// Criando um segundo contato
		Contato fulano = new Contato();
		fulano.setNome("Fulano Lunar");
		fulano.setTelefone("(47) 7777-2222");
		fulano.setEmail("fulano@teste.com.br");
		fulano.setDataCadastro(new Date(System.currentTimeMillis()));
		fulano.setObservacao("Novo contato – possível cliente");
		contatoCRUDJDBC.salvar(fulano);
		System.out.println("Contatos cadastrados: " + contatoCRUDJDBC.listar().size());
	}

}

```

```java
package br.com.javaparaweb.captulo3.crudannotaions;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {
	
	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "nome", length = 50, nullable = true)
	private String nome;
	
	@Column(name = "telefone", length = 50, nullable = true)
	private String telefone;
	
	@Column(name = "email", length = 50, nullable = true)
	private String email;
	
	@Column(name = "dt_cad", nullable = true)
	private Date dataCadastro;
	
	@Column(name = "obs", nullable = true)
	private String observacao;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	

}

```

```xml
	<session-factory>
		<mapping class="br.com.javaparaweb.captulo3.crudannotaions.Contato"/> 
	</session-factory>
```

```java
package br.com.javaparaweb.captulo3.crudannotaions;

import java.sql.Date;
import java.util.List;

import org.hibernate.*;

import br.com.javaparaweb.captulo3.conexao.HibernateUtil;

public class ContatoCrudAnnotations {
	
	private Session sessao;
	public ContatoCrudAnnotations(Session sessao) {
		this.sessao = sessao;
	}
	
	public void salvar(Contato contato) {
		sessao.save(contato);
	}
	public void atualizar(Contato contato) {
		sessao.update(contato);
	}
	public void excluir(Contato contato) {
		sessao.delete(contato);
	}
	
	public List<Contato> listar(){
		Query consulta  =  sessao.createQuery("from Contato");
		return consulta.list();
	}
	public Contato buscaContato(int valor) {
		Query consulta = sessao.createQuery("from Contato where codigo = :parametro");
		consulta.setInteger("parametro", valor);
		return (Contato) consulta.uniqueResult();
	}



	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		
		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
		
		Contato contato1 = new Contato();
		contato1.setNome("Solanu");
		contato1.setTelefone("(47) 3333-4444");
		contato1.setEmail("solanu@javaparaweb.com.br");
		contato1.setDataCadastro(new Date(System.currentTimeMillis()));
		contato1.setObservacao("Novo cliente");
		contatoCrud.salvar(contato1);
		
		contato1.setObservacao("Retomar contato");
		contatoCrud.atualizar(contato1); 
		
		Contato contato2 = new Contato();
		contato2.setNome("Lunare");
		contato2.setTelefone("(47) 7777-5555");
		contato2.setEmail("lunare@javaparaweb.com.br");
		contato2.setDataCadastro(new Date(System.currentTimeMillis()));
		contato2.setObservacao("Cliente em dia");
		contatoCrud.salvar(contato2); 

		System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size()); 

		//contatoCrud.excluir(contato2); 

		transacao.commit(); 
			
		System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size()); 
	}

}


```


---



[Voltar ao Índice](#indice)

---

## <a name="parte4">Desenvolvimento do cadastro de usuários</a>

[Voltar ao Índice](#indice)

---

## <a name="parte5">Administração de usuários com DataTable</a>

[Voltar ao Índice](#indice)

---

## <a name="parte6">Proteção do aplicativo com Spring Security</a>

[Voltar ao Índice](#indice)

---

## <a name="parte7">Cadastro de contas com Ajax</a>

[Voltar ao Índice](#indice)

---

## <a name="parte8">Definição da identidade visual com Facelets e CSS</a>

[Voltar ao Índice](#indice)

---

## <a name="parte9">Cadastro de categorias usando PrimeFaces</a>

[Voltar ao Índice](#indice)

---

## <a name="parte10">Registro de lançamentos com tags personalizadas</a>

[Voltar ao Índice](#indice)

---

## <a name="parte11">Cadastro de cheques multi-idiomas</a>

[Voltar ao Índice](#indice)

---

## <a name="parte12">Como trabalhar com gráficos</a>

[Voltar ao Índice](#indice)

---

## <a name="parte13">Gerenciamento de investimentos na bolsa de valores</a>

[Voltar ao Índice](#indice)

---

## <a name="parte14">Envio de email</a>

[Voltar ao Índice](#indice)

---

## <a name="parte15">Relatórios com iReport e JasperReports</a>

[Voltar ao Índice](#indice)

---

## <a name="parte16">Fornecimento e consumo de web services</a>

[Voltar ao Índice](#indice)

---

## <a name="parte17">Finalização e empacotamento do projeto</a>

[Voltar ao Índice](#indice)

---

## <a name="parte18">Apêndices</a>

[Voltar ao Índice](#indice)

---
