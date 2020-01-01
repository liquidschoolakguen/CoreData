package akguen.liquidschool.coredata.model;

public class Separator {

    private long id;
    private String stringId; // zusammengesetzter String aus den alphabetisch sortierten StringIds der Radios
    private String name;
    private String need;

    private boolean selected=false; //Hilfsvariable;

    private int visibility; //Nicht für die Datenbank, sondern nur eine Hilfsvariable

    public Separator(long id, String stringId, String name, String need){

        this.id = id;
        this.stringId = stringId;
        this.name = name;
        this.need = need;
    }

    public Separator(){

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
