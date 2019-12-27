package akguen.liquidschool.coredata.model;

public class Kontakt {


    private int id;

    private String mail;


    private int kollege_id;
    private int angehoeriger_id;
    private int schueler_id;

    public Kontakt(int id, String mobil, String mail, int kollege_id, int angehoeriger_id, int schueler_id) {

        this.id = id;

        this.mail = mail;


        /// entwederOder
        this.angehoeriger_id = angehoeriger_id;
        this.kollege_id = kollege_id;
        this.schueler_id = schueler_id;

    }


    public Kontakt() {


    }


    public int getKollege_id() {
        return kollege_id;
    }

    public void setKollege_id(int kollege_id) {
        this.kollege_id = kollege_id;
    }

    public int getAngehoeriger_id() {
        return angehoeriger_id;
    }

    public void setAngehoeriger_id(int angehoeriger_id) {
        this.angehoeriger_id = angehoeriger_id;
    }

    public int getSchueler_id() {
        return schueler_id;
    }

    public void setSchueler_id(int schueler_id) {
        this.schueler_id = schueler_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        String output = mail;
        if (kollege_id != 0)
            return "Kollegenkontakt: " + kollege_id;

        if (angehoeriger_id != 0)
            return "Kollegenkontakt: " + angehoeriger_id;

        if (schueler_id != 0)
            return "Kollegenkontakt: " + schueler_id;

        return null;
    }

}
