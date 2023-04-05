package ifpr.pgua.eic.projetointegrador.model.repositories;

import ifpr.pgua.eic.projetointegrador.model.daos.ClienteDAO;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.results.Result;
import ifpr.pgua.eic.projetointegrador.model.results.SuccessResult;

public class ClienteRepository {

    private Cliente clienteLogado;
    private ClienteDAO dao;

    public ClienteRepository(ClienteDAO dao) {
        this.dao = dao;
    }

    public Result cadastrar(String nome, String email, String senha){
        
        if (!(nome.equals("")) && !(email.equals("")) && !(senha.equals(""))) {
            Cliente cliente = new Cliente(nome,email,senha);
            return dao.criar(cliente);
        }
        return Result.fail("Preencha todos os campos!");
    }

    public Result atualizar(String novoNome, String novoEmail, String novaSenha){

        if (!(novoNome.equals("")) && !(novoEmail.equals("")) && !(novaSenha.equals(""))) {
            Result msg = dao.atualizar(clienteLogado, new Cliente(novoNome, novoEmail, novaSenha));
            if (msg instanceof SuccessResult) {
                clienteLogado.setNome(novoNome);
                clienteLogado.setEmail(novoEmail);
                clienteLogado.setSenha(novaSenha);
            }
            return msg;
        }
        return Result.fail("Preencha todos os campos!");
    }

    public Result desativar(){
        return dao.desativar(clienteLogado);
    }

    public Result logar(String email, String senha){

        if (!(email.equals("")) && !(senha.equals(""))) {
            setClienteLogado(dao.logar(new Cliente(email, senha)));
            if(this.clienteLogado == null){
                return Result.fail("Falha ao realizar login!");
            }
            return Result.success("Bem vindo " + clienteLogado.getNome() + "!");
        }
        return Result.fail("Preencha todos os campos!");
    }

    public Cliente getClienteLogado() {
        return clienteLogado;
    }

    public void setClienteLogado(Cliente clienteLogado) {
        this.clienteLogado = clienteLogado;
    }




}