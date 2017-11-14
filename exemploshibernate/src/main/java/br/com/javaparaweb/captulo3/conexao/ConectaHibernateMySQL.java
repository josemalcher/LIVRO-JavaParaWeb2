package br.com.javaparaweb.captulo3.conexao;

import org.hibernate.Session;

public class ConectaHibernateMySQL {

	public static void main(String[] args) {
		
		Session sessao = null;
		sessao = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Conectou!");
		sessao.close();
	}
		
}
