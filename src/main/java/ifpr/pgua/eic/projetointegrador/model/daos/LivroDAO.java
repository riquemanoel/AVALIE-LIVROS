package ifpr.pgua.eic.projetointegrador.model.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

// Definição caso de uso
public interface LivroDAO {
    Result criar(Livro livro);
    List<Genero> listAllGeneros();
    Genero getGenero(int id);
    List<Tag> listAllTags();
    Tag getTag(int id);
    List<Livro> listAll(Cliente clienteLogado);
    List<Livro> listAllTitulo(String busca, Cliente clienteLogado);
    Result desativar(Livro livro);
    Result atualizar(Livro livro);
}
