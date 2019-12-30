package akguen.liquidschool.coredata.model;

public class Formular {

    private int id;
    private String result;  // zB "0010"

    private String seperator_id;



    public Formular(int id, String result, String seperator_id){

        this.id = id;
        this.result = result;
        this.seperator_id = seperator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSeperator_id() {
        return seperator_id;
    }

    public void setSeperator_id(String seperator_id) {
        this.seperator_id = seperator_id;
    }
}
