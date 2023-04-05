package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.entities.Avaliacao;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.repositories.AvaliacaoRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.LivroRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaAtuLivroViewModel {

    private StringProperty notaProperty = new SimpleStringProperty();
    private StringProperty comentarioProperty = new SimpleStringProperty();
    private StringProperty numero_paginas_lidasProperty = new SimpleStringProperty();
    private StringProperty tituloProperty = new SimpleStringProperty();
    private StringProperty autorProperty = new SimpleStringProperty();
    private StringProperty ano_lancamentoProperty = new SimpleStringProperty();
    private StringProperty numero_paginas_livroProperty = new SimpleStringProperty();
    private ObjectProperty<Genero> opGenero = new SimpleObjectProperty<>();
    private ObjectProperty<Tag> opTag = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ObservableList<Genero> generos = FXCollections.observableArrayList();
    private ObservableList<Tag> tags = FXCollections.observableArrayList();
    private ObservableList<AvaliacaoRow> avaliacoes = FXCollections.observableArrayList();
    
    private AvaliacaoRepository avaliacaoRepository;
    private LivroRepository livroRepository;
    private ClienteRepository clienteRepository;

    public TelaAtuLivroViewModel(AvaliacaoRepository avaliacaoRepository, LivroRepository livroRepository,
            ClienteRepository clienteRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        updateList();
    }

    public void updateList(){
        generos.clear();
        generos.addAll(livroRepository.getGeneros());

        tags.clear();
        tags.addAll(livroRepository.getTags());

        tituloProperty.setValue(livroRepository.getLivroSelecionado().getTitulo());
        autorProperty.setValue(livroRepository.getLivroSelecionado().getAutor());
        numero_paginas_livroProperty.setValue(String.valueOf(livroRepository.getLivroSelecionado().getNumero_paginas_livro()));
        ano_lancamentoProperty.setValue(String.valueOf(livroRepository.getLivroSelecionado().getAno_lancamento()));
        opGenero.setValue(livroRepository.getLivroSelecionado().getGenero());
        opTag.setValue(livroRepository.getLivroSelecionado().getTag());

        avaliacoes.clear();
        for (Avaliacao avaliacao : avaliacaoRepository.getAvaliacoes(livroRepository.getLivroSelecionado())) {
            avaliacoes.add(new AvaliacaoRow(avaliacao));
        }

    }

    public boolean desativar() {
        Result msg = livroRepository.desativar();
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }
    
    public boolean atualizar() {
        String titulo = tituloProperty.getValue();
        String autor = autorProperty.getValue();
        int ano_lancamento = 0;
        int numero_paginas_livro = 0;
        Genero genero = opGenero.getValue();
        Tag tag = opTag.getValue();
        Cliente cliente = clienteRepository.getClienteLogado();
        
        try{
            ano_lancamento = Integer.parseInt(ano_lancamentoProperty.getValue());
        }catch(NumberFormatException e){
            alertProperty.setValue(Result.fail("Ano lançamento inválido!"));
        }

        try{
            numero_paginas_livro = Integer.parseInt(numero_paginas_livroProperty.getValue());
        }catch(NumberFormatException e){
            alertProperty.setValue(Result.fail("Número de páginas lidas inválido!"));
        }

        Result msg = livroRepository.atualizar(titulo, autor, numero_paginas_livro, ano_lancamento, genero, tag, cliente);
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        notaProperty.setValue(null);
        comentarioProperty.setValue("");
        numero_paginas_lidasProperty.setValue("0");
        tituloProperty.setValue("");
        autorProperty.setValue("");
        ano_lancamentoProperty.setValue("");
        numero_paginas_livroProperty.setValue("0");
        opGenero.setValue(null);
        opTag.setValue(null);
    }

    public StringProperty getNotaProperty() {
        return notaProperty;
    }


    public StringProperty getComentarioProperty() {
        return comentarioProperty;
    }


    public StringProperty getNumero_paginas_lidasProperty() {
        return numero_paginas_lidasProperty;
    }


    public StringProperty getTituloProperty() {
        return tituloProperty;
    }


    public StringProperty getAutorProperty() {
        return autorProperty;
    }


    public StringProperty getAno_lancamentoProperty() {
        return ano_lancamentoProperty;
    }


    public StringProperty getNumero_paginas_livroProperty() {
        return numero_paginas_livroProperty;
    }


    public ObjectProperty<Genero> getOpGenero() {
        return opGenero;
    }


    public ObjectProperty<Tag> getOpTag() {
        return opTag;
    }


    public ObjectProperty<Result> getAlertProperty() {
        return alertProperty;
    }


    public ObservableList<Genero> getGeneros() {
        return generos;
    }


    public ObservableList<Tag> getTags() {
        return tags;
    }


    public AvaliacaoRepository getAvaliacaoRepository() {
        return avaliacaoRepository;
    }

    public ObservableList<AvaliacaoRow> getAvaliacoes(){
        return avaliacoes;
    }

}
