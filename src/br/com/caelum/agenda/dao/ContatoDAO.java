package br.com.caelum.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.agenda.ConnectionFactory;
import br.com.caelum.agenda.modelo.Contato;

public class ContatoDAO {
	private Connection connection;

	public ContatoDAO() {
		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Contato contato) {
		try {
			String sql = "insert into contatos (nome, email, endereco, dataNascimento) values (?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato buscaPorId(Long id) {
			Contato contato = null;
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from Contatos where id=?");

			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				// CRIA O OBJETO CONTATO
				contato=new Contato();
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// CRIA OBJETO DATA E FAZ A CONVERSAO
				Calendar data = Calendar.getInstance();

				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}
			rs.close();
			stmt.close();
			System.out.println("PASSOU NO TRY");
			return contato;

		} catch (SQLException e) {
			System.out.println("Falha ao Buscar por ID");
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();
				// POPULA O OBJETO CONTATO
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// POPULA A DATA DE NASCIMENTO DO CONTATO, FAZENDO A CONVERSAO
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// ADICIONA CONTATO NA LISTA
				contatos.add(contato);
			}

			rs.close();
			stmt.close();

			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Contato contato) {
		String sql = "delete from contatos where id=?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, contato.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Contato contato) {
		String sql = "update contatos set nome = ?, email = ?, endereco = ?, dataNascimento = ? where id = ?";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());

			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
