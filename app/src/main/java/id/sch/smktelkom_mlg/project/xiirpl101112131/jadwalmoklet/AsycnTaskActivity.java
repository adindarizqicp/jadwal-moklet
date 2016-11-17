package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class AsycnTaskActivity extends AppCompatActivity {

    JadwalDB jadwal;
    ProgressBar myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asycn_task);

        myProgressBar = (ProgressBar) findViewById(R.id.progressBarAsyncTask);
        myProgressBar.setProgress(0);

        Intent i = getIntent();
        jadwal = new JadwalDB(this);

        new Thread(new Runnable() {
            int time = 1500;

            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgressBar.setProgress(10);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jadwal.getJadwalPelajaran("Senin");
                    }
                });
                myProgressBar.setProgress(20);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgressBar.setProgress(30);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jadwal.getJadwalPelajaran("Selasa");
                    }
                });
                myProgressBar.setProgress(40);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgressBar.setProgress(50);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jadwal.getJadwalPelajaran("Rabu");
                    }
                });
                myProgressBar.setProgress(60);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgressBar.setProgress(70);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jadwal.getJadwalPelajaran("Kamis");
                    }
                });
                myProgressBar.setProgress(80);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myProgressBar.setProgress(90);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jadwal.getJadwalPelajaran("Jumat");
                    }
                });
                myProgressBar.setProgress(100);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }).start();
    }

}
