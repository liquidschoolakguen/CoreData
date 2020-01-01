package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.*;


public class DataSource_Kollege {

    private static final String LOG_TAG = DataSource_Kollege.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.KOLLEGE_COLUMN_ID,
            MyDbHelper.KOLLEGE_COLUMN_VORNAME,
            MyDbHelper.KOLLEGE_COLUMN_NACHNAME,
            MyDbHelper.KOLLEGE_COLUMN_PERSONALTYP,
            MyDbHelper.KOLLEGE_COLUMN_GEBURTSTAG,
            MyDbHelper.KOLLEGE_COLUMN_STRASSE,
            MyDbHelper.KOLLEGE_COLUMN_PLZ,
            MyDbHelper.KOLLEGE_COLUMN_TELEFON,
            MyDbHelper.KOLLEGE_COLUMN_EMAIL,
            MyDbHelper.KOLLEGE_COLUMN_STANDORT,
            MyDbHelper.KOLLEGE_COLUMN_PASSWORT
    };

    public DataSource_Kollege(Context context) {

        dbHelper = new MyDbHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();

    }


    public Kollege createKollege(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10) throws GeneralSecurityException, IOException {



        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KOLLEGE_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.KOLLEGE_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PERSONALTYP, v3);
        values.put(MyDbHelper.KOLLEGE_COLUMN_GEBURTSTAG, v4);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STRASSE, v5);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PLZ, v6);
        values.put(MyDbHelper.KOLLEGE_COLUMN_TELEFON, v7);
        values.put(MyDbHelper.KOLLEGE_COLUMN_EMAIL, v8);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STANDORT, v9);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PASSWORT, v10);

        long insertId = database.insert(MyDbHelper.TABLE_KOLLEGE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;
    }

    public void deleteKollege(Kollege kollege) {
        long id = kollege.getId();

        database.delete(MyDbHelper.TABLE_KOLLEGE,
                MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + kollege.toString());
    }

    public Kollege updateKollege(int id, String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10) {


        ContentValues values = new ContentValues();
        values.put(MyDbHelper.KOLLEGE_COLUMN_VORNAME, v1);
        values.put(MyDbHelper.KOLLEGE_COLUMN_NACHNAME, v2);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PERSONALTYP, v3);
        values.put(MyDbHelper.KOLLEGE_COLUMN_GEBURTSTAG, v4);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STRASSE, v5);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PLZ, v6);
        values.put(MyDbHelper.KOLLEGE_COLUMN_TELEFON, v7);
        values.put(MyDbHelper.KOLLEGE_COLUMN_EMAIL, v8);
        values.put(MyDbHelper.KOLLEGE_COLUMN_STANDORT, v9);
        values.put(MyDbHelper.KOLLEGE_COLUMN_PASSWORT, v10);


        database.update(MyDbHelper.TABLE_KOLLEGE,
                values,
                MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;
    }

    public Kollege getKollegeById(int id) {


        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, MyDbHelper.KOLLEGE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege = cursorToKollege(cursor);
        cursor.close();

        return kollege;


    }

    private Kollege cursorToKollege(Cursor cursor) {


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_VORNAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_NACHNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_PERSONALTYP);
        int id4 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_GEBURTSTAG);
        int id5 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_STRASSE);
        int id6 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_PLZ);
        int id7 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_TELEFON);
        int id8 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_EMAIL);
        int id9 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_STANDORT);
        int id10 = cursor.getColumnIndex(MyDbHelper.KOLLEGE_COLUMN_PASSWORT);

        int id = cursor.getInt(id0);
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

        return new Kollege(id, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);


    }

    public List<Kollege> getAllKolleges() {
        List<Kollege> kollegeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_KOLLEGE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Kollege kollege;

        while (!cursor.isAfterLast()) {
            kollege = cursorToKollege(cursor);
            kollegeList.add(kollege);
            Log.d(LOG_TAG, "ID: " + kollege.getId() + ", Inhalt: " + kollege.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return kollegeList;
    }

}