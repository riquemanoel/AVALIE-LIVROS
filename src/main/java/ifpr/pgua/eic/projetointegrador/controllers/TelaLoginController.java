package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaLoginViewModel;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TelaLoginController extends BaseController implements Initializable{

    @FXML
    TextField tfEmail;

    @FXML
    TextField tfSenha;

    TelaLoginViewModel viewModel;

    public TelaLoginController(TelaLoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfEmail.textProperty().bindBidirectional(viewModel.getEmailProperty());
        tfSenha.textProperty().bindBidirectional(viewModel.getSenhaProperty());

        viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        viewModel.limpar();
    }

    @FXML
    private void entrar(){
        if (viewModel.login()){
            App.pushScreen("PRINCIPAL");
            viewModel.limpar();
        }
    }

    @FXML
    private void cadCliente(){
        App.pushScreen("CLIENTE");
    }
}
