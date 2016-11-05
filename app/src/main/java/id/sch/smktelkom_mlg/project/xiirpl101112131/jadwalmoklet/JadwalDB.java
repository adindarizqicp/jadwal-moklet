package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite.DBhelper;
import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite.SQLController;

/**
 * Created by Adinda Rizqi on 10/28/2016.
 */

public class JadwalDB extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("XIIRPL1");

    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> myListSenin;
    Context myContext;
    private SQLController dbController;

    public JadwalDB(Context c) {
        MainActivity m = new MainActivity();
        dbController = new SQLController(c);
        dbController.open();
        Log.d("SQL BEH", "open: " + dbController.toString());

        myContext = c;
    }

    public ArrayList DBSeninToArray() {
        Cursor cursor = dbController.getDB().rawQuery("select * from myJadwal_out where Hari = \"Senin001\" ", null);
        String[] from = new String[]{
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

        /*ArrayList<String> mArrayList = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                mArrayList.add(cursor.getString(cursor.getColumnIndex("Senin001"))); //add the item
            }while(cursor.moveToNext());
        }*/

        ArrayList<String> maplist = new ArrayList<String>();  // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                for (int i = 1; i < cursor.getColumnCount(); i++) {
                    maplist.add(cursor.getString(i));
                }
            } while (cursor.moveToNext());
        }

        cursor.close();

        return maplist;
    }


    public void getSenin() {
        myRef.child("Senin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MataPelajaran mataPelajaran = dataSnapshot.getValue(MataPelajaran.class);
                try {
                    dbController.delete("Senin001");
                } catch (SQLException e) {

                }


                try {
                    dbController.insert(
                            "Senin001",
                            mataPelajaran.getJ1(),
                            mataPelajaran.getJ2(),
                            mataPelajaran.getJ3(),
                            mataPelajaran.getJ4(),
                            mataPelajaran.getJ5(),
                            mataPelajaran.getJ6(),
                            mataPelajaran.getJ7(),
                            mataPelajaran.getJ8(),
                            mataPelajaran.getJ9(),
                            mataPelajaran.getJ10(),
                            mataPelajaran.getJ11(),
                            mataPelajaran.getJ12()
                    );
                } catch (SQLException e) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                error("Gagal melakukan koneksi");
            }
        });
    }

    public void getSelasa() {
        myRef.child("Selasa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Getting the data from snapshot
                MataPelajaran mataPelajaran = dataSnapshot.getValue(MataPelajaran.class);

                //Adding to list
                myList.clear();
                myList.add(mataPelajaran.getJ1());
                myList.add(mataPelajaran.getJ2());
                myList.add(mataPelajaran.getJ3());
                myList.add(mataPelajaran.getJ4());
                myList.add("hmm");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                error("Gagal melakukan koneksi");
            }
        });
    }

    public void error(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(JadwalDB.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
