package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.sc.info.iStudyServer.dao.DisciplinaDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;



@Path("disciplinas")
public class DisciplinaWS {

	@GET
	@Path("/listarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Disciplina> listarTodas() {

		System.out.println("Executando o método listar disciplinas");
		DisciplinaDAO dao = new DisciplinaDAO();
		return dao.listarTodas();

	}

	@GET
	@Path("/buscar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Disciplina buscar(@PathParam("id") int id) {

		System.out.println("Executando o método buscar disciplina");
		DisciplinaDAO dao = new DisciplinaDAO();
		return dao.buscar(id);

	}
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean inserir(Disciplina disciplina) {
		
		DisciplinaDAO dao = new DisciplinaDAO();
		
		return dao.inserir(disciplina);
	}
	
	//Call<Boolean> inserir(@Body Disciplina disciplina);

}
