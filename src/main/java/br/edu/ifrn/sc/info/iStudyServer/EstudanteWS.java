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

import br.edu.ifrn.sc.info.iStudyServer.dao.EstudanteDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Estudante;

@Path("estudantes")
public class EstudanteWS {
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudante> listarTodos() {
		
		System.out.println("Executando o método listar estudantes");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.listarTodos();
		
	}
	
	@GET
	@Path("/buscar/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudante buscar(@PathParam("email") String email) {

		System.out.println("Executando o método buscar estudante");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.buscar(email);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Estudante estudante) {
		
		System.out.println("Executando o método inserir estudante");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.inserir(estudante);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Estudante estudante) {
		
		System.out.println("Executando o método atualizar estudante");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.atualizar(estudante);
	}
	
	@DELETE
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover estudante");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.remover(id);
	}

}
