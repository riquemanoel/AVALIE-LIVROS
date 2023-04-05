package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.entities.Livro;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LivroRow {
    private Livro livro;
    
    public LivroRow(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro(){
        return livro;
    }

    /**
     * Propriedade para representar o atributo titulo do livro.
     * 
     * @return SimpleStringProperty com o valor do titulo do livro.
     */
    public StringProperty tituloProperty(){
        return new SimpleStringProperty(livro.getTitulo());
    }

    /**
     * Propriedade para representar o atributo autor do livro.
     * 
     * @return SimpleStringProperty com o valor do autor do livro.
     */
    public StringProperty autorProperty(){
        return new SimpleStringProperty(livro.getAutor());
    }

    /**
     * Propriedade para representar o atributo numero_paginas_livro do livro.
     * 
     * @return SimpleStringProperty com o valor do numero_paginas_livro do livro.
     */
    public StringProperty numero_paginas_livroProperty(){
        return new SimpleStringProperty(String.valueOf(livro.getNumero_paginas_livro()));
    }

    /**
     * Propriedade para representar o atributo ano_lancamento do livro.
     * 
     * @return SimpleStringProperty com o valor do ano_lancamento do livro.
     */
    public StringProperty ano_lancamentoProperty(){
        return new SimpleStringProperty(String.valueOf(livro.getAno_lancamento()));
    }

    /**
     * Propriedade para representar o atributo genero do livro.
     * 
     * @return SimpleStringProperty com o valor do genero do livro.
     */
    public StringProperty generoProperty(){
        return new SimpleStringProperty(String.valueOf(livro.getGenero()));
    }

    /**
     * Propriedade para representar o atributo tag do livro.
     * 
     * @return SimpleStringProperty com o valor do tag do livro.
     */
    public StringProperty tagProperty(){
        return new SimpleStringProperty(String.valueOf(livro.getTag()));
    }
}
