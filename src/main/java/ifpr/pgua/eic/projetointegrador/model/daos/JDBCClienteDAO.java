package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCClienteDAO implements ClienteDAO{

    private static final String INSERT = "INSERT INTO Cliente (nome,email,senha) VALUES (?,?,?)";
    private static final String LOGIN = "SELECT * FROM Cliente WHERE email = ? AND senha = ? AND ativo=0";
    private static final String UPDATE = "UPDATE Cliente set email=?, nome=?, senha=? WHERE id_cliente=?";
    private static final String DESATIVAR = "UPDATE Cliente set ativo=1 WHERE id_cliente=?";


    private FabricaConexoes fabricaConexao;

    public JDBCClienteDAO(FabricaConexoes fabricaConexao){
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result criar(Cliente cliente) {

        try{
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement(INSERT);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            pstm.setString(3, cliente.getSenha());
            
            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Cliente cadastrado com sucesso!");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(msg(e.getMessage()));
        }
        
    }

    @Override
    public Result atualizar(Cliente cliente, Cliente atualizacaoCliente) {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement(UPDATE);

            pstm.setString(1, atualizacaoCliente.getEmail());
            pstm.setString(2, atualizacaoCliente.getNome());
            pstm.setString(3, atualizacaoCliente.getSenha());
            pstm.setInt(4, cliente.getId());
            
            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Atualizado com sucesso!");

        }catch(SQLException e){
            System.out.println(msg(e.getMessage()));
            return Result.fail(msg(e.getMessage()));
        }
    }

    @Override
    public Result desativar(Cliente cliente) {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement(DESATIVAR);

            pstm.setInt(1, cliente.getId());
            
            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Cliente desativado com sucesso!");

        }catch(SQLException e){
            System.out.println(msg(e.getMessage()));
            return Result.fail(msg(e.getMessage()));
        }
    }

    private Cliente buildFrom(ResultSet result) throws SQLException{
        int id = result.getInt("id_cliente");
        String nome = result.getString("nome");
        String email = result.getString("email");
        String senha = result.getString("senha");
        int paginas_lidas = result.getInt("paginas_lidas");

        Cliente cliente = new Cliente(id,nome, email, senha, paginas_lidas);

        return cliente;
    }

    @Override
    public Cliente logar(Cliente cliente) {
        Cliente c = null;
        try{
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement(LOGIN);

            pstm.setString(1, cliente.getEmail());
            pstm.setString(2, cliente.getSenha());

            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                c = buildFrom(rs);
            }
            
            rs.close();
            pstm.close();
            con.close();

            return c;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

        

        

    }

    public String msg(String msg){
        String[] msgSeparada = msg.split("'");
        msg = msgSeparada[1];
        msgSeparada = msg.split("\\.");
        return msgSeparada[1];
    }
}