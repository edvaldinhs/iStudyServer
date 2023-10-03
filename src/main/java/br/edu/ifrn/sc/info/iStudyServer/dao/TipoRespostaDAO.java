package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.dominio.TipoResposta;

public class TipoRespostaDAO {
	

	public boolean inserir(TipoResposta tipoResposta) {

		boolean resultado = false;
		String sql = "INSERT INTO tipo_resposta(id, descricao) VALUES (?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, tipoResposta.getId());
			comando.setString(2, tipoResposta.getDescricao());

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

	public boolean atualizar(TipoResposta tipoResposta) {

		boolean resultado = false;
		String sql = "UPDATE tipo_resposta SET descricao = ? WHERE id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, tipoResposta.getDescricao());
			comando.setInt(2, tipoResposta.getId());
		
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
		String sql = "DELETE FROM tipo_resposta where id = ?;";
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
	
	public List<TipoResposta> listarTodas() {
		
		List<TipoResposta> lista = new ArrayList<>();
		
		String sql = "select id, descricao from tipo_resposta;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				TipoResposta r = new TipoResposta();
				
				r.setId(resultSet.getInt("id"));
				r.setDescricao(resultSet.getString("descricao"));
				
				lista.add(r);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}
	public TipoResposta buscar(int id) {
		
		TipoResposta r = null;
		
		String sql = "select id, descricao from tipo_resposta where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				r = new TipoResposta();
				
				r.setId(resultSet.getInt("id"));
				r.setDescricao(resultSet.getString("descricao"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return r;
	}


}
