package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dell on 05/11/2016.
 */

public class activity_jadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        findViewById(R.id.imageButtonSiswa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHasil();
            }
        });
    }

    private void goHasil() {
        Intent intent = new Intent(activity_jadwal.this, MainActivity.class);
        startActivity(intent);
    }
}