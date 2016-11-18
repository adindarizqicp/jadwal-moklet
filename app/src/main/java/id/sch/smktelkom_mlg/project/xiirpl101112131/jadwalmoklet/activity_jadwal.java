package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.InputActivity.mypreference;

/**
 * Created by dell on 05/11/2016.
 */

public class activity_jadwal extends AppCompatActivity {

    Button show;
    SharedPreferences pref;
    TextView optiondisp;
    SharedPreferences.Editor editor;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal2);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        optiondisp = (TextView) findViewById(R.id.textView3);

        findViewById(R.id.imageButtonSiswa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(activity_jadwal.this, InputActivity.class);
                startActivity(in);
                finish();
                /*String kelas=pref.getString("Kelas", "");
                String jurang=pref.getString("Jurang", "");

                if (sharedpreferences.contains(kelas)|| sharedpreferences.contains(jurang))
                {
                    optiondisp.setText("Kelas:"+kelas+"\nJurang:"+jurang);
                    Intent in=new Intent(activity_jadwal.this,MainActivity.class);
                    startActivity(in);
                }

                else {
                    Intent in=new Intent(activity_jadwal.this,InputActivity.class);
                    startActivity(in);
                }
*/

            }
        });
        findViewById(R.id.imageButtonGuru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soon();
            }
        });

        pref = getApplication().getSharedPreferences("Options", MODE_PRIVATE);
    }

    private void soon() {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Coming soon").setNegativeButton("OK", dialogClickListener).show();
    }

    private void goHasil() {
        Intent intent = new Intent(activity_jadwal.this, InputActivity.class);
        startActivity(intent);
    }


}