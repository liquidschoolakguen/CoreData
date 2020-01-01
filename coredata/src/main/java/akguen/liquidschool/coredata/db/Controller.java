package akguen.liquidschool.coredata.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Radio;
import akguen.liquidschool.coredata.model.Separator;

public class Controller {

    Context c;

    private DataSource_Gruppe ds_gruppe;
    private DataSource_Separator ds_separator;
    private DataSource_Radio ds_radio;



    public Controller(Context context) {
        this.c = context;
    }

    public void openAll() {

        ds_gruppe = new DataSource_Gruppe(c);
        ds_gruppe.open();

        ds_separator = new DataSource_Separator(c);
        ds_separator.open();

        ds_radio = new DataSource_Radio(c);
        ds_radio.open();

    }

    public void closeAll() {

        ds_gruppe.close();
        ds_separator.close();
        ds_radio.close();

    }


 
    
    public void buildGruppe(Gruppe vaterGruppe, Separator s) {


        Gruppe n = new Gruppe();


        if (vaterGruppe == null) {

            // erste Gruppe
            n.setStringId("main");
            n.setName("main");
            n.setExternName("main");

            n.setVaterStringId("");


            ds_gruppe.createGruppe(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());


            return;

        }


        n.setStringId(createGen(s));
        n.setExternName(getCheckedRadioFromSeparator(s) + " Gruppe");

        n.setVaterStringId(vaterGruppe.getStringId());


        ds_gruppe.createGruppe(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());


    }


    public List<Separator> getVisibleSeparatorsOfGruppe(Gruppe gruppe) {

        List<Separator> separatorList = ds_separator.getAllSeparators();
        List<Separator> visibles = new ArrayList<Separator>();

        for (Separator s : separatorList) {
            boolean passt = false;

            if (s.getNeed().equals(gruppe.getStringId())) {
                s.setVisibility(2);
                visibles.add(s);

            } else {
                s.setVisibility(0);

                for (Gruppe gg : holeAlleVäter(gruppe)) {


                    if (s.getNeed().equals(gg.getStringId())) {
                        s.setVisibility(1);
                        visibles.add(s);
                        System.out.print("++++++++++++++++Gefunden Ende+++++++++++++++++");

                        break;
                    }
                }
            }
        }

       // return separatorList;

        return visibles;
    }


    public List<Gruppe> holeAlleVäter(Gruppe g) {


        List<Gruppe> väter = new ArrayList<Gruppe>();

        Gruppe toCheck = g;
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            System.out.print("Enter: " + toCheck.getStringId());


            if (toCheck.getStringId().equals("main")) {

                System.out.print("++++++++++++++++++main Ende+++++++++++++++++++");
                break;
            }


            toCheck = ds_gruppe.getGruppeByStringId(toCheck.getVaterStringId());


            väter.add(toCheck);


        }


        return väter;
    }

    public String getCheckedRadioFromSeparator(Separator s) {


        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(s.getStringId());


        String externname = "";
        for (Radio r : radioList) {
            if (r.isChecked()) {

                externname = externname + " + " + r.getName();
            }

        }

        externname = externname.substring(0, externname.length() - 4);

        return externname;
    }

    public String createGen(Separator s) {

        String gen = ""; // wird so aussehen "vormund+schüler+personal+leitung#0010"
        String separator_id = s.getStringId(); // wird so aussehen "vormund+schüler+personal+leitung"


        gen = gen + separator_id;
        gen = gen + "#";


        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(separator_id);


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
        } else {


            for (Gruppe ggg : holeAlleVäter(neededGruppe)) {
                if (possibleGruppe.getStringId().equals(neededGruppe.getStringId())) {
                    goodPossible = true;
                    break;

                }

            }
        }

        if (goodPossible) {

            ss.setPossible(possibleGruppe.getStringId());

            Separator h = ds_separator.createSeparator(ss.getStringId(), ss.getName(), ss.getNeed(), ss.getPossible());


            for (String radioName : radioNames) {

                ds_radio.createRadio(radioName, radioName, h.getStringId());

            }


        }


    }


}
