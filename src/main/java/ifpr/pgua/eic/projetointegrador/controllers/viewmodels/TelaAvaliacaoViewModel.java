package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Nota;
import ifpr.pgua.eic.projetointegrador.model.repositories.AvaliacaoRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.LivroRepository;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaAvaliacaoViewModel {

    private StringProperty livroProperty = new SimpleStringProperty();
    private StringProperty paginas_lidasProperty = new SimpleStringProperty();
    private StringProperty comentarioProperty = new SimpleStringProperty();
    private ObjectProperty<Nota> opNota = new SimpleObjectProperty<>();

    private ObjectProperty<Result> alertProperty = new SimpleObjectProperty<>();

    private ObservableList<Nota> notas = FXCollections.observableArrayList();

    private AvaliacaoRepository avaliacaoRepository;
    private LivroRepository livroRepository;
    private ClienteRepository clienteRepository; 

    public TelaAvaliacaoViewModel(AvaliacaoRepository avaliacaoRepository, LivroRepository livroRepository,
            ClienteRepository clienteRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        updateList();
    }

    public void updateList() {
        limpar();
        
        notas.clear();
        notas.addAll(avaliacaoRepository.getNotas());

        livroProperty.setValue("Avaliando: " + livroRepository.getLivroSelecionado().getTitulo());

        paginas_lidasProperty.setValue(String.valueOf(avaliacaoRepository.getNumeroPaginaUltimaAvaliacao(livroRepository.getLivroSelecionado())));
    }

    public boolean avaliar(){
        Livro livro = livroRepository.getLivroSelecionado();
        int paginas_lidas = 0;
        Nota nota = opNota.getValue();
        String comentario = comentarioProperty.getValue();
        Cliente cliente = clienteRepository.getClienteLogado();

        try{
            paginas_lidas = Integer.parseInt(paginas_lidasProperty.getValue());
        }catch(NumberFormatException e){
            alertProperty.setValue(Result.fail("Número de páginas lidas inválido!"));
        }

        Result msg = avaliacaoRepository.avaliar(livro, nota, paginas_lidas, comentario, cliente);
        alertProperty.setValue(msg);
        return msg instanceof SuccessResult;
    }

    public void limpar() {
        livroProperty.setValue("");
        paginas_lidasProperty.setValue("");
        comentarioProperty.setValue("");
        opNota.setValue(null);
    }

    public StringProperty getLivroProperty() {
        return livroProperty;
    }

    public StringProperty getPaginas_lidasProperty() {
        return paginas_lidasProperty;
    }

    public StringProperty getComentarioProperty() {
        return comentarioProperty;
    }

    public ObjectProperty<Nota> getOpNota() {
        return opNota;
    }

    public ObjectProperty<Result> getAlertProperty() {
        return alertProperty;
    }

    public ObservableList<Nota> getNotas() {
        return notas;
    }

}
