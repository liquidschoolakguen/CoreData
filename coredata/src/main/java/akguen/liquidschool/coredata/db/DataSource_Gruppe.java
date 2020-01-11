package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Gruppe;


public class DataSource_Gruppe {

    private static final String LOG_TAG = DataSource_Gruppe.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;
    private Context context;


    private String[] columns = {
            MyDbHelper.GRUPPE_COLUMN_ID,
            MyDbHelper.GRUPPE_COLUMN_STRINGID,
            MyDbHelper.GRUPPE_COLUMN_NAME,
            MyDbHelper.GRUPPE_COLUMN_EXTERNNAME,
            MyDbHelper.GRUPPE_COLUMN_VATERSTRINGID


    };


    public DataSource_Gruppe(Context context) {
        dbHelper = new MyDbHelper(context);
        this.context = context;
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Gruppe createGruppe(String v1, String v2, String v3, String v4) {

        Gruppe gibs = getGruppeByStringId(v1);
        if (gibs != null) {

            return gibs;

        }


        ContentValues values = new ContentValues();

        values.put(MyDbHelper.GRUPPE_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.GRUPPE_COLUMN_NAME, v2);
        values.put(MyDbHelper.GRUPPE_COLUMN_EXTERNNAME, v3);
        values.put(MyDbHelper.GRUPPE_COLUMN_VATERSTRINGID, v4);


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
        long id = gruppe.getId();

        database.delete(MyDbHelper.TABLE_GRUPPE,
                MyDbHelper.GRUPPE_COLUMN_ID + "=" + id,
                null);
    }





    public Gruppe updateGruppe(long id, String v1, String v2, String v3, String v4) {

        Gruppe gibs = getGruppeByStringId(v1);
        if (gibs != null) {

            return gibs;

        }



        ContentValues values = new ContentValues();

        values.put(MyDbHelper.GRUPPE_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.GRUPPE_COLUMN_NAME, v2);
        values.put(MyDbHelper.GRUPPE_COLUMN_EXTERNNAME, v3);
        values.put(MyDbHelper.GRUPPE_COLUMN_VATERSTRINGID, v4);



        database.update(MyDbHelper.TABLE_GRUPPE,
                values,
                MyDbHelper.GRUPPE_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, MyDbHelper.GRUPPE_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe = cursorToGruppe(cursor);
        cursor.close();

        return gruppe;
    }









    public Gruppe getGruppeByStringId(String stringId) {


        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, MyDbHelper.GRUPPE_COLUMN_STRINGID + " = '" + stringId+"'",
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
        int id1 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_STRINGID);
        int id2 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_NAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_EXTERNNAME);
        int id4 = cursor.getColumnIndex(MyDbHelper.GRUPPE_COLUMN_VATERSTRINGID);


        long id = cursor.getLong(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);


        return new Gruppe(id, q1, q2, q3, q4);
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
            // Log.d(LOG_TAG, "ID: " + gruppe.getStringId() + ", Inhalt: " + gruppe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gruppeList;
    }


    public List<Gruppe> getAllGruppesByVaterStringId(String vaterStringId) {
        List<Gruppe> gruppeList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE,
                columns, MyDbHelper.GRUPPE_COLUMN_VATERSTRINGID + "='" + vaterStringId+"'", null, null, null, null);

        cursor.moveToFirst();
        Gruppe gruppe;

        while (!cursor.isAfterLast()) {
            gruppe = cursorToGruppe(cursor);
            gruppeList.add(gruppe);
            // Log.d(LOG_TAG, "ID: " + gruppe.getStringId() + ", Inhalt: " + gruppe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gruppeList;
    }













}