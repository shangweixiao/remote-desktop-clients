package com.iiordanov.bVNC;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.Arrays;

/**
 * Created by iordan on 24/06/18.
 */

class PermissionsManager {
    public static String TAG = "PermissionsManager";
    private static String[] retrievePermissions(Context context) {
        try {
            String [] requestedPermissions = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
            android.util.Log.e(TAG, Arrays.toString(requestedPermissions));
            return requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("This should have never happened.", e);
        }
    }

    void requestPermissions(Activity activity) {
        String[] permissions = retrievePermissions(activity);
        for (String permission: permissions) {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(activity, permissions,0);
            }
        }
    }

}
