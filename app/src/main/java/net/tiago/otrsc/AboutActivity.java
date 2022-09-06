package net.tiago.otrsc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        SharedPreferences themeEng = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);
        if (themeEng.getString("theme", "").equals("")) {
            int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
                setTheme(R.style.ThemeDark);
            } else {
                setTheme(R.style.ThemeLight);
            }
        }
        setContentView(R.layout.about);
        initialize();
        initializeLogic();
    }

    private void initialize() {
        AppBarLayout _app_bar = findViewById(R.id._app_bar);
        CoordinatorLayout _coordinator = findViewById(R.id._coordinator);
        Toolbar _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(_v -> onBackPressed());
    }

    private void initializeLogic() {
        setTitle(R.string.ac_about);
    }

}
