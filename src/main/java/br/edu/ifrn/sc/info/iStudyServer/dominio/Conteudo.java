package br.edu.ifrn.sc.info.iStudyServer.dominio;

public class Conteudo {

	private int id;
	private String nome;
	private String resumo;
	private String dataInicio;
	private String dataFim;
	private String imagem;
	private Disciplina disciplina;
	private Boolean bloqueado;
	
	public Conteudo() {

	}

	public Conteudo(int id, String nome, String resumo, String dataInicio, String dataFim, String imagem, Disciplina disciplina, Boolean bloqueado) {
		this.id = id;
		this.nome = nome;
		this.resumo = resumo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.imagem = imagem;
		this.disciplina = disciplina;
		this.bloqueado = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String data_inicio) {
		this.dataInicio = data_inicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String data_fim) {
		this.dataFim = data_fim;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = true;
	}
	
	public boolean conteudoDesbloquear() {
		if (!this.bloqueado) {
	        return false;
	    } else {
	        this.bloqueado = false;
	        return true;
	    }
	}
	
	public boolean estaDesbloqueado(Estudante estudante) {
        return !this.bloqueado || estudante.getConteudosDesbloqueados().contains(this);
    }

}
