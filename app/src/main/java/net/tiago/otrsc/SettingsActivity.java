package net.tiago.otrsc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private final ArrayList<String> metricSpinner = new ArrayList<>();

    private Spinner spinner1;

    private SharedPreferences metricCalc;
    private SharedPreferences themeEng;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        themeEng = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);
        if (themeEng.getString("theme", "").equals("")) {
            int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                setTheme(R.style.ThemeDark);
            } else {
                setTheme(R.style.ThemeLight);
            }
        }
        setContentView(R.layout.settings);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        AppBarLayout _app_bar = findViewById(R.id._app_bar);
        CoordinatorLayout _coordinator = findViewById(R.id._coordinator);
        Toolbar _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(_v -> onBackPressed());
        spinner1 = findViewById(R.id.spinner1);
        metricCalc = getSharedPreferences("metricCalc", Activity.MODE_PRIVATE);
        themeEng = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                if (_position == 0) {
                    metricCalc.edit().putString("metric", "").apply();
                    spinner1.setSelection((int) (0));
                }
                if (_position == 1) {
                    metricCalc.edit().putString("metric", "1").apply();
                    spinner1.setSelection((int) (1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> _param1) {

            }
        });
    }

    private void initializeLogic() {
        setTitle(R.string.ac_settings);
        metricSpinner.add("KPH");
        metricSpinner.add("MPH");
        spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, metricSpinner));
        ((BaseAdapter) spinner1.getAdapter()).notifyDataSetChanged();
        if (metricCalc.getString("metric", "").equals("")) {
            spinner1.setSelection((int) (0));
        } else {
            if (metricCalc.getString("metric", "").equals("1")) {
                spinner1.setSelection((int) (1));
            }
        }
    }

}
