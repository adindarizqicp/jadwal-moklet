package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Bunga on 12/11/2016.
 */

public class Splash extends Activity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        pref = getSharedPreferences("Options", MODE_PRIVATE);
        final ImageView iv = (ImageView) findViewById(R.id.imageView7);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                String kelas, jurang;
                kelas = pref.getString("Kelas", "").toString();
                jurang = pref.getString("Jurang", "").toString();

                Toast.makeText(getApplicationContext(), kelas + " " + jurang, Toast.LENGTH_LONG).show();

                iv.startAnimation(an2);
                finish();
                if (!(kelas.isEmpty() && jurang.isEmpty())) {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(getBaseContext(), activity_jadwal.class);
                    startActivity(i);
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
