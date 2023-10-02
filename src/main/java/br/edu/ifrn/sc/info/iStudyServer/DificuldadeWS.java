package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.sc.info.iStudyServer.dao.DificuldadeDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Dificuldade;

@Path("dificuldades")
public class DificuldadeWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dificuldade> listarTodas() {

		System.out.println("Executando o método listar dificuldades");
		DificuldadeDAO dao = new DificuldadeDAO();
		return dao.listarTodas();

	}

	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Dificuldade buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar dificuldade");
		DificuldadeDAO dao = new DificuldadeDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Dificuldade dificuldade) {
		
		DificuldadeDAO dao = new DificuldadeDAO();
		
		return dao.inserir(dificuldade);
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover dificuldade");
		DificuldadeDAO dao = new DificuldadeDAO();
		return dao.remover(id);
		
	}

}
