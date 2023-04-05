package ifpr.pgua.eic.projetointegrador.model.entities;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int ano_lancamento;
    private int numero_paginas_livro;
    private Genero genero;
    private Tag tag;
    private Cliente cliente;
    
    public Livro(int id, String titulo, String autor, int ano_lancamento, int numero_paginas_livro, Genero genero, Tag tag,
            Cliente cliente) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano_lancamento = ano_lancamento;
        this.numero_paginas_livro = numero_paginas_livro;
        this.genero = genero;
        this.tag = tag;
        this.cliente = cliente;
    }

    public Livro(String titulo, String autor, int ano_lancamento, int numero_paginas_livro, Genero genero, Tag tag,
            Cliente cliente) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_lancamento = ano_lancamento;
        this.numero_paginas_livro = numero_paginas_livro;
        this.genero = genero;
        this.tag = tag;
        this.cliente = cliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno_lancamento() {
        return ano_lancamento;
    }

    public void setAno_lancamento(int ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }
    
    public int getNumero_paginas_livro() {
        return numero_paginas_livro;
    }

    public void setNumero_paginas_livro(int numero_paginas_livro) {
        this.numero_paginas_livro = numero_paginas_livro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ano_lancamento=" + ano_lancamento
                + ", numero_paginas_livro=" + numero_paginas_livro + ", genero=" + genero + ", tag=" + tag
                + ", cliente=" + cliente + "]";
    }

    
}
