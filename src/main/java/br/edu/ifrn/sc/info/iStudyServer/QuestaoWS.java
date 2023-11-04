package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.sc.info.iStudyServer.dao.QuestaoDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Questao;

@Path("questoes")
public class QuestaoWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Questao> listarTodas() {
		
		System.out.println("Executando o método listar questões");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.listarTodas();
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Questao buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar questão");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.buscar(id);

	}
	
	@GET
	@Path("/buscarPorAtividade/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Questao> buscarPorAtividade(@PathParam("id") int id) {

		System.out.println("Executando o método buscar questão");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.buscarPorAtividade(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Questao questao) {
		
		System.out.println("Executando o método inserir questão");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.inserir(questao);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Questao questao) {
		
		System.out.println("Executando o método atualizar questão");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.atualizar(questao);
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover questão");
		QuestaoDAO dao = new QuestaoDAO();
		return dao.remover(id);
	}

}
