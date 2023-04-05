package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.AvaliacaoRow;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAtuLivroViewModel;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaAtuLivroController extends BaseController implements Initializable{
    
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

    @FXML
    private TableView<AvaliacaoRow> tbAvaliacoes;

    @FXML
    private TableColumn<AvaliacaoRow,String> tbcNota;

    @FXML
    private TableColumn<AvaliacaoRow,String> tbcCometario;

    @FXML
    private TableColumn<AvaliacaoRow,String> tbcNumeroPaginasLidas;

    private TelaAtuLivroViewModel viewModel;

    public TelaAtuLivroController(TelaAtuLivroViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Set as celulas relacionadas a livro  
        this.tbcNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        this.tbcCometario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        this.tbcNumeroPaginasLidas.setCellValueFactory(new PropertyValueFactory<>("numero_paginas_lidas"));
        this.tbAvaliacoes.setItems(viewModel.getAvaliacoes());

        this.tfTitulo.textProperty().bindBidirectional(viewModel.getTituloProperty());
        this.tfAutor.textProperty().bindBidirectional(viewModel.getAutorProperty());
        this.tfNumero_paginas_livro.textProperty().bindBidirectional(viewModel.getNumero_paginas_livroProperty());
        this.tfAno_lancamento.textProperty().bindBidirectional(viewModel.getAno_lancamentoProperty());
        
        this.cbGenero.setItems(viewModel.getGeneros());
        this.cbTag.setItems(viewModel.getTags());

        this.cbGenero.valueProperty().bindBidirectional(viewModel.getOpGenero());
        this.cbTag.valueProperty().bindBidirectional(viewModel.getOpTag());

        viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });
    }

    @FXML
    private void voltar(){
        App.popScreen();
    }

    @FXML
    private void desativar(){
        if (viewModel.desativar()) App.popScreen();
    }

    @FXML
    private void atualizar(){
        if (viewModel.atualizar()) App.popScreen();
    }

}
