package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

}