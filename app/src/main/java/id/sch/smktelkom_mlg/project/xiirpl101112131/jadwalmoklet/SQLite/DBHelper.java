package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adinda Rizqi on 11/4/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "myJadwal_out";

    // Table column
    //public static final String myID = "myID";
    public static final String HARI = "Hari";
    public static final String JP_01 = "JP_01";
    public static final String JP_02 = "JP_02";
    public static final String JP_03 = "JP_03";
    public static final String JP_04 = "JP_04";
    public static final String JP_05 = "JP_05";
    public static final String JP_06 = "JP_06";
    public static final String JP_07 = "JP_07";
    public static final String JP_08 = "JP_08";
    public static final String JP_09 = "JP_09";
    public static final String JP_10 = "JP_10";
    public static final String JP_11 = "JP_11";
    public static final String JP_12 = "JP_12";

    // Database Information
    static final String DB_NAME = "JadwalMoklet.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "(" +
            //myID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HARI + " TEXT," +
            JP_01 + " VARCHAR," +
            JP_02 + " VARCHAR," +
            JP_03 + " VARCHAR," +
            JP_04 + " VARCHAR," +
            JP_05 + " VARCHAR," +
            JP_06 + " VARCHAR," +
            JP_07 + " VARCHAR," +
            JP_08 + " VARCHAR," +
            JP_09 + " VARCHAR," +
            JP_10 + " VARCHAR," +
            JP_11 + " VARCHAR," +
            JP_12 + " VARCHAR);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
