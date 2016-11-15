package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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