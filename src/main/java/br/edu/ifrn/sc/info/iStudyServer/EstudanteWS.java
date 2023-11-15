package br.edu.ifrn.sc.info.iStudyServer;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.sc.info.iStudyServer.dao.EstudanteDAO;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Estudante;
import br.edu.ifrn.sc.info.iStudyServer.dominio.EstudanteAtividade;

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
    @Path("/verificarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean verificarUsuario(@FormParam("email") String email,
                                     @FormParam("senha") String senhaInserida) {

        EstudanteDAO dao = new EstudanteDAO();
        System.out.println("Executando o método inserir verificar Estudante");
        boolean resultado = dao.verificarUsuario(email, senhaInserida);
        return resultado;
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
	
	@POST
	@Path("/cadastrarEstudante")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean cadastrarEstudante(@FormParam("nome") String nome,
	                                  @FormParam("email") String email,
	                                  @FormParam("senha") String senhaInserida) {
	    
	    System.out.println("Executando o método cadastrar estudante");
	    EstudanteDAO dao = new EstudanteDAO();
	    return dao.cadastrarEstudante(nome, email, senhaInserida);
	}

	
	@POST
	@Path("/registrarProgresso")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registrarProgresso(EstudanteAtividade estudanteAtividade) {
		
		System.out.println("Executando o método progresso conteudo");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.registrarProgresso(estudanteAtividade);
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
	@Path("/remover/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean remover(@PathParam("email") String email) {
		
		System.out.println("Executando o método remover estudante");
		EstudanteDAO dao = new EstudanteDAO();
		return dao.remover(email);
	}

}
