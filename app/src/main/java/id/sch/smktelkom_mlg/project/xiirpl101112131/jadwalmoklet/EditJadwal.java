package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


/**
 * Created by Distiara on 10/31/2016.
 */

public class EditJadwal extends AppCompatActivity {

    Menu Msave;
    Menu Mcancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Msave = (Menu) findViewById(R.id.action_save);
        Mcancel = (Menu) findViewById(R.id.action_cancel);

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
            Intent intent = new Intent(EditJadwal.this, ListJadwal.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            Intent intent = new Intent(EditJadwal.this, ListJadwal.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
