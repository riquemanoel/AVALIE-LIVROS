package ifpr.pgua.eic.projetointegrador.model.repositories;

import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.daos.LivroDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class LivroRepository {

    private Livro livroSelecionado;
    private LivroDAO dao;
    private List<Genero> generos;
    private List<Tag> tags;

    public LivroRepository(LivroDAO dao) {
        this.dao = dao;
    }

    //CRIA LIVRO E VERIFICA SE AS INFORMAÇÕES NÃO SÃO VAZIAS 
    public Result criar(String titulo, String autor, int ano_lancamento, int numero_paginas_livro, Genero genero, Tag tag, Cliente cliente){
        
        if (!(titulo.equals("")) && !(autor.equals("")) && !(numero_paginas_livro == 0) && !(genero == null) && !(tag == null) && !(cliente == null)) {
            Livro livro = new Livro(titulo, autor, ano_lancamento, numero_paginas_livro, genero, tag, cliente);
            return dao.criar(livro);
        }

        return Result.fail("Preencha todos os campos!");
        
    }


    public List<Genero> getGeneros(){
        generos = dao.listAllGeneros();
        return Collections.unmodifiableList(generos);
    }

    public List<Tag> getTags(){
        tags = dao.listAllTags();
        return Collections.unmodifiableList(tags);
    }

    //RETORNA LISTA DE LIVROS LINKADO AO USUARIO
    public List<Livro> getLivros(Cliente clienteLogado){
        return dao.listAll(clienteLogado);
    }

    //DEFINIR LIVRO SELECIONADO
    public Result setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
        if (livroSelecionado == null) {
            return Result.fail("Selecione um livro!");
        }
        return Result.success("Selecionou livro!");
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setAvaliandoLivro(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }

    public List<Livro> getLivrosByTitulo(String busca, Cliente clienteLogado){
        return dao.listAllTitulo(busca, clienteLogado);
    }

    public Result desativar(){
        return dao.desativar(livroSelecionado);
    }

    //ATUALIZA INFO LIVRO
    public Result atualizar(String novoTitulo, String novoAutor, int novaNumeroPaginas, int novoAnoLancamento, Genero genero, Tag tag, Cliente cliente){

        if (!(novoTitulo.equals("")) && !(novoAutor.equals("")) && !(novaNumeroPaginas == 0) && !(novoAnoLancamento == 0) && !(tag == null) && !(genero == null)) {
            Livro livro = new Livro(livroSelecionado.getId(), novoTitulo, novoAutor, novoAnoLancamento, novaNumeroPaginas, genero, tag, cliente);
            return dao.atualizar(livro);
        }

        return Result.fail("Preencha todos os campos!");
    }

}
