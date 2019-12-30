package akguen.liquidschool.coredata.model;

public class Radio {

    private long id;
    private String stringId;
    private String name;
    private String separator_id;

    private boolean checked = false; //Nicht für die Datenbank, sondern nureine Hilfsvariable beim Build einer Gruppe

    public Radio(long id, String stringId, String name, String separator_id) {

        this.id = id;
        this.stringId = stringId;
        this.name = name;
        this.separator_id = separator_id;
    }

    public Radio(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getSeparator_id() {
        return separator_id;
    }

    public void setSeparator_id(String separator_id) {
        this.separator_id = separator_id;
    }
}
