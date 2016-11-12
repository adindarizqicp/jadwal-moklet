package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

public class InputActivity extends AppCompatActivity {

    public static final String mypreference = "mypref";
    public static final String Kelas = "kelas";
    SharedPreferences sharedpreferences;
    Spinner kelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        kelas = (Spinner) findViewById(R.id.spinnerKelas);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Kelas)) {
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }

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