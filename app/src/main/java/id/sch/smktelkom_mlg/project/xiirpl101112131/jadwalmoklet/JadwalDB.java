package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Adinda Rizqi on 10/28/2016.
 */

public class JadwalDB extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("XIIRPL1");

    ArrayList<String> myList = new ArrayList<String>();

    public JadwalDB() {
        myList.add("harusnya sih muncul");
    }

    public ArrayList<String> getList() {

        return myList;
    }

    public void getSenin() {
        myRef.child("Senin").addValueEventListener(new ValueEventListener() {
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
                myList.add("muncul kah?");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                error();
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
                error();
            }
        });
    }

    public void error() {
        AlertDialog alertDialog = new AlertDialog.Builder(JadwalDB.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Gagal melakukan koneksi");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
