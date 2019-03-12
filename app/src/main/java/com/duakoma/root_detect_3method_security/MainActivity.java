package com.duakoma.root_detect_3method_security;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.scottyab.rootbeer.RootBeer;
import com.stericson.RootTools.RootTools;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RootBeer rootBeer = new RootBeer(this);
        isRooted();
        if (isRooted()){
            dialog();
        }
        else if (rootBeer.isRooted()) {
            dialog();
        }
        else if(RootTools.isRootAvailable()){
            dialog();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "NOT ROOT", Toast.LENGTH_SHORT); toast.show();
        }
    }

    private void dialog() {
        Toast toast = Toast.makeText(getApplicationContext(), "ROOT DEVICE", Toast.LENGTH_SHORT); toast.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("ROOT DEVICE!")
                .setCancelable(false)
                .setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        finish();
                        System.exit(0);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public static boolean findBinary(String binaryName) {
        boolean found = false;
        if (!found) {
            String[] places = { "/sbin/", "/system/bin/", "/system/xbin/",
                    "/data/local/xbin/", "/data/local/bin/",
                    "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/",
                    "/system/app/Superuser.apk", "/sbin/su", "/sbin/su/", "/system/bin/su","/system/bin/su/",
                    "/system/xbin/su", "/system/xbin/su/", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                    "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su", "/su/",
                    "/data/local/xbin/",
                    "/system/bin/.ext/",
                    "/system/bin/failsafe/",
                    "/system/sd/xbin/",
                    "/su/xbin/",
                    "/su/bin/",
                    "/magisk/.core/bin/",
                    "/system/usr/we-need-root/",
                    "/system/xbin/",
                    "/system/su","/system/bin/.ext/.su","/system/usr/we-need-root/su-backup",
                    "/system/xbin/mu",
                    "/system/su/","/system/bin/.ext/.su/","/system/usr/we-need-root/su-backup/",
                    "/system/xbin/mu/"};
            for (String where : places) {
                if (new File(where + binaryName).exists()) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
    private static boolean isRooted() {
        return findBinary("su");
    }

}
