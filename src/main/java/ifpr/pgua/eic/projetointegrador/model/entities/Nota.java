package ifpr.pgua.eic.projetointegrador.model.entities;

public class Nota {
    int id;
    String nota;

    public Nota() {
    }

    public Nota(int id, String nota) {
        this.id = id;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAvaliacao() {
        return nota;
    }
    public void setAvaliacao(String nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nota;
    }

    
}
