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

import br.edu.ifrn.sc.info.iStudyServer.dao.ConteudoDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Conteudo;

@Path("conteudos")
public class ConteudoWS {
	
	@GET
	@Path("/listarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conteudo> listarTodos() {
		
		System.out.println("Executando o método listar conteudos");
		ConteudoDAO dao = new ConteudoDAO();
		return dao.listarTodos();
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Conteudo buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar conteudos");
		ConteudoDAO dao = new ConteudoDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Conteudo conteudo) {
		
		System.out.println("Executando o método inserir conteudo");
		ConteudoDAO dao = new ConteudoDAO();
		return dao.inserir(conteudo);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(Conteudo conteudo) {
		
		System.out.println("Executando o método atualizar conteudo");
		ConteudoDAO dao = new ConteudoDAO();
		return dao.atualizar(conteudo);
	}
	
	@DELETE
	@Path("/remover/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover conteudo");
		ConteudoDAO dao = new ConteudoDAO();
		return dao.remover(id);
	}
	
}
