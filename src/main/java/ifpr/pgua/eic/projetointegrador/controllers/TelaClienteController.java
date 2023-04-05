package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaClienteViewModel;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TelaClienteController extends BaseController implements Initializable{

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfSenha;

    private TelaClienteViewModel viewModel;

    public TelaClienteController(TelaClienteViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Conexão com ViewModel
        tfNome.textProperty().bindBidirectional(viewModel.getNomeProperty());
        tfEmail.textProperty().bindBidirectional(viewModel.getEmailProperty());
        tfSenha.textProperty().bindBidirectional(viewModel.getSenhaProperty());

        // Ta escutando e mostra a mensagem
        viewModel.getAlertProperty().addListener((ChangeListener<Result>) (observable, oldVal, newVal) -> {
            showMessage(newVal);
        });

        viewModel.limpar();
    }

    @FXML
    private void cadastrar(){
        if (viewModel.cadastrar()) voltar();
    }

    @FXML
    private void voltar(){
        App.popScreen();
    }
}
