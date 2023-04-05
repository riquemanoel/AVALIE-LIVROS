package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAtuClienteViewModel;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TelaAtuClienteController extends BaseController implements Initializable{

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSenha;

    private TelaAtuClienteViewModel viewModel;

    public TelaAtuClienteController(TelaAtuClienteViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.tfNome.textProperty().bindBidirectional(viewModel.getNomeProperty());
        this.tfEmail.textProperty().bindBidirectional(viewModel.getEmailProperty());
        this.tfSenha.textProperty().bindBidirectional(viewModel.getSenhaProperty());

        this.viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });
    }

    @FXML
    private void voltar(){
        App.popScreen();
    }

    @FXML
    private void desativar(){
        if(viewModel.desativar()){
            voltar();
            voltar();
        }
    }

    @FXML
    private void atualizarCliente(){
        if(viewModel.atualizar()) voltar();
    }
}
