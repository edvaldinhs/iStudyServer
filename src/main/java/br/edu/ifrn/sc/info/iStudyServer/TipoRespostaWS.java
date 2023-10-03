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

import br.edu.ifrn.sc.info.iStudyServer.dao.TipoRespostaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.TipoResposta;

@Path("tipoRespostas")
public class TipoRespostaWS {
	
	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TipoResposta> listarTodas() {
		
		System.out.println("Executando o método listar tipos de respostas");
		TipoRespostaDAO dao = new TipoRespostaDAO();
		return dao.listarTodas();
		
	}
	
	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TipoResposta buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar tipo de resposta");
		TipoRespostaDAO dao = new TipoRespostaDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(TipoResposta tipoResposta) {
		
		System.out.println("Executando o método inserir tipo de resposta");
		TipoRespostaDAO dao = new TipoRespostaDAO();
		return dao.inserir(tipoResposta);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean atualizar(TipoResposta tipoResposta) {
		
		System.out.println("Executando o método atualizar tipo de resposta");
		TipoRespostaDAO dao = new TipoRespostaDAO();
		return dao.atualizar(tipoResposta);
	}
	
	@DELETE
	@Path("/remover")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("id") int id) {
		
		System.out.println("Executando o método remover tipo de resposta");
		TipoRespostaDAO dao = new TipoRespostaDAO();
		return dao.remover(id);
	}

}
