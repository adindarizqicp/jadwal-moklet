package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite.SQLController;

/**
 * Created by Adinda Rizqi on 10/28/2016.
 */

public class JadwalDB extends AppCompatActivity {
    public String kelas = "XIIRPL";
    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference myRef = fireDB.getReference("XIIRPL1");
    DatabaseReference myRef_G = fireDB.getReference("Guru");

    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> myListSenin;
    Context myContext;
    String[] myJ = new String[12];
    String[] myJ_C = new String[12];
    String[] myJ_G = new String[12];
    String notFound = "-";
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

            ArrayList<String> maplist = new ArrayList<String>();  // looping through all rows and adding to list

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        for (int i = 1; i < cursor.getColumnCount(); i++) {
                            maplist.add(cursor.getString(i));
                        }
                    } while (cursor.moveToNext());
                    }
            }
        } catch (Error e) {

            }

        cursor.close();
        myList.add("muncul lah nak");
        //maplist.add("Loo");
            return maplist;
    }

    public void getJadwalPelajaran(String hari, final String record) {
        Log.d("&&", "GET SENIN");
        myRef.child(hari).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //AMBIL DATA DARI FIREBASE------------
                Log.d("&&", "GET SENIN 2");
                MataPelajaran mataPelajaran = dataSnapshot.getValue(MataPelajaran.class);
                myJ[0] = mataPelajaran.getJ1();
                myJ[1] = mataPelajaran.getJ2();
                myJ[2] = mataPelajaran.getJ3();
                myJ[3] = mataPelajaran.getJ4();
                myJ[4] = mataPelajaran.getJ5();
                myJ[5] = mataPelajaran.getJ6();
                myJ[6] = mataPelajaran.getJ7();
                myJ[7] = mataPelajaran.getJ8();
                myJ[8] = mataPelajaran.getJ9();
                myJ[9] = mataPelajaran.getJ10();
                myJ[10] = mataPelajaran.getJ11();
                myJ[11] = mataPelajaran.getJ12();

                Log.d("&&", "myJ.length: " + myJ.length);

                //AMBIL DATA DARI FIREBASE (disimpan ke array)------------
                for (int i = 0; i < myJ.length; i++) {
                    if (myJ[i] == null) myJ[i] = " - ";
                    Log.d("&&", "onDataChange: " + i + " ~ " + myJ[i]);
                    //convert();
                }
                input(record, myJ);
                convert();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                message("Alert", "Gagal melakukan koneksi");
            }

        });
        Log.d("&&", "GET SENIN_END");
    }

    public void convert() {
        //KONVERSI DATA--------------------------
        if (myJ[0] != null) {
            myRef_G.child(myJ[0].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[0] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[0] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 1: " + myJ_C[0]);
                        Log.d("&&_gendeng", "onDataChange myJ_C 1: " + myJ_G[0]);
                    } else {
                        myJ_C[0] = notFound;
                        myJ_G[0] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 1: " + myJ_C[0]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[1] != null) {
            myRef_G.child(myJ[1].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[1] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[1] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 2: " + myJ_C[1]);
                    } else {
                        myJ_C[1] = "Data tidak ditemukan";
                        myJ_G[1] = "Data tidak ditemukan";
                        Log.d("&&_gendeng", "onDataChange myJ_C 2: " + myJ_C[1]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[2] != null) {
            myRef_G.child(myJ[2].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[2] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[2] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 3: " + myJ_C[2]);
                    } else {
                        myJ_C[2] = notFound;
                        myJ_G[2] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 3: " + myJ_C[2]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[3] != null) {
            myRef_G.child(myJ[3].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[3] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[3] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 4: " + myJ_C[3]);
                    } else {
                        myJ_C[3] = notFound;
                        myJ_G[3] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 4: " + myJ_C[3]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[4] != null) {
            myRef_G.child(myJ[4].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[4] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[4] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 5: " + myJ_C[4]);
                    } else {
                        myJ_C[4] = notFound;
                        myJ_G[4] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 5: " + myJ_C[4]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[5] != null) {
            myRef_G.child(myJ[5].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[5] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[5] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 6: " + myJ_C[5]);
                    } else {
                        myJ_C[5] = notFound;
                        myJ_G[5] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 6: " + myJ_C[5]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[6] != null) {
            myRef_G.child(myJ[6].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[6] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[6] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 7: " + myJ_C[6]);
                    } else {
                        myJ_C[6] = notFound;
                        myJ_G[6] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 7: " + myJ_C[6]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[7] != null) {
            myRef_G.child(myJ[7].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[7] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[7] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 8: " + myJ_C[7]);
                    } else {
                        myJ_C[7] = notFound;
                        myJ_G[7] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 8: " + myJ_C[7]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[8] != null) {
            myRef_G.child(myJ[8].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[8] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[8] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 9: " + myJ_C[8]);
                    } else {
                        myJ_C[8] = notFound;
                        myJ_G[8] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 9: " + myJ_C[8]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[9] != null) {
            myRef_G.child(myJ[9].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[9] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[9] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 10: " + myJ_C[9]);
                    } else {
                        myJ_C[9] = notFound;
                        myJ_G[9] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 10: " + myJ_C[9]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[10] != null) {
            myRef_G.child(myJ[10].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[10] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[10] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 11: " + myJ_C[10]);
                    } else {
                        myJ_C[10] = notFound;
                        myJ_G[10] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 11: " + myJ_C[10]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (myJ[11] != null) {
            myRef_G.child(myJ[11].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&_gendeng", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[11] = mataPelajaran_c.getJadwal_C(kelas);
                        myJ_G[11] = mataPelajaran_c.getNama();
                        Log.d("&&_gendeng", "onDataChange myJ_C 12: " + myJ_C[11]);
                    } else {
                        myJ_C[11] = notFound;
                        myJ_G[11] = notFound;
                        Log.d("&&_gendeng", "onDataChange myJ_C 12: " + myJ_C[11]);
                    }
                    input("Senin001", myJ_C);
                    input("Senin002", myJ_G);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void input(String tbl, String[] col) {
        try { //HAPUS RECORD YG SDH ADA
            dbController.delete(tbl);
        } catch (SQLException e) {
            System.out.println("&& error " + e.toString());
        }
        //INSERT KE SQLITE---------------------------
        try {
            dbController.insert(
                    tbl,
                    col[0],
                    col[1],
                    col[2],
                    col[3],
                    col[4],
                    col[5],
                    col[6],
                    col[7],
                    col[8],
                    col[9],
                    col[10],
                    col[11]
            );

            message("Info", "Berhasil memperbarui jadwal");

        } catch (Exception e) {
            System.out.println("BEH! " + e.toString());
        }
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
