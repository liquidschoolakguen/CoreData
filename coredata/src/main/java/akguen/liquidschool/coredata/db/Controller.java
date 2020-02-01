package akguen.liquidschool.coredata.db;

import android.content.Context;
import android.util.Log;

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
        n.setExternName(vaterGruppe.getExternName() + ">" + getNameOfCheckedRadioFromSeparator(s));

        n.setVaterStringId(vaterGruppe.getStringId());

        if (checkIfIntenticalGruppeExists(n.getStringId())) {

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


    public Separator buildSeparator(String[] radioNames, String sepName, String needed) {

        List<String> list = Arrays.asList(radioNames);
        List<Radio> radios = new ArrayList<Radio>();

        for (String f : list) {

            Radio r = new Radio();
            r.setName(f);
            radios.add(r);
            Log.d("GruppeTest4", "build separator "+sepName+ " with radio "+r.getName());

        }

        Gruppe g = new Gruppe();
        g.setName(needed);

        return buildSeparatorWithRadios(radios, sepName, g);

    }


    public boolean checkIfIntenticalGruppeExists(String stringId) {
        boolean gibs_schon = false;
        String tempArray[];
        String delimiter = "\n";
        tempArray = stringId.split(delimiter);
        Set<String> mySet1 = new HashSet<>(Arrays.asList(tempArray));


        List<Gruppe> all = ds_gruppe.getAllGruppes();

        for (Gruppe ggg : all) {

            String tempArrayX[];

            tempArrayX = ggg.getStringId().split(delimiter);
            Set<String> mySetX = new HashSet<>(Arrays.asList(tempArrayX));

            if (mySet1.containsAll(mySetX) && mySetX.containsAll(mySet1)) {
                gibs_schon = true;
                Log.d("GruppeTest3", "diese Gruppe gibt es schon bb ");

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


            if (isVisible == 1) {

                visibles.add(s);

            }
            Log.d("GruppeTest3", "visible:  " + isVisible + s.getStringId());

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
           // Log.d("GruppeTest4", "r.getName()  " + r.getName());
            strs.add(r.getName());
        }

        Collections.sort(strs);
        String sep_id = "";
        for (String v : strs) {
           // Log.d("GruppeTest4", "v  " + v);
            sep_id = sep_id + v + "+";

        }
        //Log.d("GruppeTest4", "sep_id:  " + sep_id);
        sep_id = sep_id.substring(0, sep_id.length() - 1);

        ss.setStringId(sep_id);
        ss.setNeed(neededGruppe.getName());


        Separator h = ds_separator.createSeparator(ss.getStringId(), ss.getName(), ss.getNeed());


        for (Radio rrr : radios) {

            ds_radio.createRadio(rrr.getStringId(), rrr.getName(), h.getStringId(), false, rrr.isDefault_checked());

        }


        return h;
    }


    public void init() {

        // erste Gruppe
        Gruppe wurzelGruppe = new Gruppe();
        wurzelGruppe.setStringId("main#1");
        wurzelGruppe.setName("main#1");
        wurzelGruppe.setExternName("Wurzelgruppe");
        wurzelGruppe.setVaterStringId("");

        wurzelGruppe = ds_gruppe.createGruppe(wurzelGruppe.getStringId(), wurzelGruppe.getName(), wurzelGruppe.getExternName(), wurzelGruppe.getVaterStringId());


        String[] sep1Content = {"Männlich", "Weiblich"};
        String sep1Name = "Geschlechter";
        String sep1Needed = "main#1";
        Separator sep1 = buildSeparator(sep1Content, sep1Name, sep1Needed);

        String[] sep2Content = {"Deutschland", "Österreich", "Schweiz", "Frankreich", "England", "Italien"};
        String sep2Name = "Länder";
        String sep2Needed = "main#1";
        Separator sep2 = buildSeparator(sep2Content, sep2Name, sep2Needed);

        String[] sep4Content = {"staatlich", "privat"};
        String sep4Name = "Schulträgertypen";
        String sep4Needed = "main#1";
        Separator sep4 = buildSeparator(sep4Content, sep4Name, sep4Needed);

        String[] sep5Content = {"Grundschulen", "Stadtteilschulen", "Gymnasien", "Sonderschulen"};
        String sep5Name = "Schultypen";
        String sep5Needed = "main#1";
        Separator sep5 = buildSeparator(sep5Content, sep5Name, sep5Needed);

        String[] sep7Content = {"Arbeiter", "Schüler", "Angehöriger"};
        String sep7Name = "Personentypen";
        String sep7Needed = "main#1";
        Separator sep7 = buildSeparator(sep7Content, sep7Name, sep7Needed);






        //List<Gruppe> geschlechtGruppes = buildAllGruppes(sep1, wurzelGruppe);


        List<Gruppe> landGruppes = buildAllGruppes(sep2, wurzelGruppe);

            String[] sep3Content = {"Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen", "Hamburg", "Hessen", "Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz", "Saarland", "Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen"};
            String sep3Name = "Bundesland";
            String sep3Needed = landGruppes.get(0).getName();
            Separator sep3 = buildSeparator(sep3Content, sep3Name, sep3Needed);




        List<Gruppe> bundesland_von_DeutschlandGruppes = buildAllGruppes(sep3, landGruppes.get(0));
        List<Gruppe> träger_von_hamburgGruppes = buildAllGruppes(sep4, bundesland_von_DeutschlandGruppes.get(5));





        List<Gruppe> schultypen_von_staatlich_von_hamburg_Gruppes = buildAllGruppes(sep5, träger_von_hamburgGruppes.get(0));


        String[] sep6Content = {

        "Albert-Schweitzer-Schule (ehemalige Gesamtschule)",
        //"Bugenhagenschule Alsterdorf (Privatschule)",
        //"Bugenhagenschule Blankenese (Privatschule)",
        "STS Alter Teichweg (GS Alter Teichweg)",
        "STS Altrahlstedt (HRS Altrahlstedt)",
        "STS am Hafen (HRS Königstraße, HRS St. Pauli, Rudolf-Roß-GS)",
        "STS Am Heidberg (GS Am Heidberg)",
        "STS Schule am See (ehem. Grundschule Seeredder, Langform)",
        "STS auf der Veddel (HRS Slomanstieg)",
        "STS Bahrenfeld (GS Bahrenfeld)",
        "STS Bergedorf (GS Bergedorf)",
        "STS Bergstedt (GS Bergstedt)",
        "STS Blankenese (GS Blankenese)",
        "STS Bramfelder Dorfplatz/Hegholt (HRS Bramfelder Dorfplatz, HRS Hegholt)",
        "STS Denksteinweg/Holstenhof (HRS Denksteinweg, HRS Holstenhof)",
        "STS Ehestorfer Weg (HRS Ehestorfer Weg)",
        "STS Eidelstedt (GS Eidelstedt)",
        "Emil-Krause-Schule (bis 2019 STS Barmbek, aus HRS Tieloh, HRS Fraenkelstraße, Emil-Krause-Gymnasium)",
        "STS Eppendorf (GS Eppendorf)",
        "Erich-Kästner-Schule (ehemalige Gesamtschule)",
        "STS Finkenwerder (GS Finkenwerder)",
        "STS Fischbek-Falkenberg (GS Fischbek, HRS Am Falkenberg)",
        "STS Flottbek (Bahrenfeld)",
        //"Franz-von-Assisi-Schule (katholische Privatschule)",
        "Fritz-Schumacher-Schule (ehemalige Gesamtschule)",
        "Geschwister-Scholl-Schule (ehemalige Gesamtschule)",
        "Goethe-Schule Harburg",
        "Gretel-Bergmann-Schule (HRS Allermöhe, GS Allermöhe)",
        "Gyula-Trebitsch-Schule Tonndorf (KGS Tonndorf)",
        "STS Hamburg-Mitte (HRS Griesstraße, Lohmühlen-Aufbaugymnasium)",
        "Heinrich-Hertz-Schule (ehemalige kooperative Gesamtschule)",
        "STS Helmuth Hübener (KGS Benzenbergweg)",
        "STS Horn (GS Horn)",
        "Ida-Ehre-Schule (ehemalige Gesamtschule, Jahnschule)",
        "Irena-Sendler-Schule (Peter-Petersen-Schule)",
        "Julius-Leber-Schule (ehemalige Gesamtschule)",
        "STS Kirchwerder (GS Kirchwerder)",
        "Kurt-Tucholsky-Schule",
        "STS Langenhorn (HRS Langenhorn)",
        "Lessing-STS (HRS Hanhoopsfeld, HRS Sinstorf, Lessing-Aufbaugymnasium)",
        "STS Lohbrügge (GS Lohbrügge)",
        "STS Lurup (HRS Luruper Hauptstraße, HRS Am Altonaer Volkspark)",
        "STS Maretstraße (HRS Maretstraße)",
        "Max-Brauer-Schule (ehemalige Gesamtschule)",
        "Max-Schmeling-STS (Zusammenlegung der ehemaligen HRS Holstenhofweg sowie der HRS Denksteinweg)",
        "STS Meiendorf (HRS Meiendorf)",
        "STS Mümmelmannsberg (GS Mümmelmannsberg)",
        "Nelson-Mandela-Schule (GS Kirchdorf)",
        //"Neue Schule Hamburg (Privatschule, Langform)",
        "STS Niendorf (HRS Sachsenweg, GS Niendorf)",
        "STS Öjendorf (GS Öjendorf)",
        "STS Oldenfelde (HRS Oldenfelde)",
        "Otto-Hahn-Schule (ehemalige Gesamtschule)",
        "STS Poppenbüttel (HRS Poppenbüttler Stieg, GS Poppenbüttel)",
        "STS Richard-Linde-Weg (HRS Richard-Linde-Weg)",
        "STS Rissen (HRS Iserbarg)",
        "STS Querkamp/Steinadlerweg (HRS Steinadlerweg)",
        "STS Stellingen (HRS Sportplatzring, GS Stellingen)",
        "STS St. Georg (ehem. Ganztagsrealschule St. Georg)",
        "STS Stübenhofer Weg (HRS Stübenhofer Weg)",
        "STS Süderelbe (GS Süderelbe)",
        "STS Walddörfer (GS Walddörfer)",
        //"Wichern-Schule (evangelische Privatschule)",
        "STS Wilhelmsburg (GS Wilhelmsburg)",
        "STS Winterhude (GS Winterhude)",

        };

        String sep6Name = "staatliche STS in HH";
        String sep6Needed = schultypen_von_staatlich_von_hamburg_Gruppes.get(1).getName(); //schulTyp: STS
        Separator sep6 = buildSeparator(sep6Content, sep6Name, sep6Needed);



        List<Gruppe> schulen_von_sts_von_staatlich_von_hamburg_Gruppes = buildAllGruppes(sep6, schultypen_von_staatlich_von_hamburg_Gruppes.get(1));


        List<Gruppe> personentypen_von_schulen_von_sts_von_staatlich_von_hamburg_Gruppes = buildAllGruppes(sep7, schulen_von_sts_von_staatlich_von_hamburg_Gruppes.get(3));


    }


    public List<Gruppe> buildAllGruppes(Separator s, Gruppe vater) {
        //florisbela.net@gmail.com
        Log.d("GruppeTest4", "s.getStringId() " + s.getStringId());
        List<Radio> l = ds_separator.getRadiosFromSeparatorById(s.getStringId());
        List<Gruppe> gr = new ArrayList<Gruppe>();

        int i = 0;
        for (Radio g : l) {

            Radio willChecked = l.get(l.indexOf(g));
            willChecked.setFormular_checked(true);

            for (Radio gg : l) {
                if (!gg.equals(willChecked)) {
                    l.get(l.indexOf(gg)).setFormular_checked(false);

                }

            }


            i++;


            for (Radio gggg : l) {

                //Log.d("GruppeTest2", "update " + gggg.getName() + ";  "+ gggg.isFormular_checked());
                ds_radio.updateRadio(gggg.getId(), gggg.getStringId(), gggg.getName(), gggg.getSeparator_id(), gggg.isFormular_checked(), gggg.isDefault_checked());


            }
            //System.out.println(msg);


            gr.add(buildGruppe(vater, s));



            //clear? vielleicht unnötig
            for (Radio ggg : l) {


                ds_radio.updateRadio(ggg.getId(), ggg.getStringId(), ggg.getName(), ggg.getSeparator_id(), false, ggg.isDefault_checked());


            }


        }


        return gr;
    }


}
