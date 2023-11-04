package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.QuestaoWS;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Alternativa;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Questao;

public class AlternativaDAO {
	
	public boolean inserir(Alternativa alternativa) {

		boolean resultado = false;
		String sql = "INSERT INTO alternativa(id, resposta_certa, texto, questao_id) VALUES (?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, alternativa.getId());
			comando.setBoolean(2, alternativa.isRespostaCerta());
			comando.setString(3, alternativa.getTexto());
			comando.setInt(4, alternativa.getQuestao().getId());

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

	public boolean atualizar(Alternativa alternativa) {

		boolean resultado = false;
		String sql = "UPDATE alternativa resposta_certa = ?, texto = ?, questao_id = ? where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, alternativa.getId());
			comando.setBoolean(2, alternativa.isRespostaCerta());
			comando.setString(3, alternativa.getTexto());
			comando.setInt(4, alternativa.getQuestao().getId());
			
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
	
	public boolean remover(int id) {

		boolean resultado = false;
		String sql = "DELETE FROM alternativa where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);

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
	
public List<Alternativa> listarTodas() {
		
		List<Alternativa> lista = new ArrayList<>();
		
		String sql = "select id, resposta_certa, texto, questao_id from alternativa;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Alternativa a = new Alternativa();
				
				a.setId(resultSet.getInt("id"));
				a.setRespostaCerta(resultSet.getBoolean("resposta_certa"));
				a.setTexto(resultSet.getString("texto"));
				Questao questao = new Questao();
	            questao.setId(resultSet.getInt("questao_id"));
	            a.setQuestao(questao);
	            
				lista.add(a);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}

	public Alternativa buscar(int id) {
	
		Alternativa a = null;
		
		String sql = "select id, resposta_certa, texto, questao_id from alternativa where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				a = new Alternativa();
				
				a.setId(resultSet.getInt("id"));
				a.setRespostaCerta(resultSet.getBoolean("resposta_certa"));
				a.setTexto(resultSet.getString("texto"));
				int questaoId = resultSet.getInt("questao_id");
	            Questao questao = new QuestaoWS().buscar(questaoId);
	            a.setQuestao(questao);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return a;
	}
public List<Alternativa> buscarPorQuestao(int id) {
		
		List<Alternativa> lista = new ArrayList<>();
		
		String sql = "select id, resposta_certa, texto, questao_id from alternativa where questao_id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Alternativa a = new Alternativa();
				
				a.setId(resultSet.getInt("id"));
				a.setRespostaCerta(resultSet.getBoolean("resposta_certa"));
				a.setTexto(resultSet.getString("texto"));
				Questao questao = new Questao();
	            questao.setId(resultSet.getInt("questao_id"));
	            a.setQuestao(questao);
	            
				lista.add(a);
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
