package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.adapter.JurusanAngkaAdapter;

public class InputActivity extends AppCompatActivity {

    public static final String mypreference = "mypref";
    public static final String Kelas = "kelas";
    SharedPreferences sharedpreferences;
    Spinner kelas;


    Spinner spKelas, spJurusanAngka;

    String[][] arJurusanAngka = {{"X RPL 1", "X RPL 2", "X RPL 3",
            "X RPL 4", "X RPL 5", "X RPL 6", "X TKJ 1", "X TKJ 2", "X TKJ 3",
            "X TKJ 4", "X TKJ 5", "X TKJ 6"},
            {"XI RPL 1", "XI RPL 2", "XI RPL 3", "XI RPL 4", "XI RPL 5", "XI RPL 6", "XI TKJ 1", "XI TKJ 2", "XI TKJ 3", "XI TKJ 4", "XI TKJ 5"},
            {"XII RPL 1", "XII RPL 2", "XII RPL 3", "XII RPL 4", "XII RPL 5", "XII TKJ 1", "XII TKJ 2", "XII TKJ 3", "XII TKJ 4"}};
    ArrayList<String> listJurusanAngka = new ArrayList<>();
    JurusanAngkaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        spJurusanAngka = (Spinner) findViewById(R.id.spinnerJurusanAngka);

        adapter = new JurusanAngkaAdapter(this, listJurusanAngka);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusanAngka.setAdapter(adapter);

        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listJurusanAngka.clear();
                listJurusanAngka.addAll(Arrays.asList(arJurusanAngka[pos]));
                adapter.setKelas((String) spKelas.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spJurusanAngka.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kelas = (Spinner) findViewById(R.id.spinnerKelas);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Kelas)) {
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }
        findViewById(R.id.textViewExit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metu();
            }
        });

    }

    private void metu() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        startActivity(new Intent(InputActivity.this, activity_jadwal.class));
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yakin keluar?").setNegativeButton("Yes", dialogClickListener)
                .setPositiveButton("No", dialogClickListener).show();
    }

    public void Save(View view) {
        String n = kelas.getSelectedItem().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(String.valueOf(kelas), n);
        editor.commit();

        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

}