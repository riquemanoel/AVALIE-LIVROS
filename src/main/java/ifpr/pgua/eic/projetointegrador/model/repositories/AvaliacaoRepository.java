package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.AvaliacaoDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Avaliacao;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Nota;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class AvaliacaoRepository {

    private AvaliacaoDAO dao;
    private List<Nota> notas;

    public AvaliacaoRepository(AvaliacaoDAO dao) {
        this.dao = dao;
    }

    public Result avaliar(Livro livro, Nota nota, int numero_paginas_lidas, String comentario, Cliente cliente){

        if (comentario.equals("")) {
            comentario = "Não há comentário.";
        }
        
        if (!(livro == null) && !(nota == null) && !(cliente == null)) {
            Avaliacao avaliacao = new Avaliacao(livro, numero_paginas_lidas, nota, comentario, cliente);
            return dao.avaliar(avaliacao);
        }

        return Result.fail("Preencha todos os campos!");
    }

    public List<Nota> getNotas(){
        notas = dao.listAllNotas();
        return Collections.unmodifiableList(notas);
    }

    public List<Avaliacao> getAvaliacoes(Livro livroInfo){
        return dao.listAllAvaliacaoByLivro(livroInfo);
    }

    public int getNumeroPaginaUltimaAvaliacao(Livro livro){
        return dao.getNumeroPaginaUltimaAvaliacao(livro);
    }
        
}
