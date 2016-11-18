package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.adapter.JurusanAngkaAdapter;

public class InputActivity extends AppCompatActivity {

    public static final String mypreference = "mypref";
    public static final String Kelas = "kelasKey";
    public static final String Name = "nameKey";
    public static final String JurusanAngka = "jurusanAngkaKey";
    Spinner spKelas, spJurusanAngka;
    TextView name;
    SharedPreferences.Editor editor;
    Button next;
    SharedPreferences sharedpreferences;
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

        next = (Button) findViewById(R.id.jadwal);

        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        spJurusanAngka = (Spinner) findViewById(R.id.spinnerJurusanAngka);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Options", MODE_PRIVATE);
        editor = pref.edit();

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String kelas = spKelas.getSelectedItem().toString();
                String jurang = spJurusanAngka.getSelectedItem().toString();
                editor.putString("Kelas", kelas);
                editor.putString("Jurang", jurang);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                Intent in = new Intent(InputActivity.this, MainActivity.class);
                in.putExtra("updateFirst", true);
                startActivity(in);
                finish();
            }
        });

    }

    public void Save(View view) {
        String n = spKelas.getSelectedItem().toString();
        String e = spJurusanAngka.getSelectedItem().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Kelas, n);
        editor.putString(JurusanAngka, e);
        editor.commit();

        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void SavePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
//
//    public void clear(View view) {
//        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
//        spJurusanAngka = (Spinner) findViewById(R.id.spinnerJurusanAngka);
//        spKelas.setSelection(1);
//        spJurusanAngka.setSelection(1);
//
//    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String data = sharedPreferences.getString("name", "08:00");
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    public void Get(View view) {
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        spJurusanAngka = (Spinner) findViewById(R.id.spinnerJurusanAngka);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Kelas) || sharedpreferences.contains(JurusanAngka)) {
            setContentView(R.layout.activity_main);
            //name.setText(sharedpreferences.getString(String.valueOf(spKelas), ""));
        }

    }


}
