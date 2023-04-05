package ifpr.pgua.eic.projetointegrador.model.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.model.entities.Avaliacao;
import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import ifpr.pgua.eic.projetointegrador.model.entities.Nota;
import ifpr.pgua.eic.projetointegrador.model.results.Result;

public class JDBCAvaliacaoDAO implements AvaliacaoDAO{

    private static final String INSERT = "INSERT INTO Avaliacao (nota,comentario,numero_paginas_lidas,livro,cliente) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL_NOTAS = "SELECT * FROM Nota";
    private static final String SELECT_ALL_AVALIACOES = "SELECT * FROM Avaliacao WHERE livro=?";
    private static final String GET_NOTA = "SELECT * FROM Nota WHERE id_nota=?";

    private FabricaConexoes fabricaConexao;

    public JDBCAvaliacaoDAO(FabricaConexoes fabricaConexao){
        this.fabricaConexao = fabricaConexao;
    }


    @Override
    public Result avaliar(Avaliacao avaliacao) {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement(INSERT);

            pstm.setInt(1, avaliacao.getNota().getId());
            pstm.setString(2, avaliacao.getComentario());
            pstm.setInt(3, avaliacao.getNumero_paginas_lidas());
            pstm.setInt(4, avaliacao.getLivro().getId());
            pstm.setInt(5, avaliacao.getCliente().getId());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Avaliação registrada com sucesso!");

        }catch(SQLException e){
            System.out.println(msg(e.getMessage()));
            return Result.fail(msg(e.getMessage()));
        }
    }

    @Override
    public List<Nota> listAllNotas() {
        ArrayList<Nota> notas = new ArrayList<>();
        try{
            Connection con = fabricaConexao.getConnection();
            
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_NOTAS);

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Nota nota = new Nota(rs.getInt("id_nota"), rs.getString("nota"));
                notas.add(nota);
            }

            rs.close();
            pstm.close();
            con.close();

            return notas;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getNumeroPaginaUltimaAvaliacao(Livro livro) {
        int nPg = 0;
        try{
            Connection con = fabricaConexao.getConnection();
            
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_AVALIACOES);

            pstm.setInt(1, livro.getId());

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                nPg = rs.getInt("numero_paginas_lidas");
            }

            rs.close();
            pstm.close();
            con.close();

            return nPg;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public Nota getNota(int id) {
        Nota nota = new Nota();
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement(GET_NOTA);

            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                nota = new Nota(rs.getInt("id_nota"), rs.getString("nota"));
            }

            pstm.close();
            con.close();

            return nota;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public List<Avaliacao> listAllAvaliacaoByLivro(Livro livro) {
        ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
        try{
            Connection con = fabricaConexao.getConnection();
            
            PreparedStatement pstm = con.prepareStatement(SELECT_ALL_AVALIACOES);

            pstm.setInt(1, livro.getId());

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                Avaliacao avaliacao = new Avaliacao(rs.getInt("id_avaliacao"), rs.getInt("numero_paginas_lidas"), getNota(rs.getInt("nota")),rs.getString("comentario"));
                avaliacoes.add(avaliacao);
            }

            rs.close();
            pstm.close();
            con.close();

            return avaliacoes;

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