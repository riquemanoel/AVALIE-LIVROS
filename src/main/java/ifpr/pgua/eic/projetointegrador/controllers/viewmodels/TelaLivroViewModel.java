package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
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

public class TelaLivroViewModel {

    private StringProperty tituloProperty = new SimpleStringProperty();
    private StringProperty autorProperty = new SimpleStringProperty();
    private StringProperty ano_lancamentoProperty = new SimpleStringProperty();
    private StringProperty numero_paginas_livroProperty = new SimpleStringProperty();
    // é a coisa selecionada
    private ObjectProperty<Genero> opGenero = new SimpleObjectProperty<>();
    private ObjectProperty<Tag> opTag = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    // lista de generos
    private ObservableList<Genero> generos = FXCollections.observableArrayList();
    private ObservableList<Tag> tags = FXCollections.observableArrayList();

    private LivroRepository livroRepository;
    private ClienteRepository clienteRepository; 

    public TelaLivroViewModel(LivroRepository livroRepository, ClienteRepository clienteRepository) {
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        updateList();
    }

    public void updateList() {
        generos.clear();
        generos.addAll(livroRepository.getGeneros());

        tags.clear();
        tags.addAll(livroRepository.getTags());
    }

    public boolean criar(){
        String titulo = tituloProperty.getValue();
        String autor = autorProperty.getValue();
        int ano_lancamento = 0;
        int numero_paginas_livro = 0;
        // pega o valor de opGenero e coloca na entidade genero
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


        Result msg = livroRepository.criar(titulo, autor, ano_lancamento, numero_paginas_livro, genero, tag, cliente);
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        tituloProperty.setValue("");
        autorProperty.setValue("");
        ano_lancamentoProperty.setValue("");
        numero_paginas_livroProperty.setValue("");
        opGenero.setValue(null);
        opTag.setValue(null);
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

    // Faz um objeto de propriedade da entidade genero, pegando o genero selecionado e retornando ele 
    public ObjectProperty<Genero> getOpGenero() {
        return opGenero;
    }

    public ObjectProperty<Tag> getOpTag() {
        return opTag;
    }

    // retorna a propriedade de objeto result, pegando o AlertProperty 
    public ObjectProperty<Result> getAlertProperty() {
        return alertProperty;
    }

    // Faz um ObservableList de generos, pegando os generos
    public ObservableList<Genero> getGeneros() {
        return generos;
    }

    public ObservableList<Tag> getTags() {
        return tags;
    }
    
}
    
