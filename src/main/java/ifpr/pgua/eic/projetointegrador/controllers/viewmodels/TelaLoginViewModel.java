package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.results.FailResult;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaLoginViewModel {

    private StringProperty emailProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();
    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ClienteRepository repository;

    public TelaLoginViewModel(ClienteRepository repository) {
        this.repository = repository;
    }

    public boolean login() {  
        String email = emailProperty.getValue();
        String senha = senhaProperty.getValue();

        Result msg  = repository.logar(email, senha);
        if (msg instanceof FailResult) alertProperty.setValue(Result.fail(msg.getMsg()));
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        emailProperty.setValue("");
        senhaProperty.setValue("");
    }

    public ClienteRepository getRepository() {
        return repository;
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
