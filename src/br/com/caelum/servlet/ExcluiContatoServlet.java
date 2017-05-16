package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDAO;
import br.com.caelum.agenda.modelo.Contato;
import jdk.nashorn.internal.runtime.ListAdapter;

@WebServlet("/excluiContato")
public class ExcluiContatoServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// BUSCA O WRITER
		PrintWriter out = response.getWriter();

		// BUSCA OS PAREMETROS REQUEST
		Long id = Long.parseLong(request.getParameter("id"));
		
		ContatoDAO dao=new ContatoDAO();
		Contato contatoQueVoltouDaBusca = new ContatoDAO().buscaPorId(id);
							
		// EXCLUI CONTATO
		dao.exclui(contatoQueVoltouDaBusca);
		
		// IMPRIME O NOME DE CONTATO EXCLUI
		out.println("<html>");
		out.println("<body>");
		out.println("Contato " + contatoQueVoltouDaBusca.getNome() + " Excluido com sucesso");
		out.println("</body>");
		out.println("</html>");
	}

}
