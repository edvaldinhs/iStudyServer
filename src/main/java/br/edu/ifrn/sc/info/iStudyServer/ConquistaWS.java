package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.sc.info.iStudyServer.dao.ConquistaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Conquista;

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
	
}
