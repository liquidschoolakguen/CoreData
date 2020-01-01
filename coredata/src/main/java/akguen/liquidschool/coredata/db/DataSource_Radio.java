package akguen.liquidschool.coredata.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import akguen.liquidschool.coredata.model.Radio;

public class DataSource_Radio {

    private static final String LOG_TAG = DataSource_Radio.class.getSimpleName();

    private SQLiteDatabase database;
    private MyDbHelper dbHelper;

    private String[] columns = {
            MyDbHelper.RADIO_COLUMN_ID,
            MyDbHelper.RADIO_COLUMN_STRINGID,
            MyDbHelper.RADIO_COLUMN_NAME,
            MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID,
            MyDbHelper.RADIO_COLUMN_FORMULARCHECKED,
            MyDbHelper.RADIO_COLUMN_DEFAULTCHECKED
    };

    public DataSource_Radio(Context context) {
        dbHelper = new MyDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Radio createRadio(String v1, String v2, String v3, boolean v4, boolean v5) {

        Radio gibs = getRadioByStringId(v1);
        if (gibs != null) {

            return gibs;

        }
        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RADIO_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.RADIO_COLUMN_NAME, v2);
        values.put(MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID, v3);
        values.put(MyDbHelper.RADIO_COLUMN_FORMULARCHECKED, v4);
        values.put(MyDbHelper.RADIO_COLUMN_DEFAULTCHECKED, v5);


        long insertId = database.insert(MyDbHelper.TABLE_RADIO, null, values);


        Cursor cursor = database.query(MyDbHelper.TABLE_RADIO,
                columns, MyDbHelper.RADIO_COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Radio radio = cursorToRadio(cursor);
        cursor.close();

        return radio;
    }

    public void deleteRadio(Radio radio) {
        long id = radio.getId();

        database.delete(MyDbHelper.TABLE_RADIO,
                MyDbHelper.RADIO_COLUMN_ID + "=" + id,
                null);

    }


    public Radio updateRadio(long id, String v1, String v2, String v3, boolean v4, boolean v5) {

        Radio gibs = getRadioByStringId(v1);
        if (gibs != null) {

            return gibs;

        }



        ContentValues values = new ContentValues();
        values.put(MyDbHelper.RADIO_COLUMN_STRINGID, v1);
        values.put(MyDbHelper.RADIO_COLUMN_NAME, v2);
        values.put(MyDbHelper.RADIO_COLUMN_SEPARATOR_COLUMN_ID, v3);
        values.put(MyDbHelper.RADIO_COLUMN_FORMULARCHECKED, v4);
        values.put(MyDbHelper.RADIO_COLUMN_DEFAULTCHECKED, v5);




        database.update(MyDbHelper.TABLE_RADIO,
                values,
                MyDbHelper.RADIO_COLUMN_ID + "=" + id,
                null);

        Cursor cursor = database.query(MyDbHelper.TABLE_RADIO,
                columns, MyDbHelper.RADIO_COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();
        Radio radio = cursorToRadio(cursor);
        cursor.close();

        return radio;
    }




    public Radio getRadioByStringId(String stringId) {


        Cursor cursor = database.query(MyDbHelper.TABLE_RADIO,
                columns, MyDbHelper.RADIO_COLUMN_STRINGID + "=" + stringId,
                null, null, null, null);

        cursor.moveToFirst();
        Radio radio = cursorToRadio(cursor);
        cursor.close();

        return radio;


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

        boolean bb4 = q4==1 ? true:false;
        boolean bb5 = q5==1 ? true:false;


        return new Radio(id, q1, q2, q3, bb4, bb5);
    }

    public List<Radio> getAllRadios() {
        List<Radio> radioList = new ArrayList<>();

        Cursor cursor = database.query(MyDbHelper.TABLE_RADIO,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Radio radio;

        while (!cursor.isAfterLast()) {
            radio = cursorToRadio(cursor);
            radioList.add(radio);

            cursor.moveToNext();
        }

        cursor.close();

        return radioList;
    }

}