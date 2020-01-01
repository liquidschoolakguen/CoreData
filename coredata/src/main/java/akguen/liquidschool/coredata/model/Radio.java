package akguen.liquidschool.coredata.model;

public class Radio {

    private long id;
    private String stringId;
    private String name;
    private String separator_id;

    private boolean formular_checked = false; // muss jetzt doch in die Datenbank
    private boolean default_checked = false;


    public Radio(long id, String stringId, String name, String separator_id, boolean formular_checked, boolean default_checked) {

        this.id = id;
        this.stringId = stringId;
        this.name = name;
        this.separator_id = separator_id;
        this.formular_checked = formular_checked;
        this.default_checked = default_checked;
    }

    public Radio(){

    }


    public boolean isDefault_checked() {
        return default_checked;
    }

    public void setDefault_checked(boolean default_checked) {
        this.default_checked = default_checked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFormular_checked() {
        return formular_checked;
    }

    public void setFormular_checked(boolean formular_checked) {
        this.formular_checked = formular_checked;
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
