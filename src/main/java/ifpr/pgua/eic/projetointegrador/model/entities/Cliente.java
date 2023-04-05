package ifpr.pgua.eic.projetointegrador.model.entities;

public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private int paginas_lidas;

    public Cliente(int id, String nome, String email, String senha, int paginas_lidas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.paginas_lidas = paginas_lidas;
    }

    public Cliente(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPaginas_lidas() {
        return paginas_lidas;
    }

    public void setPaginas_lidas(int paginas_lidas) {
        this.paginas_lidas = paginas_lidas;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
