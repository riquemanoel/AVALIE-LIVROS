package ifpr.pgua.eic.projetointegrador.model.daos;

import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public interface ClienteDAO {
    Result criar(Cliente cliente);
    Result atualizar(Cliente cliente, Cliente atualizacaoCliente);
    Cliente logar(Cliente cliente);
    Result desativar(Cliente cliente);
}
