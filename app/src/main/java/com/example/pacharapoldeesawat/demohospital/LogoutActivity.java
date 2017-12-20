package com.example.pacharapoldeesawat.demohospital;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class LogoutActivity extends AppCompatActivity {

    private static LogoutActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        instance = this;


        AlertDialog.Builder builder = new AlertDialog.Builder(LogoutActivity.this)
                .setTitle("ออกจากระบบ")
                .setMessage("คคุณยืนยันที่จะออกจากระบบหรือไม่")
                .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences prefs = getSharedPreferences("label", 0);
                        prefs.edit().clear().commit();
                        clearApplicationData();
                        Intent intentApp = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        intentApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        SharedPreferences restartPerf = getSharedPreferences("label", 0);
                        SharedPreferences.Editor editor = restartPerf.edit();
                        Log.i("First time", String.valueOf(restartPerf.getBoolean("restart", true)));
                        editor.putBoolean("restart", true);
                        editor.commit();

                        startActivity(intentApp);
                    }
                })
                .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LogoutActivity.this, "ยกเลิก", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(LogoutActivity.this, InAppActivity.class);
                        startActivity(it);
                    }
                });
        builder.show();


    }

    public static LogoutActivity getInstance() {
        return instance;
    }

    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }
}
