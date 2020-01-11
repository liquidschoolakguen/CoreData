package akguen.liquidschool.coredata.db;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public Gruppe buildGruppe(Gruppe vaterGruppe, Separator s) {


        Gruppe n = new Gruppe();


        n.setStringId(vaterGruppe.getStringId() + "\n" + createGen(s));
        n.setName(createGen(s));
        n.setExternName(vaterGruppe.getExternName() + "\n>" + getNameOfCheckedRadioFromSeparator(s));

        n.setVaterStringId(vaterGruppe.getStringId());

        if(checkIfIntenticalGruppeExists(n.getStringId())){

            return null;

        }
        return ds_gruppe.createGruppe(n.getStringId(), n.getName(), n.getExternName(), n.getVaterStringId());


    }

    public static int benno(Gruppe g, Separator s) {


        String tempArray1[];
        String tempArray2[];

        String delimiter = "\n";

        String s1 = "Hallo\nwie\ngeht\nes\ndir?\n";
        String s2 = "Hallo\nwie\nes\ngeht\ndir?\n";


        tempArray1 = s1.split(delimiter);
        tempArray2 = s2.split(delimiter);

        for (int i = 0; i < tempArray1.length; i++) {

            //  Log.d("GruppeTest3", "tempArray1[i] " + tempArray1[i]);


        }

        for (int i = 0; i < tempArray2.length; i++) {

            //  Log.d("GruppeTest3", "tempArray2[i] " + tempArray2[i]);


        }


        Set<String> mySet1 = new HashSet<>(Arrays.asList(tempArray1));
        Set<String> mySet2 = new HashSet<>(Arrays.asList(tempArray2));

                /*Set<String> h1 = new HashSet<String>();
                h1.add("For");
                h1.add("Geeks");
                h1.add("Example");
                h1.add("Set");


                Set<String> h2 = new HashSet<String>();
                h2.add("For");
                h2.add("Geeks");
                h2.add("Example");
                h2.add("Set");
                h2.add("Geeks");*/

        if (mySet1.containsAll(mySet2) && mySet2.containsAll(mySet1)) {
            // Toast.makeText(getApplicationContext(), "geklappt: " , Toast.LENGTH_LONG).show();
            //  Log.d("GruppeTest3", "geklappt ");

        } else {
            //  Log.d("GruppeTest3", "nein ");
            //Toast.makeText(getApplicationContext(), "nein: " , Toast.LENGTH_LONG).show();
        }


        return 1;

    }

    public boolean checkIfIntenticalGruppeExists(String stringId) {
        boolean gibs_schon=false;
        String tempArray[];
        String delimiter = "\n";
        tempArray = stringId.split(delimiter);
        Set<String> mySet1 = new HashSet<>(Arrays.asList(tempArray));



        List<Gruppe> all = ds_gruppe.getAllGruppes();

        for(Gruppe ggg: all){

            String tempArrayX[];

            tempArrayX = ggg.getStringId().split(delimiter);
            Set<String> mySetX = new HashSet<>(Arrays.asList(tempArrayX));

            if (mySet1.containsAll(mySetX) && mySetX.containsAll(mySet1)) {
                gibs_schon=true;
                  Log.d("GruppeTest3", "diese Gruppe gibt es schon ");

            }


        }


        return gibs_schon;
    }

    public List<Separator> getVisibleSeparatorsOfGruppe(Gruppe gruppe) {

        List<Separator> separatorList = ds_separator.getAllSeparators();
        List<Separator> visibles = new ArrayList<Separator>();


        for (Separator s : separatorList) {
            int isVisible = -1;

            List<Gruppe> alleVäter = holeAlleVäter(gruppe);

           /* int ip1 = gruppe.getName().indexOf('#');
            String namensStammGruppe = gruppe.getName().substring(0,ip1);
            Log.d("GruppeTest3", "gruppe.getStringId() "+gruppe.getStringId());*/


            if (gruppe.getStringId().contains(s.getStringId())) {

                // wenn die Gruppe erstellt wurde mit Hilfe des Seps, dann wird der Sep nicht mehr gebraucht
                // die StringId gibt ja auch an wer die väter sind. Wenn also die StringId des seps in der
                // id der Gruppe steckt, dann kann das auch bedeuten, dass ein Vater der Gruppe mit Hilfe des seps
                // erstellt wurde. So oder so: die sep muss ausgeblendet werden.

                isVisible = 0;

            } else {

                //Weder die Gruppe noch eines ihrer Väter wurde mit dem Sep erstellt

                if (gruppe.getStringId().contains(s.getNeed())) {

                    // wenn der Sep genau diese Gruppe braucht ODER der Sep eines der Väter braucht,
                    // muss der sep eingeblendet werden -> Denn es ist nicht möglich, dass der Sep
                    // bereits zum Gruppenerstellen eingesetzt wurde
                    isVisible = 1;

                }


            }




            if(isVisible==1){

               visibles.add(s);

            }
            Log.d("GruppeTest3", "visible:  "+isVisible+ s.getStringId());

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


            if (toCheck.getStringId().equals("main#1")) {

                System.out.print("++++++++++++++++++main Ende+++++++++++++++++++");
                break;
            }


            toCheck = ds_gruppe.getGruppeByStringId(toCheck.getVaterStringId());


            väter.add(toCheck);


        }


        return väter;
    }

    public String getNameOfCheckedRadioFromSeparator(Separator s) {


        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(s.getStringId());


        for (Radio r : radioList) {
            if (r.isFormular_checked()) {

                return r.getName();
            }

        }

        //externname = externname.substring(0, externname.length() - 3);

        return null;
    }

    public String createGen(Separator s) {

        String gen = ""; // wird so aussehen "vormund+schüler+personal+leitung#0010"
        String separator_id = s.getStringId(); // wird so aussehen "vormund+schüler+personal+leitung"


        gen = gen + separator_id;
        gen = gen + "#";


        List<Radio> radioList = ds_separator.getRadiosFromSeparatorById(separator_id);


        for (Radio r : radioList) {
            String val = (r.isFormular_checked()) ? "1" : "0";
            gen = gen + val;

        }

        return gen;
    }

    public Separator buildSeparatorWithRadios(List<Radio> radios, String sepName, Gruppe neededGruppe) {


        Separator ss = new Separator();
        ss.setName(sepName);


        List<String> strs = new ArrayList<String>();

        for (Radio r : radios) {
            r.setStringId(r.getName());

            strs.add(r.getName());
        }

        Collections.sort(strs);
        String sep_id = "";
        for (String v : strs) {
            sep_id = sep_id + v + "+";

        }

        sep_id = sep_id.substring(0, sep_id.length() - 1);

        ss.setStringId(sep_id);
        ss.setNeed(neededGruppe.getName());


        Separator h = ds_separator.createSeparator(ss.getStringId(), ss.getName(), ss.getNeed());


        for (Radio rrr : radios) {

            ds_radio.createRadio(rrr.getStringId(), rrr.getName(), h.getStringId(), false, rrr.isDefault_checked());

        }


        return null;
    }


}
