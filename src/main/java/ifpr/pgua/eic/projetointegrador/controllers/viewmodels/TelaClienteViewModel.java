package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaClienteViewModel {

    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty emailProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();
    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ClienteRepository repository;

    public TelaClienteViewModel(ClienteRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrar() {
        String nome = nomeProperty.getValue();
        String email = emailProperty.getValue();
        String senha = senhaProperty.getValue();

        System.out.println(nome);

        Result msg = repository.cadastrar(nome, email, senha);
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        nomeProperty.setValue("");
        emailProperty.setValue("");
        senhaProperty.setValue("");
    }

    public ClienteRepository getRepository() {
        return repository;
    }

    public StringProperty getNomeProperty() {
        return nomeProperty;
    }

    public StringProperty getEmailProperty() {
        return emailProperty;
    }

    public StringProperty getSenhaProperty() {
        return senhaProperty;
    }

    public ObjectProperty<Result> getAlertProperty() {
        return alertProperty;
    }

}
