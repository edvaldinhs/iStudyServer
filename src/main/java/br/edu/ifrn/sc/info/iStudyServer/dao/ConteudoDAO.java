package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import br.edu.ifrn.sc.info.iStudyServer.DisciplinaWS;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Conteudo;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Estudante;

public class ConteudoDAO {

	public boolean inserir(Conteudo conteudo) {

		boolean resultado = false;
		String sql = "INSERT INTO conteudo(id, nome, resumo, data_inicio, data_fim, imagem, disciplina_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, conteudo.getId());
			comando.setString(2, conteudo.getNome());
			comando.setString(3, conteudo.getResumo());
			comando.setString(4, conteudo.getDataInicio());
			comando.setString(5, conteudo.getDataFim());
			comando.setString(6, conteudo.getImagem());
			comando.setInt(7, conteudo.getDisciplina().getId());

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Conexao.desconectar();
		}

		return resultado;
	}

	public boolean atualizar(Conteudo conteudo) {

		boolean resultado = false;
		String sql = "UPDATE conteudo SET id = ?, nome = ?, resumo = ?, data_inicio = ?, data_fim = ?, imagem = ?, disciplina_id = ? where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, conteudo.getId());
			comando.setString(2, conteudo.getNome());
			comando.setString(3, conteudo.getResumo());
			comando.setString(4, conteudo.getDataInicio());
			comando.setString(5, conteudo.getDataFim());
			comando.setString(6, conteudo.getImagem());
			comando.setInt(7, conteudo.getDisciplina().getId());

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Conexao.desconectar();
		}

		return resultado;
	}

	public boolean remover(int id) {

		boolean resultado = false;
		String sql = "DELETE FROM conteudo where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Conexao.desconectar();
		}

		return resultado;
	}

	public List<Conteudo> listarTodos() {

		List<Conteudo> lista = new ArrayList<>();

		String sql = "select id, nome, resumo, data_inicio, data_fim, imagem, disciplina_id from conteudo;";

		Connection conexao = Conexao.conectar();

		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();

			while (resultSet.next()) {

				Conteudo c = new Conteudo();

				c.setId(resultSet.getInt("id"));
				c.setNome(resultSet.getString("nome"));
				c.setResumo(resultSet.getString("resumo"));
				c.setDataInicio(resultSet.getString("data_inicio"));
				c.setDataFim(resultSet.getString("data_fim"));
				c.setImagem(resultSet.getString("imagem"));
				int disciplinaId = resultSet.getInt("disciplina_id");
				Disciplina disciplina = new DisciplinaWS().buscar(disciplinaId);
				c.setDisciplina(disciplina);

				lista.add(c);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Conexao.desconectar();
		}

		return lista;
	}

	public Conteudo buscar(int id) {
		Conteudo c = null;

		String sql = "SELECT id, nome, resumo, data_inicio, data_fim, imagem, disciplina_id FROM conteudo WHERE id = ?;";

		Connection conexao = Conexao.conectar();

		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);

			ResultSet resultSet = comando.executeQuery();

			if (resultSet.next()) {
				c = new Conteudo();

				c.setId(resultSet.getInt("id"));
				c.setNome(resultSet.getString("nome"));
				c.setResumo(resultSet.getString("resumo"));
				c.setDataInicio(resultSet.getString("data_inicio"));
				c.setDataFim(resultSet.getString("data_fim"));
				c.setImagem(resultSet.getString("imagem"));
				int disciplinaId = resultSet.getInt("disciplina_id");
				Disciplina disciplina = new DisciplinaWS().buscar(disciplinaId);
				c.setDisciplina(disciplina);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.desconectar();
		}

		return c;
	}

	public List<Conteudo> listarTodosDesbloqueados(Estudante estudante) {

		List<Conteudo> listaDesbloqueados = new ArrayList<>();

		String sql = "select id, nome, resumo, data_inicio, data_fim, imagem, disciplina_id from conteudo;";

		Connection conexao = Conexao.conectar();

		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();

			while (resultSet.next()) {

				Conteudo c = new Conteudo();

				c.setId(resultSet.getInt("id"));
				c.setNome(resultSet.getString("nome"));
				c.setResumo(resultSet.getString("resumo"));
				c.setDataInicio(resultSet.getString("data_inicio"));
				c.setDataFim(resultSet.getString("data_fim"));
				c.setImagem(resultSet.getString("imagem"));
				int disciplinaId = resultSet.getInt("disciplina_id");
				Disciplina disciplina = new DisciplinaWS().buscar(disciplinaId);
				c.setDisciplina(disciplina);

				if (c.estaDesbloqueado(estudante)) {
					listaDesbloqueados.add(c);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Conexao.desconectar();
		}

		return listaDesbloqueados;
	}

	public boolean desbloquearConteudo(int id) {

		boolean resultado = false;
		String sql = "UPDATE conteudo SET bloqueado = 0 where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			Conexao.desconectar();
		}

		return resultado;
	}

	public int buscarProgressoConteudo(String email, int conteudoId) {
		int c = -1;

		String sql = "SELECT progresso_conteudo FROM estudante_conteudo WHERE estudante_email = ? AND conteudo_id = ?;";

		Connection conexao = Conexao.conectar();

		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, email);
			comando.setInt(2, conteudoId);
			ResultSet resultSet = comando.executeQuery();

			if (resultSet.next()) {
				c = resultSet.getInt("progresso_conteudo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexao.desconectar();
		}

		return c;
	}

}
