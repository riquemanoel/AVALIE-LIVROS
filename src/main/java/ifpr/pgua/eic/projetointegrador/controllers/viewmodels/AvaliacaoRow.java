package ifpr.pgua.eic.projetointegrador.controllers.viewmodels;

import ifpr.pgua.eic.projetointegrador.model.entities.Avaliacao;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AvaliacaoRow {

    private Avaliacao avaliacao;
    
    public AvaliacaoRow(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Avaliacao getAvaliacao(){
        return avaliacao;
    }

    /**
     * Propriedade para representar o atributo nota de Avaliacao.
     * 
     * @return SimpleStringProperty com o valor do nota de Avaliacao.
     */
    public StringProperty notaProperty(){
        return new SimpleStringProperty(avaliacao.getNota().getAvaliacao());
    }

    /**
     * Propriedade para representar o atributo comentario de Avaliacao.
     * 
     * @return SimpleStringProperty com o valor do comentario de Avaliacao.
     */
    public StringProperty comentarioProperty(){
        return new SimpleStringProperty(avaliacao.getComentario());
    }

    /**
     * Propriedade para representar o atributo numero_paginas_lidas de Avaliacao.
     * 
     * @return SimpleStringProperty com o valor do numero_paginas_lidas de Avaliacao.
     */
    public StringProperty numero_paginas_lidasProperty(){
        return new SimpleStringProperty(String.valueOf(avaliacao.getNumero_paginas_lidas()));
    }

}
