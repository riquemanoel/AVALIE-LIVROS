package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaLivroViewModel;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TelaLivroController extends BaseController implements Initializable{

    // set os valores mutáveis
    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfAutor;

    @FXML
    private TextField tfNumero_paginas_livro;

    @FXML
    private TextField tfAno_lancamento; 

    @FXML
    private ComboBox<Genero> cbGenero;

    @FXML
    private ComboBox<Tag> cbTag;

    private TelaLivroViewModel viewModel;

    public TelaLivroController(TelaLivroViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Faz uma conexão com viewModel
        this.tfTitulo.textProperty().bindBidirectional(viewModel.getTituloProperty());
        this.tfAutor.textProperty().bindBidirectional(viewModel.getAutorProperty());
        this.tfNumero_paginas_livro.textProperty().bindBidirectional(viewModel.getNumero_paginas_livroProperty());
        this.tfAno_lancamento.textProperty().bindBidirectional(viewModel.getAno_lancamentoProperty());
        
        // Set os itens que são pegos da viewModel Generos
        this.cbGenero.setItems(viewModel.getGeneros());
        this.cbGenero.valueProperty().bindBidirectional(viewModel.getOpGenero());

        this.cbTag.setItems(viewModel.getTags());
        this.cbTag.valueProperty().bindBidirectional(viewModel.getOpTag());

        // Ta escutando e mostra a mensagem
        viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        viewModel.limpar();
    }

    @FXML
    private void voltar(){
        App.popScreen();
    }

    @FXML
    private void cadastrar(){
        if (viewModel.criar()) voltar();
    }
}
