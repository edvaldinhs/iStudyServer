package br.edu.ifrn.sc.info.iStudyServer.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;




public class DisciplinaDAO {
	public boolean inserir(Disciplina disciplina) {

		boolean resultado = false;
		String sql = "INSERT INTO disciplina(id, nome) VALUES (?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, disciplina.getId());
			comando.setString(2, disciplina.getNome());

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

	public boolean atualizar(Disciplina disciplina) {

		boolean resultado = false;
		String sql = "UPDATE disciplina SET id = ?, nome = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setInt(1, disciplina.getId());
			comando.setString(2, disciplina.getNome());
		
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
	
	public boolean remover(Disciplina disciplina) {

		boolean resultado = false;
		String sql = "DELETE FROM disciplina where id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, disciplina.getId());

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
	
	public List<Disciplina> listarTodas() {
		
		List<Disciplina> lista = new ArrayList<>();
		
		String sql = "select id, nome from disciplina;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Disciplina d = new Disciplina();
				
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
	public Disciplina buscar(int id) {
		
		Disciplina d = null;
		
		String sql = "select id, nome from disciplina where id = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, id);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				d = new Disciplina();
				
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