package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaAtuClienteViewModel {

    private StringProperty nomeProperty = new SimpleStringProperty();
    private StringProperty emailProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();
    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();
    
    private ClienteRepository clienteRepository;

    public TelaAtuClienteViewModel(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        updateList();
    }

    public void updateList() {
        nomeProperty.setValue(clienteRepository.getClienteLogado().getNome());
        emailProperty.setValue(clienteRepository.getClienteLogado().getEmail());
        senhaProperty.setValue(clienteRepository.getClienteLogado().getSenha());
    }

    public boolean atualizar() {
        String novoNome = nomeProperty.getValue();
        String novoEmail = emailProperty.getValue();
        String novaSenha = senhaProperty.getValue();

        Result msg = clienteRepository.atualizar(novoNome, novoEmail, novaSenha);
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public boolean desativar() {
        Result msg = clienteRepository.desativar();
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        nomeProperty.setValue("");
        emailProperty.setValue("");
        senhaProperty.setValue("");
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
