package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Distiara on 10/31/2016.
 */

public class EditJadwal extends AppCompatActivity {

    public int jumlah = 12;
    Menu Msave;
    Menu Mcancel;
    LinearLayout llEdit;
    String hari;
    int intHari;
    JadwalDB jadwalDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intHari = extras.getInt("intHari");
        }
        if (intHari == 1) hari = "Senin";
        else if (intHari == 2) hari = "Selasa";
        else if (intHari == 3) hari = "Rabu";
        else if (intHari == 4) hari = "Kamis";
        else if (intHari == 5) hari = "Jumat";

        setTitle("Edit " + hari);

        Msave = (Menu) findViewById(R.id.action_save);
        Mcancel = (Menu) findViewById(R.id.action_cancel);

        jadwalDB = new JadwalDB(this);

        llEdit = (LinearLayout) findViewById(R.id.LinearLayoutEditJadwal);

        llEdit.removeAllViews();
        for (int i = 1; i <= jumlah; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.list_edit_jadwal, llEdit, false);
            v.setTag("Jam" + i);
            llEdit.addView(v);
        }

        String[] kode = jadwalDB.getArray(hari + "000");
        String[] mapel = jadwalDB.getArray(hari + "001");
        String[] guru = jadwalDB.getArray(hari + "002");
        for (int i = 1; i <= jumlah; i++) {
            LinearLayout llNow = (LinearLayout) llEdit.findViewWithTag("Jam" + i);
            LinearLayout ll = (LinearLayout) llNow.findViewById(R.id.LinearLayoutListEditJadwal);
            TextView tvNo = (TextView) llNow.findViewById(R.id.E_no);
            EditText etKode = (EditText) llNow.findViewById(R.id.E_kode);
            EditText etMapel = (EditText) llNow.findViewById(R.id.E_mapel);
            EditText etGuru = (EditText) llNow.findViewById(R.id.E_guru);
            tvNo.setText("" + i);
            etKode.setText(kode[i - 1]);
            etMapel.setText(mapel[i - 1]);
            etGuru.setText(guru[i - 1]);
            if (i % 2 == 0) {
                ll.setBackgroundColor(Color.WHITE);
            } else {
                ll.setBackgroundColor(Color.parseColor("#F7F7F7"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_cancel) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            Intent intent = new Intent(EditJadwal.this, MainActivity.class);
                            //intent.putExtra("mPage", intHari);
                            startActivity(intent);
                            finish();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to cancel this action?").setNegativeButton("Yes", dialogClickListener)
                    .setPositiveButton("No", dialogClickListener).show();

            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:

                            updateDB(hari);
                            Intent intent = new Intent(EditJadwal.this, MainActivity.class);
                            //intent.putExtra("mPage", intHari)
                            startActivity(intent);
                            finish();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure want to save this action?").setNegativeButton("Yes", dialogClickListener)
                    .setPositiveButton("No", dialogClickListener).show();

            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void updateDB(String hari) {
        String[] arrKode = new String[jumlah];
        String[] arrMapel = new String[jumlah];
        String[] arrGuru = new String[jumlah];
        for (int i = 1; i <= jumlah; i++) {
            LinearLayout llNow = (LinearLayout) llEdit.findViewWithTag("Jam" + i);
            LinearLayout ll = (LinearLayout) llNow.findViewById(R.id.LinearLayoutListEditJadwal);
            TextView tvNo = (TextView) llNow.findViewById(R.id.E_no);
            EditText etKode = (EditText) llNow.findViewById(R.id.E_kode);
            EditText etMapel = (EditText) llNow.findViewById(R.id.E_mapel);
            EditText etGuru = (EditText) llNow.findViewById(R.id.E_guru);
            if (etKode.getText().toString() == null) {
                arrKode[i - 1] = "-";
            } else {
                arrKode[i - 1] = etKode.getText().toString();
            }
            if (etMapel.getText().toString() == null) {
                arrMapel[i - 1] = "-";
            } else {
                arrMapel[i - 1] = etMapel.getText().toString();
            }
            if (etGuru.getText().toString() == null) {

            }
            arrGuru[i - 1] = etGuru.getText().toString();
        }
        jadwalDB.input(hari + "000", arrKode);
        jadwalDB.input(hari + "001", arrMapel);
        jadwalDB.input(hari + "002", arrGuru);
    }


}
