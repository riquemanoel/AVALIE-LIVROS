package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.LivroRow;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaPrincipalViewModel;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaPrincipalController extends BaseController implements Initializable{

    @FXML
    private Label lbUsername;

    @FXML
    private Label lbTotal_paginas_lidas;

    @FXML
    private Button btAvaliar;

    @FXML
    private Button btInfoLivro;

    @FXML
    private ComboBox<Tag> cbTag;

    @FXML
    private TextField tfPesquisa;

    // Set a TableView da tabela Livro
    @FXML
    private TableView<LivroRow> tbLivros;

    @FXML
    private TableColumn<LivroRow,String> tbcTitulo;

    @FXML
    private TableColumn<LivroRow,String> tbcAutor;

    @FXML
    private TableColumn<LivroRow,String> tbcPaginas;

    @FXML
    private TableColumn<LivroRow,String> tbcAno_lancamento;

    @FXML
    private TableColumn<LivroRow,String> tbcGenero;

    @FXML
    private TableColumn<LivroRow,String> tbcTag;

    private TelaPrincipalViewModel viewModel;

    public TelaPrincipalController(TelaPrincipalViewModel viewModel) {
        this.viewModel = viewModel;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Set as celulas relacionadas a livro  
        this.tbcTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.tbcAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.tbcPaginas.setCellValueFactory(new PropertyValueFactory<>("numero_paginas_livro"));
        this.tbcAno_lancamento.setCellValueFactory(new PropertyValueFactory<>("ano_lancamento"));
        this.tbcGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        this.tbcTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
        this.tbLivros.setItems(viewModel.getLivros());

        this.lbUsername.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        this.lbTotal_paginas_lidas.textProperty().bindBidirectional(viewModel.getTotal_paginas_lidasProperty());
    
        this.tfPesquisa.textProperty().bindBidirectional(viewModel.getPesquisaProperty());

        this.cbTag.setItems(viewModel.getTags());
        this.cbTag.valueProperty().bindBidirectional(viewModel.getOpTag());

        this.btAvaliar.setDisable(true);
        this.btInfoLivro.setDisable(true);

        this.viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });
    }

    @FXML
    private void sair(){
        App.popScreen();
    }

    @FXML
    private void cadLivro(){
        App.pushScreen("LIVRO");
        this.btAvaliar.setDisable(true);
        this.btInfoLivro.setDisable(true);
    }

    @FXML
    private void cadAvaliar(){
        App.pushScreen("AVALIACAO");
        this.btAvaliar.setDisable(true);
        this.btInfoLivro.setDisable(true);
    }

    @FXML
    private void atuCliente(){
        App.pushScreen("ATU_CLIENTE");
        this.btAvaliar.setDisable(true);
        this.btInfoLivro.setDisable(true);
    }

    @FXML
    private void infoLivro(){
        App.pushScreen("INFO_LIVRO");
        this.btAvaliar.setDisable(true);
        this.btInfoLivro.setDisable(true);
    }

    @FXML
    private void buscar(){
        tbLivros.getItems().clear();
        tbLivros.getItems().addAll(viewModel.getLivrosByTitulo());    
    }

    @FXML
    private void filtrar(){
        tbLivros.getItems().clear();
        tbLivros.getItems().addAll(viewModel.getLivrosByTag());    
    }

    @FXML
    private void selecionouLivro(){
        if(viewModel.selecionouLivro(tbLivros.getSelectionModel().getSelectedItem())){
            btAvaliar.setDisable(false);
            btInfoLivro.setDisable(false);
        }
    }

    @FXML
    private void atualizar(){
        viewModel.updateList();
        this.tbLivros.setItems(viewModel.getLivros());
    }

}
