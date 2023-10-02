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

import br.edu.ifrn.sc.info.iStudyServer.dao.AtividadeDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Atividade;


@Path("atividades")
public class AtividadeWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atividade> listarTodas() {
		
		System.out.println("Executando o método listar atividades");
		AtividadeDAO dao = new AtividadeDAO();
		return dao.listarTodas();
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Atividade buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar atividades");
		AtividadeDAO dao = new AtividadeDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Atividade atividade) {
		
		System.out.println("Executando o método inserir atividade");
		AtividadeDAO dao = new AtividadeDAO();
		return dao.inserir(atividade);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Atividade atividade) {
		
		System.out.println("Executando o método atualizar atividade");
		AtividadeDAO dao = new AtividadeDAO();
		return dao.atualizar(atividade);
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover atividade");
		AtividadeDAO dao = new AtividadeDAO();
		return dao.remover(id);
	}
		

}
