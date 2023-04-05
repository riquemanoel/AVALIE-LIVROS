package ifpr.pgua.eic.projetointegrador.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Cliente;
import ifpr.pgua.eic.projetointegrador.model.entities.Genero;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Tag;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCLivroDAO implements LivroDAO{

    private static final String INSERT = "INSERT INTO Livro (titulo, autor, ano_lancamento, numero_paginas_livro, genero, tag, cliente) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_GENEROS = "SELECT * FROM Genero";
    private static final String GET_GENERO = "SELECT * FROM Genero WHERE id_genero = ?";
    private static final String SELECT_ALL_TAGS = "SELECT * FROM Tag";
    private static final String GET_TAG = "SELECT * FROM Tag WHERE id_tag = ?";
    private static final String SELECT_ALL = "SELECT * FROM Livro WHERE cliente = ? AND ativo = 0";
    private static final String SELECT_ALL_TITULO = "SELECT * FROM Livro WHERE cliente = ? AND ativo = 0 AND titulo LIKE '%?%'";
    private static final String DESATIVAR = "UPDATE Livro set ativo = 1 WHERE id_livro = ?";
    private static final String UPDATE = "UPDATE Livro set titulo=?, autor=?, ano_lancamento=?, numero_paginas_livro=?, genero=?, tag=? WHERE id_livro = ?";


    private FabricaConexoes fabricaConexao;

    public JDBCLivroDAO(FabricaConexoes fabricaConexao){
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result criar(Livro livro) {
        try{
            // Conecta com o Banco de dados
            Connection con = fabricaConexao.getConnection();
            // Faz um PreparedStatement de INSERT
            PreparedStatement pstm = con.prepareStatement(INSERT);

            // Pega as informações para o INSERT
            pstm.setString(1, livro.getTitulo());
            pstm.setString(2, livro.getAutor());
            pstm.setInt(3, livro.getAno_lancamento());
            pstm.setInt(4, livro.getNumero_paginas_livro());
            pstm.setInt(5, livro.getGenero().getId());
            pstm.setInt(6, livro.getTag().getId());
            pstm.setInt(7, livro.getCliente().getId());

            // Executa o INSERT
            pstm.executeUpdate();

            // Fecha o PreparedStatement e a conexão com o Banco de Dados
            pstm.close();
            con.close();

            return Result.success("Livro criado!");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(msg(e.getMessage()));
        }
    }

    @Override
    public Result desativar(Livro livro) {
        try{
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement(DESATIVAR);

            pstm.setInt(1, livro.getId());
            
            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Livro excluido!");

        }catch(SQLException e){
            System.out.println(msg(e.getMessage()));
            return Result.fail(msg(e.getMessage()));
        }
    }

    @Override
    public List<Genero> listAllGeneros() {
        // Faz uma ArrayList de generos
        ArrayList<Genero> generos = new ArrayList<>();
        try{
            // Conecta com o Banco de dados
            Connection con = fabricaConexao.getConnection();
            // Faz um PreparedStatement de SELECT_ALL_GENEROS
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_GENEROS);

            // Executa o SELECT_ALL_GENEROS salva o resultado
            ResultSet rs = pstm.executeQuery();
            
            // Laço de repetição q pega oq  roda pegando o id_genero e a String genero
            // e coloca na entidade genero
            while(rs.next()){
                Genero genero = new Genero(rs.getInt("id_genero"), rs.getString("genero"));
                generos.add(genero);
            }

            // Fecha ResultSet, o PreparedStatement e a conexão com o Banco de Dados
            rs.close();
            pstm.close();
            con.close();

            return generos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Genero getGenero(int id) {
        Genero genero = new Genero();
        try{
            // Conecta com o Banco de dados
            Connection con = fabricaConexao.getConnection();
            // Faz um PreparedStatement de GET_GENERO
            PreparedStatement pstm = con.prepareStatement(GET_GENERO);

            // PreparedStatement pega o id de genero
            pstm.setInt(1, id);

            // Fala que o resultado é igual a pstm.executeQuery(executeQuery = é um método que executa a instrução SQL) 
            ResultSet rs = pstm.executeQuery();

            // Laço de repetição 
            while (rs.next()) {
                genero = new Genero(rs.getInt("id_genero"), rs.getString("genero"));
            }

            // Fecha rs(ResultSet), o PreparedStatement e a conexão com o Banco de Dados
            rs.close();
            pstm.close();
            con.close();

            return genero;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }        
    }    

    @Override
    public List<Tag> listAllTags() {
        ArrayList<Tag> tags = new ArrayList<>();
        try{
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_TAGS);

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Tag tag = new Tag(rs.getInt("id_tag"), rs.getString("tag"));
                tags.add(tag);
            }

            rs.close();
            pstm.close();
            con.close();

            return tags;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tag getTag(int id) {
        Tag tag = new Tag();

        try{
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement(GET_TAG);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                tag = new Tag(rs.getInt("id_tag"), rs.getString("tag"));
            }

            rs.close();
            pstm.close();
            con.close();

            return tag;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Livro> listAll(Cliente clienteLogado) {
        ArrayList<Livro> livros = new ArrayList<>();
        try{
            Connection con = fabricaConexao.getConnection();
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL);

            pstm.setInt(1, clienteLogado.getId());

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano_lancamento"), rs.getInt("numero_paginas_livro"), getGenero(rs.getInt("genero")), getTag(rs.getInt("tag")), clienteLogado);
                livros.add(livro);
            }

            rs.close();
            pstm.close();
            con.close();

            return livros;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public List<Livro> listAllTitulo(String busca, Cliente clienteLogado) {
        ArrayList<Livro> livros = new ArrayList<>();
        try{
            // Conecta com o Banco de dados
            Connection con = fabricaConexao.getConnection();
            // Faz um PreparedStatement de SELECT_ALL_TITULO
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_TITULO);

            pstm.setInt(1, clienteLogado.getId());
            pstm.setString(2, busca);

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Livro livro = new Livro(rs.getInt("id_livro"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano_lancamento"), rs.getInt("numero_paginas_livro"), getGenero(rs.getInt("genero")), getTag(rs.getInt("tag")), clienteLogado);
                livros.add(livro);
            }

            rs.close();
            pstm.close();
            con.close();

            return livros;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Result atualizar(Livro livro) {
        try{
            // Conecta com o Banco de dados
            Connection con = fabricaConexao.getConnection();

            // Faz um PreparedStatement de UPDATE 
            PreparedStatement pstm = con.prepareStatement(UPDATE);

            // Pegamos as informações para o UPDATE
            pstm.setString(1, livro.getTitulo());
            pstm.setString(2, livro.getAutor());
            pstm.setInt(3, livro.getAno_lancamento());
            pstm.setInt(4, livro.getNumero_paginas_livro());
            pstm.setInt(5, livro.getGenero().getId());
            pstm.setInt(6, livro.getTag().getId());
            pstm.setInt(7, livro.getId());
            
            // Executamos o UPDATE

            pstm.executeUpdate();

            // Fechamos o PreparedStatement e a conexão com o Banco de Dados
            pstm.close();
            con.close();

            return Result.success("Livro atualizado!");

        }catch(SQLException e){
            System.out.println(msg(e.getMessage()));
            return Result.fail(msg(e.getMessage()));
        }
    }

    public String msg(String msg){
        String[] msgSeparada = msg.split("'");
        msg = msgSeparada[1];
        msgSeparada = msg.split("\\.");
        return msgSeparada[1];
    }
}