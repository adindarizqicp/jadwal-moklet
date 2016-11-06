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
    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference myRef = fireDB.getReference("XIIRPL1");
    DatabaseReference myRef_G = fireDB.getReference("Guru");

    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> myListSenin;
    Context myContext;
    String[] myJ = new String[12];
    String[] myJ_C = new String[12];
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

            /*Cursor cursor = dbController.fetch("Senin001");
            if(cursor != null){
            ArrayList<String> myList = new ArrayList<String>();
            //Log.d("&&_out", "DBSeninToArray: " + cursor.getColumnCount());
                Log.d("&&_if", "DBSeninToArray: " + cursor.getColumnCount());
                    Log.d("&&_bef_for", "DBSeninToArray: " + cursor.getColumnCount());
                    for (int i = 1; i < cursor.getColumnCount(); i++){
                        Log.d("&&_for", "DBSeninToArray: " + cursor.getString(i));
                        myList.add(cursor.getString(i));
                    }
            }*/

        cursor.close();
        myList.add("muncul lah nak");
        //maplist.add("Loo");
            return maplist;
    }

    public void getSenin() {
        Log.d("&&", "GET SENIN");
        myRef.child("Senin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //AMBIL DATA DARI FIREBASE------------
                //DataSnapshot mapelSnapshot = dataSnapshot.child("Senin");
                Log.d("&&", "GET SENIN 2");
                try { //HAPUS RECORD YG SDH ADA
                    dbController.delete("Senin001");
                } catch (SQLException e) {
                    System.out.println("&& error " + e.toString());
                }

                //for (DataSnapshot myMapelSnapshot: mapelSnapshot.getChildren()) {
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
                //}

                Log.d("&&", "myJ.length: " + myJ.length);

                //AMBIL DATA DARI FIREBASE (disimpan ke array)------------
                for (int i = 0; i < myJ.length; i++) {
                    if (myJ[i] == null) myJ[i] = " - ";
                    Log.d("&&", "onDataChange: " + i + " ~ " + myJ[i]);
                    //convert();
                }
                convert();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                message("Alert", "Gagal melakukan koneksi");
            }

        });


                //INSERT KE SQLITE---------------------------
                try {
                    /*dbController.insert(
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
                    );*/

                    message("Info", "Berhasil memperbarui jadwal");

                } catch (Exception e) {
                    System.out.println("BEH! " + e.toString());
                }
        Log.d("&&", "GET SENIN_END");
        /*final String myKelas = "XIIRPL";
        for(int i=0; i<myJ.length; i++){
            try {
                final int finalI = i;
                myRef_G.child(myJ[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        MataPelajaran_C mataPelajaran_c = dataSnapshot.getValue(MataPelajaran_C.class);
                        Log.d("&&", "onDataChange class nya : " + mataPelajaran_c);
                        myJ_C[finalI] = mataPelajaran_c.getJadwal_C(myKelas);
                        //myJ_C[i] = dataSnapshot.child("Guru/" + con + "/" + myKelas).getValue().toString();
                        if(myJ_C[finalI] == null) myJ_C[finalI] = " - ";
                        Log.d("&&", "onDataChange myJ_C : " + finalI + " ~ " + myJ_C[finalI]);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d("&&", "getSenin myJ_C OUT : " + finalI + " ~ " + myJ_C[finalI]);
            } catch (Exception e) {
                System.out.println("BEH! " + e.toString());
            }}*/
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
                        myJ_C[0] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 1: " + myJ_C[0]);
                    } else {
                        myJ_C[0] = "Data tidak ditemukan";
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
                        myJ_C[1] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 2: " + myJ_C[1]);
                    } else {
                        myJ_C[1] = "Data tidak ditemukan";
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
                        myJ_C[2] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 3: " + myJ_C[2]);
                    } else {
                        myJ_C[2] = "Data tidak ditemukan";
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
                        myJ_C[3] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 4: " + myJ_C[3]);
                    } else {
                        myJ_C[3] = "Data tidak ditemukan";
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
                        myJ_C[4] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 5: " + myJ_C[4]);
                    } else {
                        myJ_C[4] = "Data tidak ditemukan";
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
                        myJ_C[5] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 6: " + myJ_C[5]);
                    } else {
                        myJ_C[5] = "Data tidak ditemukan";
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
                        myJ_C[6] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 7: " + myJ_C[6]);
                    } else {
                        myJ_C[6] = "Data tidak ditemukan";
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
                        myJ_C[7] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 8: " + myJ_C[7]);
                    } else {
                        myJ_C[7] = "Data tidak ditemukan";
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
                        myJ_C[8] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 9: " + myJ_C[8]);
                    } else {
                        myJ_C[8] = "Data tidak ditemukan";
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
                        myJ_C[9] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 10: " + myJ_C[9]);
                    } else {
                        myJ_C[9] = "Data tidak ditemukan";
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
                        myJ_C[10] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 11: " + myJ_C[10]);
                    } else {
                        myJ_C[10] = "Data tidak ditemukan";
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
                        myJ_C[11] = mataPelajaran_c.getJadwal_C("XIIRPL");
                        Log.d("&&_gendeng", "onDataChange myJ_C 12: " + myJ_C[11]);
                    } else {
                        myJ_C[11] = "Data tidak ditemukan";
                        Log.d("&&_gendeng", "onDataChange myJ_C 12: " + myJ_C[11]);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
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
