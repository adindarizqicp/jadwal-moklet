package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite.SQLController;

/**
 * Created by Adinda Rizqi on 10/28/2016.
 */

public class JadwalDB extends AppCompatActivity {
    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference myRef = fireDB.getReference("XIIRPL1");

    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> myListSenin;
    String[] myJ, myJ_C;
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
        try {
            Cursor cursor = dbController.getDB().rawQuery("select * from myJadwal_out where Hari = \"Senin001\" ", null);

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
        } catch (SQLException e) {
            System.out.println("OYEE " + e.toString());
            ArrayList<String> maplist = new ArrayList<String>();
            maplist.add(" - ");
            return maplist;
        }
    }


    public void getSenin() {
        myRef.child("Senin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //AMBIL DATA DARI FIREBASE------------
                MataPelajaran mataPelajaran = dataSnapshot.getValue(MataPelajaran.class);

                try { //HAPUS RECORD YG SDH ADA
                    dbController.delete("Senin001");
                } catch (SQLException e) {
                    System.out.println("BEH! " + e.toString());
                }

                //AMBIL DATA DARI FIREBASE (disimpan ke array)------------
                myJ = new String[]{
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
                };

                //KONVERSI DATA--------------------------
                myJ_C = new String[12];
                try {
                    for (int i = 0; i < myJ.length; i++) {
                        Query queryRef = myRef.orderByChild(myJ[i]);
                        final int finalI = i;
                        queryRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                                String myKelas = "XIIRPL";
                                myJ_C[finalI] = String.valueOf(value.get(myKelas));
                                if (myJ_C[finalI].isEmpty())
                                    myJ_C[finalI] = " - ";
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                } catch (SQLException e) {
                    System.out.println("BEH! " + e.toString());
                }

                //INSERT KE SQLITE---------------------------
                try {
                    dbController.insert(
                            "Senin001",
                            myJ_C[0],
                            myJ_C[1],
                            myJ_C[2],
                            myJ_C[3],
                            myJ_C[4],
                            myJ_C[5],
                            myJ_C[6],
                            myJ_C[7],
                            myJ_C[8],
                            myJ_C[9],
                            myJ_C[10],
                            myJ_C[11]
                    );

                    message("Info", "Berhasil memperbarui jadwal");

                } catch (SQLException e) {
                    System.out.println("BEH! " + e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                message("Alert", "Gagal melakukan koneksi");
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
                message("Alert", "Gagal melakukan koneksi");
            }
        });
    }

    public void message(String jdl, String msg) {
        /*AlertDialog alertDialog = new AlertDialog.Builder().create();
        alertDialog.setTitle(jdl);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/
    }


}
