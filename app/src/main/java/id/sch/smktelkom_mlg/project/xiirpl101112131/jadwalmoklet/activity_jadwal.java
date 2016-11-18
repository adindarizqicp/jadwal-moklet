package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dell on 05/11/2016.
 */

public class activity_jadwal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal2);

        findViewById(R.id.imageButtonSiswa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHasil();
            }
        });
        findViewById(R.id.imageButtonGuru).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soon();
            }
        });
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