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

import br.edu.ifrn.sc.info.iStudyServer.dao.ConquistaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dao.DisciplinaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Conquista;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;

@Path("conquistas")
public class ConquistaWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conquista> listarTodas() {
		
		System.out.println("Executando o método listar conquistas");
		ConquistaDAO dao = new ConquistaDAO();
		return dao.listarTodas();
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Conquista buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar conquista");
		ConquistaDAO dao = new ConquistaDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Conquista conquista) {
		
		ConquistaDAO dao = new ConquistaDAO();
		
		return dao.inserir(conquista);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Conquista conquista) {
		
		System.out.println("Executando o método atualizar conquista");
		ConquistaDAO dao = new ConquistaDAO();
		return dao.atualizar(conquista);
	}
	
	
	@DELETE
	@Path("/remover/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover conquista");
		ConquistaDAO dao = new ConquistaDAO();
		return dao.remover(id);
		
	}
	
	
}
