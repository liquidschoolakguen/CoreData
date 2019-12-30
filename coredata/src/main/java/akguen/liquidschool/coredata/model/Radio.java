package akguen.liquidschool.coredata.model;

public class Radio {


    private String id;
    private String name;
    private String separator_id;

    private boolean checked = false; //Nicht für die Datenbank, sondern nureine Hilfsvariable beim Build einer Gruppe

    public Radio(String id, String name, String separator_id) {

        this.id = id;
        this.name = name;
        this.separator_id = separator_id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getSeparator_id() {
        return separator_id;
    }

    public void setSeparator_id(String separator_id) {
        this.separator_id = separator_id;
    }
}
