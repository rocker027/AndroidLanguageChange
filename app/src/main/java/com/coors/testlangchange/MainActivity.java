package com.coors.testlangchange;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String LANG_CURRENT = "en";

    private Button btnCn;
    private Button btnTw;
    private Button btnEn;
    private TextView textView;
    private DisplayMetrics dm;
    private Configuration config;
    private Resources resources;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();

    }



    private void findViews() {
        btnCn = findViewById(R.id.btn_cn);
        btnTw = findViewById(R.id.btn_tw);
        btnEn = findViewById(R.id.btn_en);
        btnCn.setOnClickListener(this);
        btnTw.setOnClickListener(this);
        btnEn.setOnClickListener(this);
        textView = findViewById(R.id.tv_text);

//        callRestart();
//        config.locale = Locale.ENGLISH;
//        resources.updateConfiguration(config,dm);

    }

    @Override
    public void onClick(View v) {
        resources = getResources();
        dm = resources.getDisplayMetrics();
        config = resources.getConfiguration();

        switch (v.getId()) {
            case R.id.btn_cn:
                clickBtn("cn");
                break;
            case R.id.btn_tw:
                clickBtn("tw");
                break;
            case R.id.btn_en:
                clickBtn("en");
                break;
        }
    }

    private void clickBtn(String lang) {
        String localeCode = null;
        switch (lang) {
            case "cn":
                localeCode = "cn";
                break;
            case "tw":
                localeCode = "tw";
                break;
            case "en":
                localeCode = "en";
                break;
        }


        editor.putString("userSetLang", localeCode).apply();
        finish();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        editor = PreferenceManager.getDefaultSharedPreferences(newBase).edit();
//        preferences = getSharedPreferences("test", MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("userSetLang", "en");

        super.attachBaseContext(MyContextWrapper.wrap(newBase, LANG_CURRENT));
    }

}
