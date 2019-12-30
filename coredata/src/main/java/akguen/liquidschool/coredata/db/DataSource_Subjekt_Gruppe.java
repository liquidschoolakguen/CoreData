package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Gruppe;
import akguen.liquidschool.coredata.model.Subjekt;


public class DataSource_Subjekt_Gruppe {

    private static final String LOG_TAG = DataSource_Subjekt_Gruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SL_SUBJEKT_ID,
            MyDbHelper.SL_GRUPPE_STRINGID,
    };


    private String[] gruppeColumns = {
            MyDbHelper.GRUPPE_COLUMN_ID,
            MyDbHelper.GRUPPE_COLUMN_STRINGID,
            MyDbHelper.GRUPPE_COLUMN_NAME,
            MyDbHelper.GRUPPE_COLUMN_EXTERNNAME,
            MyDbHelper.GRUPPE_COLUMN_S1,
            MyDbHelper.GRUPPE_COLUMN_S2,
            MyDbHelper.GRUPPE_COLUMN_S3,
            MyDbHelper.GRUPPE_COLUMN_S4,
            MyDbHelper.GRUPPE_COLUMN_S5,
            MyDbHelper.GRUPPE_COLUMN_S6,
            MyDbHelper.GRUPPE_COLUMN_S7,
            MyDbHelper.GRUPPE_COLUMN_S8,
            MyDbHelper.GRUPPE_COLUMN_S9,
            MyDbHelper.GRUPPE_COLUMN_S10


    };

    private String[] subjektColumns = {
            MyDbHelper.SUBJEKT_COLUMN_ID,
            MyDbHelper.SUBJEKT_COLUMN_VORNAME,
            MyDbHelper.SUBJEKT_COLUMN_NACHNAME,
            MyDbHelper.SUBJEKT_COLUMN_KUERZEL,
            MyDbHelper.SUBJEKT_COLUMN_BENUTZERNAME,
            MyDbHelper.SUBJEKT_COLUMN_BENUTZERPASSWORT
    };


    public DataSource_Subjekt_Gruppe(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createSubjekt_Gruppe(ContentValues v) {
        database.insert(MyDbHelper.TABLE_SUBJEKT_GRUPPE, null, v);
    }

    public void deleteSubjekt_Gruppe(ContentValues v) {
        database.delete(MyDbHelper.TABLE_SUBJEKT_GRUPPE,
                MyDbHelper.SL_SUBJEKT_ID + " = " + v.getAsString("subjekt_id") + " AND " + MyDbHelper.SL_GRUPPE_STRINGID + " = " + v.getAsString("gruppe_stringid"),
                null);

    }

    public ContentValues updateSubjekt_Gruppe(ContentValues old, ContentValues nu) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SL_SUBJEKT_ID, nu.getAsString("subjekt_id"));
        values.put(MyDbHelper.SL_GRUPPE_STRINGID, nu.getAsString("gruppe_stringid"));


        database.update(MyDbHelper.TABLE_SUBJEKT_GRUPPE,
                values,
                MyDbHelper.SL_SUBJEKT_ID + " = " + old.getAsString("subjekt_id") + " AND " + MyDbHelper.SL_GRUPPE_STRINGID + " = " + old.getAsString("gruppe_stringid"),
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT_GRUPPE,
                columns, MyDbHelper.SL_SUBJEKT_ID + " = " + nu.getAsString("subjekt_id") + " AND " + MyDbHelper.SL_GRUPPE_STRINGID + " = " + nu.getAsString("gruppe_stringid"),
                null, null, null, null);

        cursor.moveToFirst();
        ContentValues coco = cursorToSubjekt_GruppeAsContentValue(cursor);
        cursor.close();

        return coco;

    }


    private ContentValues cursorToSubjekt_GruppeAsContentValue(Cursor cursor) {

        ContentValues values = new ContentValues();


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id1 = cursor.getColumnIndex(MyDbHelper.SL_SUBJEKT_ID);
        int id2 = cursor.getColumnIndex(MyDbHelper.SL_GRUPPE_STRINGID);


        ;
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);

        values.put("subjekt_id", q1);
        values.put("gruppe_stringid", q2);

        // Subjekt_Gruppe subjekt_gruppe = new Subjekt_Gruppe(id,vorname,nachname,passwort,kuerzel,status);

        return values;
    }

    public List<ContentValues> getAllSubjekt_Gruppes() {
        List<ContentValues> subjekt_gruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT_GRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        ContentValues subjekt_gruppe;

        while (!cursor.isAfterLast()) {
            subjekt_gruppe = cursorToSubjekt_GruppeAsContentValue(cursor);
            subjekt_gruppeList.add(subjekt_gruppe);
            //Log.d(LOG_TAG, "ID: " + subjekt_gruppe.toString());
            cursor.moveToNext();
        }


        cursor.close();

        return subjekt_gruppeList;
    }

    private Gruppe cursorToGruppe(Cursor cursor) {

        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_STRINGID);
        int id2 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_NAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_EXTERNNAME);
        int id4 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S1);
        int id5 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S2);
        int id6 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S3);
        int id7 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S4);
        int id8 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S5);
        int id9 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S6);
        int id10 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S7);
        int id11 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S8);
        int id12 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S9);
        int id13 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S10);

        long id = cursor.getLong(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);
        String q6 = cursor.getString(id6);
        String q7 = cursor.getString(id7);
        String q8 = cursor.getString(id8);
        String q9 = cursor.getString(id9);
        String q10 = cursor.getString(id10);
        String q11 = cursor.getString(id11);
        String q12 = cursor.getString(id12);
        String q13 = cursor.getString(id13);

        return new Gruppe(id, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13);
    }


    private Subjekt cursorToSubjekt(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_KUERZEL);
        int id4 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_BENUTZERNAME);
        int id5 = cursor.getColumnIndex(MyDbHelper.SUBJEKT_COLUMN_BENUTZERPASSWORT);



        int id = cursor.getInt(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);
        String q5 = cursor.getString(id5);





        return new Subjekt(id,q1,q2,q3,q4,q5);
    }


    public List<Gruppe> getGruppesFromSubjektById(int i) {

        List<Gruppe> gruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE + " LEFT JOIN " + MyDbHelper.TABLE_SUBJEKT_GRUPPE + " ON " + MyDbHelper.TABLE_SUBJEKT_GRUPPE + "."
                        + MyDbHelper.SL_GRUPPE_STRINGID + " = " + MyDbHelper.GRUPPE_COLUMN_STRINGID,
                gruppeColumns,
                MyDbHelper.TABLE_SUBJEKT_GRUPPE + "." + MyDbHelper.SL_SUBJEKT_ID + " = " + i,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe;

        while (!cursor.isAfterLast()) {
            gruppe = cursorToGruppe(cursor);
            gruppeList.add(gruppe);
            //Log.d(LOG_TAG, "ID: " + gruppe);
            cursor.moveToNext();
        }


        cursor.close();


        return gruppeList;
    }


    public List<Subjekt> getSubjektsFromGruppeByStringId(String stringId) {
        Log.d(LOG_TAG, "ID::::::::::::::::::: " + stringId);
        List<Subjekt> subjektList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT + " LEFT JOIN " + MyDbHelper.TABLE_SUBJEKT_GRUPPE + " ON " + MyDbHelper.TABLE_SUBJEKT_GRUPPE + "."
                        + MyDbHelper.SL_SUBJEKT_ID + " = " + MyDbHelper.SUBJEKT_COLUMN_ID,
                subjektColumns,
                MyDbHelper.TABLE_SUBJEKT_GRUPPE + "." + MyDbHelper.SL_GRUPPE_STRINGID + " = " + stringId,
                null, null, null, null);

        cursor.moveToFirst();
        Subjekt subjekt;

        while (!cursor.isAfterLast()) {
            subjekt = cursorToSubjekt(cursor);
            subjektList.add(subjekt);
            //Log.d(LOG_TAG, "ID: " + gruppe);
            cursor.moveToNext();
        }

        cursor.close();

        return subjektList;
    }


}