package akguen.liquidschool.coredata.model;

public enum W_Frage {

    WAS("Was"),
    WER("Wer"),
    WO("Wo"),
    WANN("Wann"),
    WELCHE("Welche*"),
    WIE("Wie"),
    WARUM("Warum"),
    VIEVIELE("Wie viele");



    private String s;



    W_Frage(String s) {
        this.s = s;

    }

    public String getS() {
        return s;
    }


}
