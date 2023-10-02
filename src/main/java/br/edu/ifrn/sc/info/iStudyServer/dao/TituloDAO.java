package br.edu.ifrn.sc.info.iStudyServer.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Titulo;

public class TituloDAO {
	public boolean inserir(Titulo titulo) {

		boolean resultado = false;
		String sql = "INSERT INTO titulo(id, descricao) VALUES (?, ?);";
		Connection conexao = Conexao.conectar();

		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, titulo.getId());
			comando.setString(2, titulo.getDescricao());

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

	public boolean atualizar(Titulo titulo) {

		boolean resultado = false;
		String sql = "UPDATE titulo descricao = ? WHERE id = ?";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			
			comando.setString(1, titulo.getDescricao());
			comando.setInt(2, titulo.getId());
		
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
	
	public boolean remover(Titulo titulo) {

		boolean resultado = false;
		String sql = "DELETE FROM titulo WHERE id = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1,titulo.getId());

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


public List<Titulo> listarTodos() {
	System.out.println("Executando o método listar titulo");
	List<Titulo> lista = new ArrayList<>();
	
	String sql = "select id, descricao from titulo;";
	
	Connection conexao = Conexao.conectar();
	
	try {
		PreparedStatement comando = conexao.prepareStatement(sql);
		ResultSet resultSet = comando.executeQuery();
		
		while (resultSet.next()) {
			
			Titulo t = new Titulo();
			
			t.setId(resultSet.getInt("id"));
			t.setDescricao(resultSet.getString("descricao"));
            
			lista.add(t);
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