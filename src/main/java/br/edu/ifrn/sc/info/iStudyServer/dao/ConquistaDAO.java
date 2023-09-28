package br.edu.ifrn.sc.info.iStudyServer.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Conquista;

public class ConquistaDAO {
	
	public boolean inserir(Conquista conquista) {

		boolean resultado = false;
		String sql = "INSERT INTO conquista(id, icone, nome, descricao, data_conquista) VALUES (?,?,?,?,?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, conquista.getId());
			comando.setString(2, conquista.getIcone());
			comando.setString(3, conquista.getNome());
			comando.setString(4, conquista.getDescricao());
			comando.setString(5, conquista.getDataConquista());

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

	public boolean atualizar(Conquista conquista) {

		boolean resultado = false;
		String sql = "UPDATE conquista SET icone = ?, nome = ?, descricao = ?, data_conquista = ? where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, conquista.getIcone());
			comando.setString(2, conquista.getNome());
			comando.setString(3, conquista.getDescricao());
			comando.setString(4, conquista.getDataConquista());
			comando.setInt(5, conquista.getId());


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
	
	public boolean remover(Conquista conquista) {

		boolean resultado = false;
		String sql = "DELETE FROM conquista where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, conquista.getId());


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
	
public List<Conquista> listarTodas() {
		
		List<Conquista> lista = new ArrayList<>();
		
		String sql = "select id, icone, nome, descricao, data_conquista from conquista;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Conquista c = new Conquista();
				
				c.setId(resultSet.getInt("id"));
				c.setIcone(resultSet.getString("icone"));
				c.setNome(resultSet.getString("nome"));
				c.setDescricao(resultSet.getString("descricao"));
				c.setDataConquista(resultSet.getString("data_conquista"));
	            
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
	public Conquista buscar(int id) {
		
		Conquista c = null;
		
		String sql = "select id, nome from conquista where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				c = new Conquista();
				
				c.setId(resultSet.getInt("id"));
				c.setNome(resultSet.getString("nome"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return c;
	}
}
