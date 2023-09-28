package br.edu.ifrn.sc.info.iStudyServer.teste;

import br.edu.ifrn.sc.info.iStudyServer.DisciplinaWS;
import br.edu.ifrn.sc.info.iStudyServer.dominio.Disciplina;

public class TesteDelete {

	public static void main(String[] args) {
		
		new DisciplinaWS().remover(3);

	}

}
