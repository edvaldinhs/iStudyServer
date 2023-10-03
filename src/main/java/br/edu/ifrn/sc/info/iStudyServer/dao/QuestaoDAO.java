package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.AtividadeWS;
import br.edu.ifrn.sc.info.iStudyServer.TipoRespostaWS;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Atividade;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Questao;
import br.edu.ifrn.sc.info.iStudyServer.dominio.TipoResposta;

public class QuestaoDAO {
	
	public boolean inserir(Questao questao) {

		boolean resultado = false;
		String sql = "INSERT INTO questao (id, enunciado, pontuacao, imagem, tipo_resposta_id, atividades_id) VALUES (?, ?, ?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, questao.getId());
			comando.setString(2, questao.getEnunciado());
			comando.setInt(3, questao.getPontuacao());
			comando.setString(4, questao.getImagem());
			comando.setInt(5, questao.getTipo().getId());
			comando.setInt(6, questao.getAtividade().getId());

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

	public boolean atualizar(Questao questao) {

		boolean resultado = false;
		String sql = "UPDATE questao enunciado = ?, pontuacao = ?, imagem = ?, tipo_resposta_id = ?, atividades_id = ? where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, questao.getEnunciado());
			comando.setInt(2, questao.getPontuacao());
			comando.setString(3, questao.getImagem());
			comando.setInt(4, questao.getTipo().getId());
			comando.setInt(5, questao.getAtividade().getId());
			comando.setInt(6, questao.getId());
			
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
		String sql = "DELETE FROM questao where id = ?;";
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
	
	public List<Questao> listarTodas() {
		
		List<Questao> lista = new ArrayList<>();
		
		String sql = "select id, enunciado, pontuacao, imagem, tipo_resposta_id, atividades_id from questao;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Questao q = new Questao();
				
				q.setId(resultSet.getInt("id"));
				q.setEnunciado(resultSet.getString("enunciado"));
				q.setPontuacao(resultSet.getInt("pontuacao"));
				q.setImagem(resultSet.getString("imagem"));
				int tipoRespostaId = resultSet.getInt("tipo_resposta_id");
	            TipoResposta tipo = new TipoRespostaWS().buscar(tipoRespostaId);
	            q.setTipo(tipo);
	            int atividadeId = resultSet.getInt("atividades_id");
	            Atividade atividade = new AtividadeWS().buscar(atividadeId);
	            q.setAtividade(atividade);
	            
				lista.add(q);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}

	public Questao buscar(int id) {
	
		Questao q = null;
		
		String sql = "select id, enunciado, pontuacao, imagem, tipo_resposta_id, atividades_id from questao where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				q = new Questao();
				
				q.setId(resultSet.getInt("id"));
				q.setEnunciado(resultSet.getString("enunciado"));
				q.setPontuacao(resultSet.getInt("pontuacao"));
				q.setImagem(resultSet.getString("imagem"));
				int tipoRespostaId = resultSet.getInt("tipo_resposta_id");
	            TipoResposta tipo = new TipoRespostaWS().buscar(tipoRespostaId);
	            q.setTipo(tipo);
	            int atividadeId = resultSet.getInt("atividades_id");
	            Atividade atividade = new AtividadeWS().buscar(atividadeId);
	            q.setAtividade(atividade);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return q;
	}

}
