package ifpr.pgua.eic.projetointegrador;

import ifpr.pgua.eic.projetointegrador.controllers.TelaAtuClienteController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaAtuLivroController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaClienteController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaLivroController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaLoginController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaPrincipalController;
import ifpr.pgua.eic.projetointegrador.controllers.TelaAvaliacaoController;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAtuClienteViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAtuLivroViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaAvaliacaoViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaClienteViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaLivroViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaLoginViewModel;
import ifpr.pgua.eic.projetointegrador.controllers.viewmodels.TelaPrincipalViewModel;
import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.daos.AvaliacaoDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.ClienteDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCAvaliacaoDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCClienteDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.JDBCLivroDAO;
import ifpr.pgua.eic.projetointegrador.model.daos.LivroDAO;
import ifpr.pgua.eic.projetointegrador.model.repositories.AvaliacaoRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.ClienteRepository;
import ifpr.pgua.eic.projetointegrador.model.repositories.LivroRepository;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.ScreenRegistryFXML;


/**
 * JavaFX App
 */
public class App extends BaseAppNavigator{

    private ClienteDAO clienteDAO;
    private ClienteRepository clienteRepository;

    private LivroDAO livroDAO;
    private LivroRepository livroRepository;

    private AvaliacaoDAO avaliacaoDAO;
    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public void init() throws Exception {
        super.init();
        
        clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
        clienteRepository = new ClienteRepository(clienteDAO);

        livroDAO = new JDBCLivroDAO(FabricaConexoes.getInstance());
        livroRepository = new LivroRepository(livroDAO);

        avaliacaoDAO = new JDBCAvaliacaoDAO(FabricaConexoes.getInstance());
        avaliacaoRepository = new AvaliacaoRepository(avaliacaoDAO);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }



    @Override
    public String getHome() {
        return "LOGIN";
    }

    @Override
    public String getAppTitle() {
        return "Lidos";
    }

    @Override
    public void registrarTelas() {
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "view/Login.fxml", (o)->new TelaLoginController(new TelaLoginViewModel(clienteRepository))));
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "view/Principal.fxml", (o)->new TelaPrincipalController(new TelaPrincipalViewModel(clienteRepository, livroRepository))));
        registraTela("CLIENTE", new ScreenRegistryFXML(getClass(), "view/Cliente.fxml", (o)->new TelaClienteController(new TelaClienteViewModel(clienteRepository))));
        registraTela("LIVRO", new ScreenRegistryFXML(getClass(), "view/Livro.fxml", (o)->new TelaLivroController(new TelaLivroViewModel(livroRepository, clienteRepository))));
        registraTela("AVALIACAO", new ScreenRegistryFXML(getClass(), "view/Avaliacao.fxml", (o)->new TelaAvaliacaoController(new TelaAvaliacaoViewModel(avaliacaoRepository, livroRepository, clienteRepository))));
        registraTela("ATU_CLIENTE", new ScreenRegistryFXML(getClass(), "view/atuCliente.fxml", (o)->new TelaAtuClienteController(new TelaAtuClienteViewModel(clienteRepository))));
        registraTela("INFO_LIVRO", new ScreenRegistryFXML(getClass(), "view/atuLivro.fxml", (o)->new TelaAtuLivroController(new TelaAtuLivroViewModel(avaliacaoRepository, livroRepository, clienteRepository))));
    }


}