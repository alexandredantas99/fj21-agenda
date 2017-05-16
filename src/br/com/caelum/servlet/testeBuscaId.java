package br.com.caelum.servlet;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.com.caelum.agenda.dao.ContatoDAO;
import br.com.caelum.agenda.modelo.Contato;

public class testeBuscaId {

	public static void main(String[] args) {

//		new ContatoDAO().buscaPorId(id);
		Contato contato=new ContatoDAO().buscaPorId(13L);
//		Contato contato=new Contato();
		
		System.out.println(contato.getNome());
		System.out.println(contato.getEmail());
		System.out.println(contato.getEndereco());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(df.format(contato.getDataNascimento().getTime()));
		
		
		
	}

}
