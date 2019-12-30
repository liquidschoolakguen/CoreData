package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Gruppe2;


public class DataSource_Gruppe2 {

    private static final String LOG_TAG = DataSource_Gruppe2.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.GRUPPE2_COLUMN_ID,
            MyDbHelper.GRUPPE2_COLUMN_STRINGID,
            MyDbHelper.GRUPPE2_COLUMN_NAME,
            MyDbHelper.GRUPPE2_COLUMN_EXTERNNAME,
            MyDbHelper.GRUPPE2_COLUMN_VATERSTRINGID


    };


    public DataSource_Gruppe2(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Gruppe2 createGruppe2(String v1, String v2, String v3, String v4) {

        Gruppe2 gibs = getGruppe2ByStringId(v1);
        if (gibs != null) {

            return gibs;

        }


        ContentValues values = new ContentValues();

        values.put(MyDbHelper.GRUPPE2_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.GRUPPE2_COLUMN_NAME, v2);
        values.put(MyDbHelper.GRUPPE2_COLUMN_EXTERNNAME, v3);
        values.put(MyDbHelper.GRUPPE2_COLUMN_VATERSTRINGID, v4);


        long insertId = database.insert(MyDbHelper.TABLE_GRUPPE2, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE2,
                columns, MyDbHelper.GRUPPE2_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe2 gruppe2 = cursorToGruppe2(cursor);
        cursor.close();

        return gruppe2;
    }

    public void deleteGruppe2(Gruppe2 gruppe2) {
        long id = gruppe2.getId();

        database.delete(MyDbHelper.TABLE_GRUPPE2,
                MyDbHelper.GRUPPE2_COLUMN_ID + "=" + id,
                null);
    }





    public Gruppe2 updateGruppe2(long id, String v1, String v2, String v3, String v4) {

        Gruppe2 gibs = getGruppe2ByStringId(v1);
        if (gibs != null) {

            return gibs;

        }



        ContentValues values = new ContentValues();

        values.put(MyDbHelper.GRUPPE2_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.GRUPPE2_COLUMN_NAME, v2);
        values.put(MyDbHelper.GRUPPE2_COLUMN_EXTERNNAME, v3);
        values.put(MyDbHelper.GRUPPE2_COLUMN_VATERSTRINGID, v4);



        database.update(MyDbHelper.TABLE_GRUPPE2,
                values,
                MyDbHelper.GRUPPE2_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE2,
                columns, MyDbHelper.GRUPPE2_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe2 gruppe2 = cursorToGruppe2(cursor);
        cursor.close();

        return gruppe2;
    }









    public Gruppe2 getGruppe2ByStringId(String stringId) {


        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE2,
                columns, MyDbHelper.GRUPPE2_COLUMN_STRINGID + "=" + stringId,
                null, null, null, null);

        cursor.moveToFirst();
        Gruppe2 gruppe2 = cursorToGruppe2(cursor);
        cursor.close();

        return gruppe2;


    }

    private Gruppe2 cursorToGruppe2(Cursor cursor) {

        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }


        int id0 = cursor.getColumnIndex(MyDbHelper.GRUPPE2_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.GRUPPE2_COLUMN_STRINGID);
        int id2 = cursor.getColumnIndex(MyDbHelper.GRUPPE2_COLUMN_NAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.GRUPPE2_COLUMN_EXTERNNAME);
        int id4 = cursor.getColumnIndex(MyDbHelper.GRUPPE2_COLUMN_VATERSTRINGID);


        long id = cursor.getLong(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        String q4 = cursor.getString(id4);


        return new Gruppe2(id, q1, q2, q3, q4);
    }

    public List<Gruppe2> getAllGruppe2s() {
        List<Gruppe2> gruppe2List = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_GRUPPE2,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Gruppe2 gruppe2;

        while (!cursor.isAfterLast()) {
            gruppe2 = cursorToGruppe2(cursor);
            gruppe2List.add(gruppe2);
            // Log.d(LOG_TAG, "ID: " + gruppe2.getStringId() + ", Inhalt: " + gruppe2.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return gruppe2List;
    }


}