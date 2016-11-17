package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.content.Intent;
import android.os.AsyncTask;
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

        new asyncTask().execute();
    }

    private class asyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            new Thread(new Runnable() {
                int time = 1500;

                @Override
                public void run() {
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jadwal.getJadwalPelajaran("Senin");
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jadwal.getJadwalPelajaran("Selasa");
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jadwal.getJadwalPelajaran("Rabu");
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jadwal.getJadwalPelajaran("Kamis");
                        }
                    });
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jadwal.getJadwalPelajaran("Jumat");
                        }
                    });
                }
            }).start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            myProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }
}
