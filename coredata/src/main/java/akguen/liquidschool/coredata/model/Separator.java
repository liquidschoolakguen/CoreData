package akguen.liquidschool.coredata.model;

public class Separator {


    private String id; // zusammengesetzter String aus den alphabetisch sortierten StringIds der Radios
    private String name;
    private String need;

    private int visibility; //Nicht für die Datenbank, sondern nureine Hilfsvariable

    public Separator(String id, String name, String need){

        this.id = id;
        this.name = name;
        this.need = need;

    }

    public Separator(){

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
