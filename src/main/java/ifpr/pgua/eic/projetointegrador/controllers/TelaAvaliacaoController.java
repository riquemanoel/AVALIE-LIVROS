package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAvaliacaoViewModel;
import ifpr.pgua.eic.projetointegrador.model.entities.Nota;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TelaAvaliacaoController extends BaseController implements Initializable{

    // Set as variáveis mutáveis
    @FXML
    private TextField tfLivro;

    @FXML
    private TextField tfPagina_lidas;

    @FXML
    private ComboBox<Tag> cbTag;

    @FXML
    private ComboBox<Nota> cbNota;

    @FXML
    private TextField tfComentario;

    private TelaAvaliacaoViewModel viewModel;

    public TelaAvaliacaoController(TelaAvaliacaoViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Faz uma conexão com viewModel
        this.tfLivro.textProperty().bindBidirectional(viewModel.getLivroProperty());
        this.tfPagina_lidas.textProperty().bindBidirectional(viewModel.getPaginas_lidasProperty());
        this.tfComentario.textProperty().bindBidirectional(viewModel.getComentarioProperty());

        this.cbNota.setItems(viewModel.getNotas());
        this.cbNota.valueProperty().bindBidirectional(viewModel.getOpNota());
        this.tfLivro.setDisable(true);
    
        // quando acontece uma inserção em AlertProperty ele vai mostra a mensagem na tela
        viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });
    }

    @FXML
    private void avaliar(){
        if(viewModel.avaliar()) voltar();
    }

    @FXML
    private void voltar(){
        App.popScreen();
    }
}
