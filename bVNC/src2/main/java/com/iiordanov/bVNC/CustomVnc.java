package com.iiordanov.bVNC;

import android.os.Bundle;
import android.view.View;
import com.iiordanov.bVNC.*;
import com.iiordanov.freebVNC.*;
import com.iiordanov.aRDP.*;
import com.iiordanov.freeaRDP.*;
import com.iiordanov.aSPICE.*;
import com.iiordanov.CustomClientPackage.*;
import com.iiordanov.util.CustomClientConfigFileReader;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class CustomVnc extends bVNC {
    private final static String TAG = "CustomVnc";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        try {
            View view;
            CustomClientConfigFileReader configFileReader = new CustomClientConfigFileReader(
                            getAssets().open("custom_vnc_client.yaml"));
            Map<String, Map> configData = configFileReader.getConfigData();
            Map<String, Integer> visibility = (Map<String, Integer>)configData.get("mainConfiguration").get("visibility");

            for (String s : visibility.keySet()){
                android.util.Log.d(TAG, s);
                int resID = getResources().getIdentifier(s, "id", getPackageName());
                view = findViewById(resID);
                view.setVisibility(visibility.get(s));
            }

            String title = (String)configData.get("mainConfiguration").get("title");
            setTitle(title);

        } catch (IOException e) {
            android.util.Log.e(TAG, "Error opening config file from assets.");
            e.printStackTrace();
        }
    }
}
