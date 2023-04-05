package ifpr.pgua.eic.projetointegrador.model.entities;

public class Tag {

    private int id;
    private String tag;

    public Tag(){
        
    }

    public Tag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
