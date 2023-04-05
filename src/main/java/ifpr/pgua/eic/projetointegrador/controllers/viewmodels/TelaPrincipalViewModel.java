package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;


import java.util.List;
import java.util.stream.Collectors;

import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
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

public class TelaPrincipalViewModel {

    private StringProperty pesquisaProperty = new SimpleStringProperty("");
    private StringProperty tituloProperty = new SimpleStringProperty();
    private StringProperty autorProperty = new SimpleStringProperty();
    private StringProperty paginasProperty = new SimpleStringProperty();
    private StringProperty ano_lancamentoProperty = new SimpleStringProperty();
    private StringProperty generoProperty = new SimpleStringProperty();
    private StringProperty tagProperty = new SimpleStringProperty();
    private StringProperty usernameProperty = new SimpleStringProperty();
    private StringProperty total_paginas_lidasProperty = new SimpleStringProperty();

    private ObjectProperty<Tag> opTag = new SimpleObjectProperty<>();
    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ObservableList<Tag> tags = FXCollections.observableArrayList();
    private ObservableList<LivroRow> livros = FXCollections.observableArrayList();

    private ClienteRepository clienteRepository ;
    private LivroRepository livroRepository;

    public TelaPrincipalViewModel(ClienteRepository clienteRepository, LivroRepository livroRepository) {
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;

        Tag tagTodos = new Tag(0, "Todos");
        tags.clear();
        tags.add(tagTodos);
        tags.addAll(livroRepository.getTags());
        opTag.setValue(tagTodos);
        
        updateList();
    }

    public void updateList() {
        clienteRepository.logar(clienteRepository.getClienteLogado().getEmail(), clienteRepository.getClienteLogado().getSenha());
        usernameProperty.setValue(clienteRepository.getClienteLogado().getNome());
        total_paginas_lidasProperty.setValue(String.valueOf(clienteRepository.getClienteLogado().getPaginas_lidas()));

        livros.clear();
        for (Livro livro : livroRepository.getLivros(clienteRepository.getClienteLogado())) {
            livros.add(new LivroRow(livro));
        }
    }

    public ObservableList<LivroRow> getLivros(){
        ObservableList<LivroRow> test = FXCollections.observableArrayList();
        test.addAll(livros);
        return test;
    }

    public List<LivroRow> getLivrosByTitulo(){      
        return getLivros().stream().filter((livroRow)->livroRow.getLivro().getTitulo().startsWith(pesquisaProperty.getValue())).collect(Collectors.toList());        
    }

    public List<LivroRow> getLivrosByTag(){   
        if (opTag.getValue().getTag() == "Todos") {
            return getLivros();
        }
        return getLivros().stream().filter((livroRow)->livroRow.getLivro().getTag().getTag().startsWith(opTag.getValue().getTag())).collect(Collectors.toList());        
    }

    public boolean selecionouLivro(LivroRow livro){
        if (livro == null) return false;
        return livroRepository.setLivroSelecionado(livro.getLivro()) instanceof SuccessResult;
    }

    public StringProperty getPesquisaProperty() {
        return pesquisaProperty;
    }

    public StringProperty getTituloProperty() {
        return tituloProperty;
    }

    public StringProperty getAutorProperty() {
        return autorProperty;
    }

    public StringProperty getPaginasProperty() {
        return paginasProperty;
    }

    public StringProperty getAno_lancamentoProperty() {
        return ano_lancamentoProperty;
    }

    public StringProperty getGeneroProperty() {
        return generoProperty;
    }

    public StringProperty getTagProperty() {
        return tagProperty;
    }

    public StringProperty getUsernameProperty() {
        return usernameProperty;
    }

    public StringProperty getTotal_paginas_lidasProperty() {
        return total_paginas_lidasProperty;
    }

    public ObjectProperty<Tag> getOpTag() {
        return opTag;
    }

    public ObjectProperty<Result> getAlertProperty() {
        return alertProperty;
    }

    public ObservableList<Tag> getTags() {
        return tags;
    }

}
