package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Adinda Rizqi on 11/4/2016.
 */

public class SQLController {

    private DBhelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLiteDatabase getDB() {
        return database;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBhelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        Log.d("SQL BEH", "open: " + database.getPath().toString());
        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String hari, String jp1, String jp2, String jp3,
                       String jp4, String jp5, String jp6,
                       String jp7, String jp8, String jp9,
                       String jp10, String jp11, String jp12) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.HARI, hari);
        contentValue.put(DBhelper.JP_01, jp1);
        contentValue.put(DBhelper.JP_02, jp2);
        contentValue.put(DBhelper.JP_03, jp3);
        contentValue.put(DBhelper.JP_04, jp4);
        contentValue.put(DBhelper.JP_05, jp5);
        contentValue.put(DBhelper.JP_06, jp6);
        contentValue.put(DBhelper.JP_07, jp7);
        contentValue.put(DBhelper.JP_08, jp8);
        contentValue.put(DBhelper.JP_09, jp9);
        contentValue.put(DBhelper.JP_10, jp10);
        contentValue.put(DBhelper.JP_11, jp11);
        contentValue.put(DBhelper.JP_12, jp12);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{
                DBhelper.HARI,
                DBhelper.JP_01,
                DBhelper.JP_02,
                DBhelper.JP_03,
                DBhelper.JP_04,
                DBhelper.JP_05,
                DBhelper.JP_06,
                DBhelper.JP_07,
                DBhelper.JP_08,
                DBhelper.JP_09,
                DBhelper.JP_10,
                DBhelper.JP_11,
                DBhelper.JP_12};
        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(String hari, String jp1, String jp2, String jp3,
                      String jp4, String jp5, String jp6,
                      String jp7, String jp8, String jp9,
                      String jp10, String jp11, String jp12) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.JP_01, jp1);
        contentValue.put(DBhelper.JP_02, jp2);
        contentValue.put(DBhelper.JP_03, jp3);
        contentValue.put(DBhelper.JP_04, jp4);
        contentValue.put(DBhelper.JP_05, jp5);
        contentValue.put(DBhelper.JP_06, jp6);
        contentValue.put(DBhelper.JP_07, jp7);
        contentValue.put(DBhelper.JP_08, jp8);
        contentValue.put(DBhelper.JP_09, jp9);
        contentValue.put(DBhelper.JP_10, jp10);
        contentValue.put(DBhelper.JP_11, jp11);
        contentValue.put(DBhelper.JP_12, jp12);
        int i = database.update(DBhelper.TABLE_NAME, contentValue,
                DBhelper.HARI + " = " + hari, null);
        return i;
    }

    public void delete(String hari) {
        database.delete(DBhelper.TABLE_NAME, DBhelper.HARI + "=\"" + hari + "\"", null);
    }
}
