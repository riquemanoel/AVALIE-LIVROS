package ifpr.pgua.eic.projetointegrador.model.entities;

public class Avaliacao {
    private int id;
    private Livro livro;
    private int numero_paginas_lidas;
    private Nota nota;
    private String comentario;
    private Cliente cliente;

    
    public Avaliacao(int id, int numero_paginas_lidas, Nota nota, String comentario) {
        this.id = id;
        this.numero_paginas_lidas = numero_paginas_lidas;
        this.nota = nota;
        this.comentario = comentario;
    }
    
    public Avaliacao(Livro livro, int numero_paginas_lidas, Nota nota, String comentario, Cliente cliente) {
        this.livro = livro;
        this.numero_paginas_lidas = numero_paginas_lidas;
        this.nota = nota;
        this.comentario = comentario;
        this.cliente = cliente;
    }
    
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public int getNumero_paginas_lidas() {
        return numero_paginas_lidas;
    }
    
    public void setNumero_paginas_lidas(int numero_paginas_lidas) {
        this.numero_paginas_lidas = numero_paginas_lidas;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Nota getNota() {
        return nota;
    }
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Avaliacao [id=" + id + ", livro=" + livro + ", numero_paginas_lidas=" + numero_paginas_lidas + ", nota="
                + nota + ", comentario=" + comentario + ", cliente=" + cliente + "]";
    }
}
