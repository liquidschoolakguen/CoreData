package akguen.liquidschool.coredata.model;

public class Subjekt {
    // knummer 1 und 1  27450802
    private int id;

    private String vorname;
    private String nachname;
    private String kürzel;

   // private String geburtstag;
   // private String geburtsort;
   // private String nationalität;

    private String benutzername;
    private String benutzerpasswort;

   // private String schulpfad;
    //private boolean aktiv = false;
    //private String typ_;
    //private boolean männlich = false;



    public Subjekt(int id, String vorname, String nachname, String kürzel, String benutzername, String benutzerpasswort) {

        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.kürzel = kürzel;
        this.benutzername = benutzername;
        this.benutzerpasswort = benutzerpasswort;

    }

    public Subjekt(){


    }




    public String getKürzel() {
        return kürzel;
    }

    public void setKürzel(String kürzel) {
        this.kürzel = kürzel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }


    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getBenutzerpasswort() {
        return benutzerpasswort;
    }

    public void setBenutzerpasswort(String benutzerpasswort) {
        this.benutzerpasswort = benutzerpasswort;
    }

}
