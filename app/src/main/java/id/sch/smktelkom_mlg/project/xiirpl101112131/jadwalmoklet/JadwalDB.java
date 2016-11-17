package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite.SQLController;

/**
 * Created by Adinda Rizqi on 10/28/2016.
 */

public class JadwalDB extends AppCompatActivity {
    String kelas, kelass;
    FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    DatabaseReference myRef_G;

    SharedPreferences sharedpreferences;


    ArrayList<String> myList = new ArrayList<String>();
    ArrayList<String> myListSenin;
    Context myContext;
    String[] myJ = new String[12];
    String[] myJ_C = new String[12];
    String[] myJ_G = new String[12];
    String notFound = "-";
    ProgressDialog progressDialog;
    private SQLController dbController;

    public JadwalDB(Context c) {
        MainActivity m = new MainActivity();
        dbController = new SQLController(c);
        dbController.open();
        Log.d("SQL BEH", "open: " + dbController.toString());

        /*sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        this.kelas = (sharedpreferences.getString(Kelas, ""));*/
        this.kelas = "XIIRPL";
        this.kelass = "XIIRPL1";
        myRef = fireDB.getReference(kelass);
        myRef_G = fireDB.getReference("Guru");

        myContext = c;
    }

    public void updateDB() {
        progressDialog = new ProgressDialog(myContext);
        progressDialog.setMessage("Updating data");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgress(0);
        progressDialog.show();

        new Thread(new Runnable() {
            int time = 1500;

            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getJadwalPelajaran("Senin");
                    }
                });
                progressDialog.setProgress(20);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getJadwalPelajaran("Selasa");
                    }
                });
                progressDialog.setProgress(40);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getJadwalPelajaran("Rabu");
                    }
                });
                progressDialog.setProgress(60);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getJadwalPelajaran("Kamis");
                    }
                });
                progressDialog.setProgress(80);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getJadwalPelajaran("Jumat");
                    }
                });
                progressDialog.setProgress(100);
                progressDialog.dismiss();
            }
        }).start();
    }

    public String[] getArray(String hari) {
        Cursor cursor = dbController.getDB().rawQuery("select * from myJadwal_out where Hari = \"" + hari + "\" ", null);

        String[] maplist = new String[cursor.getColumnCount()];  // looping through all rows and adding to list

        try {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        for (int i = 1; i < cursor.getColumnCount(); i++) {
                            maplist[i - 1] = cursor.getString(i);
                        }
                    } while (cursor.moveToNext());
                    }
            }
        } catch (Error e) {

            }

        cursor.close();
            return maplist;
    }

    public void getJadwalPelajaran(String fHari) {
        final String hari = fHari;
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
                input(hari + "000", myJ);
                convert(hari, myJ);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                message("Alert", "Gagal melakukan koneksi");
            }

        });
        Log.d("&&", "GET SENIN_END");
    }

    public void convert(String fHari, String[] myJ2) {
        final String hari = fHari;
        //KONVERSI DATA--------------------------
        if (myJ2[0] != null) {
            myRef_G.child(myJ2[0].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[1] != null) {
            myRef_G.child(myJ2[1].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[2] != null) {
            myRef_G.child(myJ2[2].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[3] != null) {
            myRef_G.child(myJ2[3].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[4] != null) {
            myRef_G.child(myJ2[4].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[5] != null) {
            myRef_G.child(myJ2[5].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[6] != null) {
            myRef_G.child(myJ2[6].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[7] != null) {
            myRef_G.child(myJ2[7].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[8] != null) {
            myRef_G.child(myJ2[8].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[9] != null) {
            myRef_G.child(myJ2[9].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[10] != null) {
            myRef_G.child(myJ2[10].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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

        if (myJ2[11] != null) {
            myRef_G.child(myJ2[11].toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                    input(hari + "001", myJ_C);
                    input(hari + "002", myJ_G);
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

    public boolean internetConnectionAvailable(int timeOut) {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
        }
        return inetAddress != null && !inetAddress.equals("");
    }

}

