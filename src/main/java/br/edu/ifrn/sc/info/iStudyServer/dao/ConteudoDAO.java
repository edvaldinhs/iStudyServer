package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Conteudo;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;

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
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			Conexao.desconectar();
		}

		return resultado;
	}

	public boolean atualizar(Conteudo conteudo) {

		boolean resultado = false;
		String sql = "UPDATE conteudo SET nome = ?, resumo = ?, data_inicio = ?, data_fim = ?, imagem = ?, disciplina_id = ? where id = ?;";
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
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			Conexao.desconectar();
		}

		return resultado;
	}
	
	public boolean remover(Conteudo conteudo) {

		boolean resultado = false;
		String sql = "DELETE FROM conteudo where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, conteudo.getId());

			int linhasAfetadas = comando.executeUpdate();

			if (linhasAfetadas > 0) {
				resultado = true;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			Conexao.desconectar();
		}

		return resultado;
	}
	
public List<Conteudo> listarTodos() {
		
		List<Conteudo> lista = new ArrayList<>();
		
		String sql = "select id, nome, resumo, data_inicio, data_fim, imagem, disciplina_id from disciplina;";
		
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
				c.setData_fim(resultSet.getString("data_fim"));
				c.setImagem(resultSet.getString("imagem"));
				Disciplina disciplina = new Disciplina();
	            disciplina.setId(resultSet.getInt("disciplina_id"));
	            c.setDisciplina(disciplina);
	            
				lista.add(c);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}
	
}
