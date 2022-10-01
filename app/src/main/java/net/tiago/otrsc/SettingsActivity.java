package net.tiago.otrsc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private final ArrayList<String> metricSpinner = new ArrayList<>();

    private Spinner spinCalcUnit;

    private SharedPreferences metricCalc;
    private SharedPreferences spThemeEngine;

    private TextView textCalcUnitDesc;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        spThemeEngine = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);
        if (spThemeEngine.getString("theme", "").equals("")) {
            int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                setTheme(R.style.ThemeDark);
            } else {
                setTheme(R.style.ThemeLight);
            }
        }
        setContentView(R.layout.settings);
        initialize();
        initializeLogic();
    }

    private void initialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        LinearLayout metricContainer1 = findViewById(R.id.metricContainer1);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(_v -> onBackPressed());
        spinCalcUnit = findViewById(R.id.spinCalcUnit);
        textCalcUnitDesc = findViewById(R.id.textCalcUnitDesc);
        metricCalc = getSharedPreferences("metricCalc", Activity.MODE_PRIVATE);
        spThemeEngine = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);

        metricContainer1.setOnClickListener(v -> findViewById(R.id.spinCalcUnit).performClick());

        spinCalcUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                if (_param3 == 0) {
                    metricCalc.edit().putString("metric", "").apply();
                    spinCalcUnit.setSelection(0);
                    textCalcUnitDesc.setText(R.string.settings_metric_1);
                }
                if (_param3 == 1) {
                    metricCalc.edit().putString("metric", "1").apply();
                    spinCalcUnit.setSelection(1);
                    textCalcUnitDesc.setText(R.string.settings_metric_2);
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
        spinCalcUnit.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, metricSpinner));
        ((BaseAdapter) spinCalcUnit.getAdapter()).notifyDataSetChanged();
        if (metricCalc.getString("metric", "").equals("")) {
            spinCalcUnit.setSelection(0);
            textCalcUnitDesc.setText(R.string.settings_metric_1);
        } else {
            if (metricCalc.getString("metric", "").equals("1")) {
                spinCalcUnit.setSelection(1);
                textCalcUnitDesc.setText(R.string.settings_metric_2);
            }
        }
    }

}
