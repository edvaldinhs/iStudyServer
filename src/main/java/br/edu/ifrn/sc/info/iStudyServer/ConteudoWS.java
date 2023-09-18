package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	
}
