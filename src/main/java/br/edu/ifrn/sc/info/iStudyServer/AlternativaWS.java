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

import br.edu.ifrn.sc.info.iStudyServer.dao.AlternativaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Alternativa;

@Path("alternativas")
public class AlternativaWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alternativa> listarTodas() {
		
		System.out.println("Executando o método listar alternativas");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.listarTodas();
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Alternativa buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar alternativa");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.buscar(id);

	}
	
	@GET
	@Path("/buscarPorQuestao/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alternativa> buscarPorQuestao(@PathParam("id") int id) {

		System.out.println("Executando o método buscar alternativa");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.buscarPorQuestao(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Alternativa alternativa) {
		
		System.out.println("Executando o método inserir alternativa");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.inserir(alternativa);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Alternativa alternativa) {
		
		System.out.println("Executando o método atualizar alternativa");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.atualizar(alternativa);
	}
	
	@DELETE
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover alternativa");
		AlternativaDAO dao = new AlternativaDAO();
		return dao.remover(id);
	}

}
