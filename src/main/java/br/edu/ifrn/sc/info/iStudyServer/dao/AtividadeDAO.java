package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.ConteudoWS;
import br.edu.ifrn.sc.info.iStudyServer.DificuldadeWS;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Atividade;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Conteudo;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Dificuldade;


public class AtividadeDAO {
	
	public boolean inserir(Atividade atividade) {

		boolean resultado = false;
		String sql = "INSERT INTO atividade(id, nome, icone, dificuldade_id, conteudo_id) VALUES (?, ?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, atividade.getId());
			comando.setString(2, atividade.getNome());
			comando.setString(3, atividade.getIcone());
			comando.setInt(4, atividade.getDificuldade().getId());
			comando.setInt(5, atividade.getConteudo().getId());

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

	public boolean atualizar(Atividade atividade) {

		boolean resultado = false;
		String sql = "UPDATE atividade nome = ?, icone = ?, dificuldade_id = ?, conteudo_id = ? WHERE id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, atividade.getNome());
			comando.setString(2, atividade.getIcone());
			comando.setInt(3, atividade.getDificuldade().getId());
			comando.setInt(4, atividade.getConteudo().getId());
			comando.setInt(5, atividade.getId());
			
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
	
	public boolean desbloquearQuiz(String email, int conteudoId) {
		int progressoConteudo;
		
		ConteudoDAO dao = new ConteudoDAO();
		progressoConteudo = dao.buscarProgressoConteudo(email, conteudoId);
		
		if (progressoConteudo < 3) {
			progressoConteudo++;
		}

		boolean resultado = false;
		String sql = "UPDATE `istudy_db`.`estudante_conteudo` SET `progresso_conteudo` = '?' WHERE (`estudante_email` = '?') and (`conteudo_id` = '?');";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, 2);
			comando.setString(2, email);
			comando.setInt(3, conteudoId);
			
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
		String sql = "DELETE id, nome, FROM atividade where id = ?;";
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
	
	public List<Atividade> listarTodas() {
		
		List<Atividade> lista = new ArrayList<>();
		
		String sql = "select id, nome, icone, dificuldade_id, conteudo_id from atividade;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Atividade a = new Atividade();
				
				a.setId(resultSet.getInt("id"));
				a.setNome(resultSet.getString("nome"));
				a.setIcone(resultSet.getString("icone"));
				int dificuldadeId = resultSet.getInt("dificuldade_id");
	            Dificuldade dificuldade = new DificuldadeWS().buscar(dificuldadeId);
	            a.setDificuldade(dificuldade);
	            int conteudoId = resultSet.getInt("conteudo_id");
	            Conteudo conteudo = new ConteudoWS().buscar(conteudoId);
	            a.setConteudo(conteudo);
	            
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

public Atividade buscar(int id) {
	
    Atividade a = null;
    
    String sql = "SELECT id, nome, icone, dificuldade_id, conteudo_id FROM atividade WHERE id = ?;";
    
    Connection conexao = Conexao.conectar();
    
    try {
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setInt(1, id);
        
        ResultSet resultSet = comando.executeQuery();
        
        if (resultSet.next()) {
            a = new Atividade();
            
            a.setId(resultSet.getInt("id"));
            a.setNome(resultSet.getString("nome"));
            a.setIcone(resultSet.getString("icone"));
            int dificuldadeId = resultSet.getInt("dificuldade_id");
            Dificuldade dificuldade = new DificuldadeWS().buscar(dificuldadeId);
            a.setDificuldade(dificuldade);
            int conteudoId = resultSet.getInt("conteudo_id");
            Conteudo conteudo = new ConteudoWS().buscar(conteudoId);
            a.setConteudo(conteudo);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        Conexao.desconectar();
    }
    
    return a;
	}
public List<Atividade> buscarPeloConteudo(int id) {
	
	List<Atividade> lista = new ArrayList<>();
	
	String sql = "select id, nome, icone, dificuldade_id, conteudo_id from atividade where conteudo_id = ?;";
	
	Connection conexao = Conexao.conectar();
	
	try {
		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, id);
		
		ResultSet resultSet = comando.executeQuery();
		
		while (resultSet.next()) {
			
			Atividade a = new Atividade();
			
			a.setId(resultSet.getInt("id"));
			a.setNome(resultSet.getString("nome"));
			a.setIcone(resultSet.getString("icone"));
			int dificuldadeId = resultSet.getInt("dificuldade_id");
            Dificuldade dificuldade = new DificuldadeWS().buscar(dificuldadeId);
            a.setDificuldade(dificuldade);
            int conteudoId = resultSet.getInt("conteudo_id");
            Conteudo conteudo = new ConteudoWS().buscar(conteudoId);
            a.setConteudo(conteudo);
            
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
