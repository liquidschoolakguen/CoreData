package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Subjekt;


public class DataSource_Subjekt {

    private static final String LOG_TAG = DataSource_Subjekt.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = new String[]{
            MyDbHelper.SUBJEKT_COLUMN_ID,
            MyDbHelper.SUBJEKT_COLUMN_VORNAME,
            MyDbHelper.SUBJEKT_COLUMN_NACHNAME,
            MyDbHelper.SUBJEKT_COLUMN_KUERZEL,
            MyDbHelper.SUBJEKT_COLUMN_BENUTZERNAME,
            MyDbHelper.SUBJEKT_COLUMN_BENUTZERPASSWORT

    };

    public DataSource_Subjekt(Context context) {

        dbHelper = new MyDbHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();

    }

    public Subjekt createSubjekt(String v1, String v2, String v3, String v4, String v5) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SUBJEKT_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SUBJEKT_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.SUBJEKT_COLUMN_KUERZEL, v3);
        values.put(MyDbHelper.SUBJEKT_COLUMN_BENUTZERNAME, v4);
        values.put(MyDbHelper.SUBJEKT_COLUMN_BENUTZERPASSWORT, v5);


        long insertId = database.insert(MyDbHelper.TABLE_SUBJEKT, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT,
                columns, MyDbHelper.SUBJEKT_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Subjekt subjekt = cursorToSubjekt(cursor);
        cursor.close();

        return subjekt;
    }

    public void deleteSubjekt(Subjekt subjekt) {
        long id = subjekt.getId();

        database.delete(MyDbHelper.TABLE_SUBJEKT,
                MyDbHelper.SUBJEKT_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + subjekt.toString());
    }

    public Subjekt updateSubjekt(int id, String v1, String v2, String v3, String v4, String v5) {

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SUBJEKT_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.SUBJEKT_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.SUBJEKT_COLUMN_KUERZEL, v3);
        values.put(MyDbHelper.SUBJEKT_COLUMN_BENUTZERNAME, v4);
        values.put(MyDbHelper.SUBJEKT_COLUMN_BENUTZERPASSWORT, v5);


        database.update(MyDbHelper.TABLE_SUBJEKT,
                values,
                MyDbHelper.SUBJEKT_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT,
                columns, MyDbHelper.SUBJEKT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Subjekt subjekt = cursorToSubjekt(cursor);
        cursor.close();

        return subjekt;
    }
    public Subjekt getSubjektById(int id){


        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT,
                columns, MyDbHelper.SUBJEKT_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Subjekt subjekt = cursorToSubjekt(cursor);
        cursor.close();

        return subjekt;


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

    public List<Subjekt> getAllSubjekts() {
        List<Subjekt> subjektList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SUBJEKT,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Subjekt subjekt;

        while(!cursor.isAfterLast()) {
            subjekt = cursorToSubjekt(cursor);
            subjektList.add(subjekt);
            Log.d(LOG_TAG, "ID: " + subjekt.getId() + ", Inhalt: " + subjekt.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return subjektList;
    }

}