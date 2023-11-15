package br.edu.ifrn.sc.info.iStudyServer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;

import br.edu.ifrn.sc.info.iStudyServer.dominio.Estudante;
import br.edu.ifrn.sc.info.iStudyServer.dominio.EstudanteAtividade;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Titulo;

public class EstudanteDAO {
	
	public boolean inserir(Estudante estudante) {

		boolean resultado = false;
		String sql = "INSERT INTO estudante(email, pontuacao_total, nome, senha, foto, titulo_id) VALUES (?, ?, ?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, estudante.getEmail());
			comando.setInt(2, estudante.getPontuacao());
			comando.setString(3, estudante.getNome());
			comando.setString(4, estudante.getSenha());
			comando.setString(5, estudante.getFoto());
			comando.setInt(6, estudante.getTitulo().getId());

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
	
	public boolean cadastrarEstudante(@FormParam("nome") String nome,
									@FormParam("email") String email,
									@FormParam("senha") String senhaInserida) {

		boolean resultado = false;
		String sql = "INSERT INTO estudante(email, nome, senha) VALUES (?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, email);
			comando.setString(2, nome);
			comando.setString(3, senhaInserida);

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

	public boolean atualizar(Estudante estudante) {

		boolean resultado = false;
		String sql = "UPDATE estudante SET pontuacao_total = ?, nome = ?, senha = ?, foto = ?, titulo_id = ? where email = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, estudante.getPontuacao());
			comando.setString(2, estudante.getNome());
			comando.setString(3, estudante.getSenha());
			comando.setString(4, estudante.getFoto());
			comando.setInt(5, estudante.getTitulo().getId());
			comando.setString(6, estudante.getEmail());
			
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
	
	public boolean remover(String email) {

		boolean resultado = false;
		String sql = "DELETE FROM estudante where email = ?;";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, email);

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
	
public List<Estudante> listarTodos() {
		
		List<Estudante> lista = new ArrayList<>();
		
		String sql = "select email, pontuacao_total, nome, senha, foto, titulo_id from estudante ;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet resultSet = comando.executeQuery();
			
			while (resultSet.next()) {
				
				Estudante es = new Estudante();
				
				es.setEmail(resultSet.getString("email"));
				es.setPontuacao(resultSet.getInt("pontuacao_total"));
				es.setNome(resultSet.getString("nome"));
				es.setSenha(resultSet.getString("senha"));
				es.setFoto(resultSet.getString("foto"));
				Titulo titulo = new Titulo();
	            titulo.setId(resultSet.getInt("titulo_id"));
	            es.setTitulo(titulo);
	            
				lista.add(es);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return lista;
	}

	public Estudante buscar(String email) {
	
		Estudante es = null;
		
		String sql = "select email, pontuacao_total, nome, senha, foto, titulo_id from estudante where email = ?;";
		
		Connection conexao = Conexao.conectar();
		
		try {
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, email);
			
			ResultSet resultSet = comando.executeQuery();
			
			if (resultSet.next()) {
				
				es = new Estudante();
				
				es.setEmail(resultSet.getString("email"));
				es.setPontuacao(resultSet.getInt("pontuacao_total"));
				es.setNome(resultSet.getString("nome"));
				es.setSenha(resultSet.getString("senha"));
				es.setFoto(resultSet.getString("foto"));
				Titulo titulo = new Titulo();
	            titulo.setId(resultSet.getInt("titulo_id"));
	            es.setTitulo(titulo);
	            
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			Conexao.desconectar();
		}
		
		return es;
	}
	public boolean verificarUsuario(String email, String senhaInserida) {
		
	    boolean resultado = false;
	    String senha = "";

	    String sql = "SELECT senha FROM estudante WHERE email = ?;";

	    try (Connection conexao = Conexao.conectar();
	         PreparedStatement comando = conexao.prepareStatement(sql)) {

	        comando.setString(1, email);

	        try (ResultSet resultSet = comando.executeQuery()) {
	            if (resultSet.next()) {
	                senha = resultSet.getString("senha");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if (senhaInserida.equals(senha)) {
	        resultado = true;
	    }

	    return resultado;
	}
	
	public boolean registrarProgresso(EstudanteAtividade estudanteAtividade) {

		boolean resultado = false;
		String sql = "INSERT INTO estudante_atividade (estudante_email, atividades_id, num_acertos, num_erros, pontuacao_atividade) VALUES (?, ?, ?, ?, ?);";
		Connection conexao = Conexao.conectar();

		try {

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, estudanteAtividade.getEmail());
			comando.setInt(2, estudanteAtividade.getAtividadeId());
			comando.setInt(3, estudanteAtividade.getNumAcertos());
			comando.setInt(4, estudanteAtividade.getNumErros());
			comando.setInt(5, estudanteAtividade.getPontuacaoTotal());
			
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

}
