package akguen.liquidschool.coredata.model;

public class Mobilnummer {

    private String nummer;




    public Mobilnummer(String nummer, int kontakt_id){

        this.nummer = nummer;

    }



    public Mobilnummer(){


    }



    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }


}
