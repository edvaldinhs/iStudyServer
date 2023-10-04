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

import br.edu.ifrn.sc.info.iStudyServer.dao.TituloDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Titulo;

@Path("titulos")
public class TituloWS {
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Titulo> listarTodos() {
		
		System.out.println("Executando o método listar titulo");
		TituloDAO dao = new TituloDAO();
		return dao.listarTodos();
		
	}

	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Titulo buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar titulo");
		TituloDAO dao = new TituloDAO();
		return dao.buscar(id);

	}

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Titulo titulo) {
		
		TituloDAO dao = new TituloDAO();
		
		return dao.inserir(titulo);
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover titulo");
		TituloDAO dao = new TituloDAO();
		return dao.remover(id);
		
	}

}