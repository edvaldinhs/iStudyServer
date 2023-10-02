package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Dificuldade;

public class DificuldadeDAO {
	
	
	public boolean inserir(Dificuldade dificuldade) {

		boolean resultado = false;
		String sql = "INSERT INTO dificuldade(id, nome) VALUES (?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, dificuldade.getId());
			comando.setString(2, dificuldade.getNome());

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

	public boolean atualizar(Dificuldade dificuldade) {

		boolean resultado = false;
		String sql = "UPDATE dificuldade SET nome = ? WHERE id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, dificuldade.getNome());
			comando.setInt(2, dificuldade.getId());
			
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
		String sql = "DELETE FROM dificuldade where id = ?;";
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
	
	public List<Dificuldade> listarTodas() {
		
		List<Dificuldade> lista = new ArrayList<>();
		
		String sql = "select id, nome from dificuldade;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Dificuldade d = new Dificuldade();
				
				d.setId(resultSet.getInt("id"));
				d.setNome(resultSet.getString("nome"));
				
				lista.add(d);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}
	public Dificuldade buscar(int id) {
		
		Dificuldade d = null;
		
		String sql = "select id, nome from dificuldade where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				d = new Dificuldade();
				
				d.setId(resultSet.getInt("id"));
				d.setNome(resultSet.getString("nome"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return d;
	}

}
