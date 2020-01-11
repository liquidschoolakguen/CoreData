package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Separator;
import akguen.liquidschool.coredata.model.Radio;

public class DataSource_Separator {

    private static final String LOG_TAG = DataSource_Separator.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.SEPARATOR_COLUMN_ID,
            MyDbHelper.SEPARATOR_COLUMN_STRINGID,
            MyDbHelper.SEPARATOR_COLUMN_NAME,
            MyDbHelper.SEPARATOR_COLUMN_NEED

    };


    private String[] radioColumns = {
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_ID,
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_STRINGID,
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_NAME,
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID,
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_FORMULARCHECKED,
            MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_DEFAULTCHECKED


    };



    public DataSource_Separator(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();

    }

    public Separator createSeparator(String v1, String v2, String v3) {

        Separator gibs = getSeparatorByStringId(v1);
        if (gibs != null) {

            return gibs;

        }

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SEPARATOR_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.SEPARATOR_COLUMN_NAME, v2);
        values.put(MyDbHelper.SEPARATOR_COLUMN_NEED, v3);


        long insertId = database.insert(MyDbHelper.TABLE_SEPARATOR, null, values);

        Cursor cursor = database.query(MyDbHelper.TABLE_SEPARATOR,
                columns, MyDbHelper.SEPARATOR_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Separator separator = cursorToSeparator(cursor);
        cursor.close();

        return separator;
    }

    public void deleteSeparator(Separator separator) {
        String id = separator.getStringId();

        database.delete(MyDbHelper.TABLE_SEPARATOR,
                MyDbHelper.SEPARATOR_COLUMN_ID + "=" + id,
                null);

    }

    public Separator updateSeparator(long id, String v1, String v2, String v3) {


        Separator gibs = getSeparatorByStringId(v1);
        if (gibs != null) {

            return gibs;

        }

        ContentValues values = new ContentValues();
        values.put(MyDbHelper.SEPARATOR_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.SEPARATOR_COLUMN_NAME, v2);
        values.put(MyDbHelper.SEPARATOR_COLUMN_NEED, v3);


        database.update(MyDbHelper.TABLE_SEPARATOR,
                values,
                MyDbHelper.SEPARATOR_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_SEPARATOR,
                columns, MyDbHelper.SEPARATOR_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Separator separator = cursorToSeparator(cursor);
        cursor.close();

        return separator;
    }



    public Separator getSeparatorByStringId(String stringId){


        Cursor cursor = database.query(MyDbHelper.TABLE_SEPARATOR,
                columns, MyDbHelper.SEPARATOR_COLUMN_STRINGID + "='" + stringId+"'",
                null, null, null, null);

        cursor.moveToFirst();
        Separator separator = cursorToSeparator(cursor);
        cursor.close();

        return separator;


    }





    //Child     //Parent
    public List<Radio> getRadiosFromSeparatorById(String i) {

        List<Radio> radioList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_RADIO+ " LEFT JOIN " + MyDbHelper.TABLE_SEPARATOR + " ON " + MyDbHelper.TABLE_SEPARATOR + "."
                        + MyDbHelper.SEPARATOR_COLUMN_ID + " = " + MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID,
                radioColumns,
                MyDbHelper.TABLE_RADIO + "." + MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID+ " = '" +i+"'",
                null, null, null, null);

        cursor.moveToFirst();
        Radio radio;

        while (!cursor.isAfterLast()) {
            radio = cursorToRadio(cursor);
            radioList.add(radio);
            //Log.d(LOG_TAG, "ID: " + angehoeriger);
            cursor.moveToNext();
        }


        cursor.close();


        return radioList;
    }


    private Radio cursorToRadio(Cursor cursor) {


        if (cursor.getCount() == 0) {
            //Log.d(LOG_TAG, "Keinen Radio mit der gewünschten StringId gefunden");
            return null;
        }
        int id0 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_STRINGID);
        int id2 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_NAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID);
        int id4 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_FORMULARCHECKED);
        int id5 = cursor.getColumnIndex(MyDbHelper.RADIO_COLUMN_DEFAULTCHECKED);


        long id = cursor.getLong(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);
        int q4 = cursor.getInt(id4);
        int q5 = cursor.getInt(id5);


        boolean boo4 = q4==1?true:false;
        boolean boo5 = q5==1?true:false;
        return new Radio(id, q1, q2, q3, boo4, boo5);
    }




    private Separator cursorToSeparator(Cursor cursor) {

        if(cursor.getCount()==0){
            //Log.d(LOG_TAG, "Keinen Kollegen mit der gewünschten Id gefunden");
            return null;
        }

        int id0 = cursor.getColumnIndex(MyDbHelper.SEPARATOR_COLUMN_ID);
        int id1 = cursor.getColumnIndex(MyDbHelper.SEPARATOR_COLUMN_STRINGID);
        int id2 = cursor.getColumnIndex(MyDbHelper.SEPARATOR_COLUMN_NAME);
        int id3 = cursor.getColumnIndex(MyDbHelper.SEPARATOR_COLUMN_NEED);


        long id = cursor.getLong(id0);
        String q1 = cursor.getString(id1);
        String q2 = cursor.getString(id2);
        String q3 = cursor.getString(id3);


       // Separator separator = new Separator(id,vorname,nachname,passwort,kuerzel,status);

        return new Separator(id,q1,q2,q3);
    }

    public List<Separator> getAllSeparators() {
        List<Separator> separatorList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_SEPARATOR,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Separator separator;

        while(!cursor.isAfterLast()) {
            separator = cursorToSeparator(cursor);
            separatorList.add(separator);
            cursor.moveToNext();
        }

        cursor.close();

        return separatorList;
    }

}