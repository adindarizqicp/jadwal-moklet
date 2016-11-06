package id.sch.smktelkom_mlg.project.xiirpl101112131.jadwalmoklet.SQLite;

/**
 * Created by Adinda Rizqi on 11/5/2016.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by denny on 16/05/2016.
 * Source: http://stackoverflow.com/questions/18322401/is-it-posible-backup-and-restore-a-database-file-in-android-non-root-devices
 */
public class BackupAndRestore {
    public static void importDB(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                File backupDB = context.getDatabasePath(DBhelper.DB_NAME);
                String backupDBPath = String.format("%s.bak", DBhelper.DB_NAME);
                File currentDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                //MyApplication.toastSomething(context, "Import Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportDB(Context context) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            Log.d("MY_BACKUP", "sd: " + sd.getPath() + " data: " + data.getPath());

            if (sd.canWrite()) {
                String backupDBPath = String.format("%s.bak", DBhelper.DB_NAME);
                File currentDB = context.getDatabasePath(DBhelper.DB_NAME);
                File backupDB = new File(sd, backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                //MyApplication.toastSomething(context, "Backup Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
