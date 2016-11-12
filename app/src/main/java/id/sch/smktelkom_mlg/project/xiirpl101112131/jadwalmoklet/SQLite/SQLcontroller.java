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

    private DBHelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLiteDatabase getDB() {
        return database;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBHelper(ourcontext);
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
        contentValue.put(DBHelper.HARI, hari);
        contentValue.put(DBHelper.JP_01, jp1);
        contentValue.put(DBHelper.JP_02, jp2);
        contentValue.put(DBHelper.JP_03, jp3);
        contentValue.put(DBHelper.JP_04, jp4);
        contentValue.put(DBHelper.JP_05, jp5);
        contentValue.put(DBHelper.JP_06, jp6);
        contentValue.put(DBHelper.JP_07, jp7);
        contentValue.put(DBHelper.JP_08, jp8);
        contentValue.put(DBHelper.JP_09, jp9);
        contentValue.put(DBHelper.JP_10, jp10);
        contentValue.put(DBHelper.JP_11, jp11);
        contentValue.put(DBHelper.JP_12, jp12);
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch(String hari) {
        String[] columns = new String[]{
                DBHelper.HARI,
                DBHelper.JP_01,
                DBHelper.JP_02,
                DBHelper.JP_03,
                DBHelper.JP_04,
                DBHelper.JP_05,
                DBHelper.JP_06,
                DBHelper.JP_07,
                DBHelper.JP_08,
                DBHelper.JP_09,
                DBHelper.JP_10,
                DBHelper.JP_11,
                DBHelper.JP_12};
        String where = DBHelper.HARI + " =?";
        String[] whereArgs = new String[]{hari + ""};
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, where,
                whereArgs, null, null, null);
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
        contentValue.put(DBHelper.JP_01, jp1);
        contentValue.put(DBHelper.JP_02, jp2);
        contentValue.put(DBHelper.JP_03, jp3);
        contentValue.put(DBHelper.JP_04, jp4);
        contentValue.put(DBHelper.JP_05, jp5);
        contentValue.put(DBHelper.JP_06, jp6);
        contentValue.put(DBHelper.JP_07, jp7);
        contentValue.put(DBHelper.JP_08, jp8);
        contentValue.put(DBHelper.JP_09, jp9);
        contentValue.put(DBHelper.JP_10, jp10);
        contentValue.put(DBHelper.JP_11, jp11);
        contentValue.put(DBHelper.JP_12, jp12);
        int i = database.update(DBHelper.TABLE_NAME, contentValue,
                DBHelper.HARI + " = " + hari, null);
        return i;
    }

    public void delete(String hari) {
        database.delete(DBHelper.TABLE_NAME, DBHelper.HARI + "=\"" + hari + "\"", null);
    }
}
