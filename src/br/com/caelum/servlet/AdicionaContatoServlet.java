package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.caelum.agenda.dao.ContatoDAO;
import br.com.caelum.agenda.modelo.Contato;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
//		BUSCA O WRITER
		PrintWriter out=response.getWriter();
		
//		BUSCA OS PAREMETROS REQUEST
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento=null;
		
//		FAZENDO A CONVERSAO DA DATA
		try{
			Date date=
				new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
		} catch (ParseException e) {
				out.println("Erro de conversão da data");
				return; // PARA A EXECUÇÃO DO METODO
		}	
		
//		MONTANDO UM OBJETO CONTATO
		Contato contato=new Contato();
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);
		
//		SALVA CONTATO
		ContatoDAO dao=new ContatoDAO();
		dao.adiciona(contato);
		
//		IMPRIME O NOME DE CONTATO QUE FOI ADICIONADO
		out.println("<html>");
		out.println("<body>");
		out.println("Contato " + contato.getNome()+ " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}

}
