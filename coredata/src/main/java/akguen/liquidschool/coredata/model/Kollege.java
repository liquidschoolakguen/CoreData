package akguen.liquidschool.coredata.model;

public class Kollege {

    private int id;
    private String vorname;
    private String nachname;
    private String personaltyp;
    private String geburtstag;
    private String strasse;
    private String plz;
    private String telefon;
    private String email;
    private String standort;
    private String passwort;


    public Kollege(int id, String vorname, String nachname, String personaltyp, String geburtstag, String strasse, String plz, String telefon, String email, String standort, String passwort) {

        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.personaltyp = personaltyp;
        this.geburtstag = geburtstag;
        this.strasse = strasse;
        this.plz = plz;
        this.telefon = telefon;
        this.email = email;
        this.standort = standort;
        this.passwort = passwort;

    }

    public Kollege() {

    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPersonaltyp() {
        return personaltyp;
    }

    public void setPersonaltyp(String personaltyp) {
        this.personaltyp = personaltyp;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }


    @Override
    public String toString() {
        String output = id + "] " +vorname + " " + nachname+ " " + personaltyp + " " + geburtstag + " " + strasse+ " " + plz+ " " + telefon+ " " + email;

        return output;

    }

}
