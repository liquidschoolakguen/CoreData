package akguen.liquidschool.coredata.db;

import android.support.v7.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import akguen.liquidschool.coredata.model.Gruppe;

import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Separator;

public class Controller extends AppCompatActivity {

    private DataSource_Separator ds_separator;
    private DataSource_Gruppe ds_gruppe;
    private DataSource_Radio ds_radio;


    public List<Separator> getVisibleSeparatorsOfGruppe(Gruppe gruppe) {
        ds_separator = new DataSource_Separator(this);
        ds_separator.open();
        List<Separator> separatorList = ds_separator.getAllSeparators();
        ds_separator.close();


        for (Separator s : separatorList) {
            boolean passt = false;

            if (s.getNeed().equals(gruppe.getId())) {
                s.setVisibility(2);

            } else if (s.getNeed().equals(gruppe.getS1()) ||
                    s.getNeed().equals(gruppe.getS2()) ||
                    s.getNeed().equals(gruppe.getS3()) ||
                    s.getNeed().equals(gruppe.getS4()) ||
                    s.getNeed().equals(gruppe.getS5()) ||
                    s.getNeed().equals(gruppe.getS6()) ||
                    s.getNeed().equals(gruppe.getS7()) ||
                    s.getNeed().equals(gruppe.getS8()) ||
                    s.getNeed().equals(gruppe.getS9()) ||
                    s.getNeed().equals(gruppe.getS10())
            ) {
                s.setVisibility(1);
            } else {

                s.setVisibility(0);
            }


        }


        return separatorList;
    }


    public void buildGruppe(Gruppe vaterGruppe, Separator s) {

        Gruppe n = new Gruppe();

        if (s == null) {

            // ohne Seperator keine neue Gruppe, VaterGruppe wird zurückgegeben

            return;
        }

        if (vaterGruppe == null) {

            // erste Gruppe
            n.setStringId("main");
            n.setName("main");
            n.setExternName("liquidschool Gruppe");

            n.setS1("");
            n.setS2("");
            n.setS3("");
            n.setS4("");
            n.setS5("");
            n.setS6("");
            n.setS7("");
            n.setS8("");
            n.setS9("");
            n.setS10("");


            ds_gruppe = new DataSource_Gruppe(this);
            ds_gruppe.open();
            ds_gruppe.createGruppe(n.getStringId(),n.getName(),n.getExternName(),n.getS1(),n.getS2(),n.getS3(),n.getS4(),n.getS5(),n.getS6(),n.getS7(),n.getS8(),n.getS9(),n.getS10());
            ds_gruppe.close();


            return;

        }


        n.setS1(vaterGruppe.getS1());
        n.setS2(vaterGruppe.getS2());
        n.setS3(vaterGruppe.getS3());
        n.setS4(vaterGruppe.getS4());
        n.setS5(vaterGruppe.getS5());
        n.setS6(vaterGruppe.getS6());
        n.setS7(vaterGruppe.getS7());
        n.setS8(vaterGruppe.getS8());
        n.setS9(vaterGruppe.getS9());
        n.setS10(vaterGruppe.getS10());


        n.setStringId(createGen(s));
        n.setExternName(getCheckedRadioFromSeparator(s) + " Gruppe");


        if (n.getS1().equals("")) {
            n.setS1(vaterGruppe.getStringId());
        } else if (n.getS2().equals("")) {
            n.setS2(vaterGruppe.getStringId());
        } else if (n.getS3().equals("")) {
            n.setS3(vaterGruppe.getStringId());
        } else if (n.getS4().equals("")) {
            n.setS4(vaterGruppe.getStringId());
        } else if (n.getS5().equals("")) {
            n.setS5(vaterGruppe.getStringId());
        } else if (n.getS6().equals("")) {
            n.setS6(vaterGruppe.getStringId());
        } else if (n.getS7().equals("")) {
            n.setS7(vaterGruppe.getStringId());
        } else if (n.getS8().equals("")) {
            n.setS8(vaterGruppe.getStringId());
        } else if (n.getS9().equals("")) {
            n.setS9(vaterGruppe.getStringId());
        } else if (n.getS10().equals("")) {
            n.setS10(vaterGruppe.getStringId());
        } else {


            System.out.println("KEIN PLATZ MEHR IN DER GENLISTE");

        }



        ds_gruppe = new DataSource_Gruppe(this);
        ds_gruppe.open();
        ds_gruppe.createGruppe(n.getStringId(),n.getName(),n.getExternName(),n.getS1(),n.getS2(),n.getS3(),n.getS4(),n.getS5(),n.getS6(),n.getS7(),n.getS8(),n.getS9(),n.getS10());
        ds_gruppe.close();




    }
    public String getCheckedRadioFromSeparator(Separator s) {

        ds_separator = new DataSource_Separator(this);
        ds_separator.open();
        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(s.getStringId());
        ds_separator.close();


        for (Radio r : radioList) {
            if(r.isChecked()){

              return r.getName();
            }

        }
        return null;
    }

    public String createGen(Separator s) {

        String gen = ""; // wird so aussehen "vormund+schüler+personal+leitung#0010"
        String separator_id = s.getStringId(); // wird so aussehen "vormund+schüler+personal+leitung"


        gen = gen + separator_id;
        gen = gen + "#";

        ds_separator = new DataSource_Separator(this);
        ds_separator.open();
        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(separator_id);
        ds_separator.close();


        for (Radio r : radioList) {
            String val = (r.isChecked()) ? "1" : "0";
            gen = gen + val;

        }

        return gen;
    }

    public void buildSeparatorWithRadios(List<String> radioNames, String sepName, Gruppe neededGruppe, Gruppe possibleGruppe) {

        Separator ss = new Separator();
        ss.setName(sepName);


        Collections.sort(radioNames);
        String sep_id = "";
        for (String v : radioNames) {
            sep_id = sep_id + v + "+";

        }

        sep_id = sep_id.substring(0, sep_id.length() - 1);

        ss.setStringId(sep_id);
        ss.setNeed(neededGruppe.getStringId());


        boolean goodPossible = false;


        if (possibleGruppe.getStringId().equals(neededGruppe.getStringId())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS1())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS2())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS3())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS4())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS5())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS6())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS7())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS8())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS9())) {
            goodPossible = true;
        } else if (possibleGruppe.getStringId().equals(neededGruppe.getS10())) {
            goodPossible = true;
        }

        if (goodPossible) {

            ss.setPossible(possibleGruppe.getStringId());


            ds_separator = new DataSource_Separator(this);
            ds_separator.open();
            Separator h = ds_separator.createSeparator(ss.getStringId(), ss.getName(), ss.getNeed(), ss.getPossible());
            ds_separator.close();


            ds_radio = new DataSource_Radio(this);
            ds_radio.open();
            for (String radioName : radioNames) {

                ds_radio.createRadio(radioName, radioName, h.getStringId());

            }
            ds_radio.close();




        }



    }


}
