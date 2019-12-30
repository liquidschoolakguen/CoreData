package akguen.liquidschool.coredata.db;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
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

            if(s.getNeed().equals(gruppe.getId())){
                s.setVisibility(2);

            }else if(s.getNeed().equals(gruppe.getS1()) ||
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
            }else{

                s.setVisibility(0);
            }







        }


        return separatorList;
    }


    public Gruppe buildGruppe(Gruppe vaterGruppe, Separator s) {

        Gruppe neueGruppe = new Gruppe();

        if (s == null) {

            // ohne Seperator keine neue Gruppe, VaterGruppe wird zurückgegeben

            return vaterGruppe;
        }

        if (vaterGruppe == null) {

            // erste Gruppe
            neueGruppe.setId("main");
            neueGruppe.setName("main");
            neueGruppe.setExternName("liquidschool Menschen");

            neueGruppe.setS1("");
            neueGruppe.setS2("");
            neueGruppe.setS3("");
            neueGruppe.setS4("");
            neueGruppe.setS5("");
            neueGruppe.setS6("");
            neueGruppe.setS7("");
            neueGruppe.setS8("");
            neueGruppe.setS9("");
            neueGruppe.setS10("");


            return neueGruppe;
        }


        neueGruppe.setS1(vaterGruppe.getS1());
        neueGruppe.setS2(vaterGruppe.getS2());
        neueGruppe.setS3(vaterGruppe.getS3());
        neueGruppe.setS4(vaterGruppe.getS4());
        neueGruppe.setS5(vaterGruppe.getS5());
        neueGruppe.setS6(vaterGruppe.getS6());
        neueGruppe.setS7(vaterGruppe.getS7());
        neueGruppe.setS8(vaterGruppe.getS8());
        neueGruppe.setS9(vaterGruppe.getS9());
        neueGruppe.setS10(vaterGruppe.getS10());


        if(neueGruppe.getS1().equals("")){
            neueGruppe.setS1(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS2().equals("")){
            neueGruppe.setS2(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS3().equals("")){
            neueGruppe.setS3(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS4().equals("")){
            neueGruppe.setS4(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS5().equals("")){
            neueGruppe.setS5(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS6().equals("")){
            neueGruppe.setS6(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS7().equals("")){
            neueGruppe.setS7(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS8().equals("")){
            neueGruppe.setS8(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS9().equals("")){
            neueGruppe.setS9(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else if(neueGruppe.getS10().equals("")){
            neueGruppe.setS10(vaterGruppe.getId());
            neueGruppe.setId(createGen(s));
        }else{


            System.out.println("KEIN PLATZ MEHR IN DER GENLISTE");

        }



        return neueGruppe;

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

    public Separator buildSeparator(List<Radio> radios, Gruppe neededGruppe){

        Separator ss = new Separator();
        List<String> l = new ArrayList<String>();

        for(Radio r : radios){
           l.add(r.getStringId());

        }
        Collections.sort(l);

        String sep_id="";
        for(String v : l){
            sep_id = sep_id + v + "+";

        }

        sep_id = sep_id.substring(0, sep_id.length() - 1);

       ss.setStringId(sep_id);
       ss.setNeed(neededGruppe.getId());

        return ss;

    }


}
