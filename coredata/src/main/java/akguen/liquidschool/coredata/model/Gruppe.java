package akguen.liquidschool.coredata.model;

public class Gruppe {

    private long id;
    private String stringId;
    private String name;
    private String externName;
    private String vaterStringId="";


    private boolean selected=false; //Hilfsvariable;

    public Gruppe(long id, String stringId, String name, String externName, String vaterStringId) {

        this.id = id;
        this.stringId = stringId;
        this.name = name;
        this.externName = externName;

        this.vaterStringId = vaterStringId;



    }
    public Gruppe(){

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getVaterStringId() {
        return vaterStringId;
    }

    public void setVaterStringId(String vaterStringId) {
        this.vaterStringId = vaterStringId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getExternName() {
        return externName;
    }

    public void setExternName(String externName) {
        this.externName = externName;
    }


}
