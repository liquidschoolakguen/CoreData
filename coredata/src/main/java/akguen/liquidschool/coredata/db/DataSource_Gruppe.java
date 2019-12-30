package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.SP_Fach;
import akguen.liquidschool.coredata.model.Gruppe;


public class DataSource_Gruppe {

    private static final String LOG_TAG = DataSource_Gruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.GRUPPE_COLUMN_ID,
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




    public DataSource_Gruppe(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Gruppe createGruppe(String v1) {
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.GRUPPE_COLUMN_NAME, v1);


        long insertId = database.insert(MyDbHelper.TABLE_GRUPPE, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, MyDbHelper.GRUPPE_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe = cursorToGruppe(cursor);
        cursor.close();

        return gruppe;
    }

    public void deleteGruppe(Gruppe gruppe) {
        String id = gruppe.getId();

        database.delete(MyDbHelper.TABLE_GRUPPE,
                MyDbHelper.GRUPPE_COLUMN_ID + "=" + id,
                null);

        // Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + id + " Inhalt: " + gruppe.toString());
    }


    public Gruppe getGruppeById(String id) {


        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, MyDbHelper.GRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe = cursorToGruppe(cursor);
        cursor.close();

        return gruppe;


    }

    private Gruppe cursorToGruppe(Cursor cursor) {

        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_NAME);
        int id2 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_EXTERNNAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S1);
        int id4 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S2);
        int id5 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S3);
        int id6 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S4);
        int id7 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S5);
        int id8 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S6);
        int id9 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S7);
        int id10 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S8);
        int id11 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S9);
        int id12 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_S10);

        String id = cursor.getString(id0);
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


        return new Gruppe(id, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12);
    }

    public List<Gruppe> getAllGruppes() {
        List<Gruppe> gruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe;

        while (!cursor.isAfterLast()) {
            gruppe = cursorToGruppe(cursor);
            gruppeList.add(gruppe);
          // Log.d(LOG_TAG, "ID: " + gruppe.getId() + ", Inhalt: " + gruppe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gruppeList;
    }







}