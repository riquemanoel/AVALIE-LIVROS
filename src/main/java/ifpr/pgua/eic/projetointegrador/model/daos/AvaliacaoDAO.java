package ifpr.pgua.eic.projetointegrador.model.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.Avaliacao;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Nota;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface AvaliacaoDAO {
    Result avaliar(Avaliacao avaliacao);
    List<Nota> listAllNotas();
    Nota getNota(int id);
    List<Avaliacao> listAllAvaliacaoByLivro(Livro livro);
    int getNumeroPaginaUltimaAvaliacao(Livro livro);
}
