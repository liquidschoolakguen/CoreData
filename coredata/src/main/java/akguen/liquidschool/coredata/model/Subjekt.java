package akguen.liquidschool.coredata.model;

public class Subjekt {

    private int id;

    private String vorname;
    private String nachname;
    private String kürzel;

    private String geburtstag;
    private String geburtsort;
    private String nationalität;

    private String benutzername;
    private String benutzerpasswort;

    private String schulpfad;
    private boolean aktiv;
    private String typ_;


    public Subjekt(int id, String vorname, String nachname, String kürzel, String geburtstag, String geburtsort, String nationalität, String benutzername, String benutzerpasswort, String schulpfad, boolean aktiv, String typ_) {

        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.kürzel = kürzel;
        this.geburtstag = geburtstag;
        this.geburtsort = geburtsort;
        this.nationalität = nationalität;
        this.benutzername = benutzername;
        this.benutzerpasswort = benutzerpasswort;
        this.schulpfad = schulpfad;
        this.aktiv = aktiv;
        this.typ_ = typ_;

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

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    public String getNationalität() {
        return nationalität;
    }

    public void setNationalität(String nationalität) {
        this.nationalität = nationalität;
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

    public String getSchulpfad() {
        return schulpfad;
    }

    public void setSchulpfad(String schulpfad) {
        this.schulpfad = schulpfad;
    }


    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public String getTyp_() {
        return typ_;
    }

    public void setTyp_(String typ_) {
        this.typ_ = typ_;
    }
}
