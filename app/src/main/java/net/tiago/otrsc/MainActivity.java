package net.tiago.otrsc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Intent mainIntent = new Intent();
    private Toolbar toolbar;
    private ViewPager vpMain;
    private BottomNavigationView bnMain;
    private FgMainFragmentAdapter fgMain;
    private AlertDialog.Builder dgUp;
    private SharedPreferences spDgUpShow;
    private SharedPreferences spThemeEngine;

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
        setContentView(R.layout.main);
        initialize();
        initializeLogic();
    }

    private void initialize() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(_v -> onBackPressed());
        vpMain = findViewById(R.id.vpMain);
        bnMain = findViewById(R.id.bnMain);
        fgMain = new FgMainFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        dgUp = new AlertDialog.Builder(this);
        spDgUpShow = getSharedPreferences("showUp", Activity.MODE_PRIVATE);
        spThemeEngine = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {
                bnMain.getMenu().getItem(_position).setChecked(true);
                switch (_position) {
                    case ((int) 0): {
                        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.fragment_calc);
                        break;
                    }
                    case ((int) 1): {
                        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.fragment_vehicles);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int _scrollState) {

            }
        });

        bnMain.setOnNavigationItemSelectedListener(item -> {
            final int _itemId = item.getItemId();
            vpMain.setCurrentItem(_itemId);
            return true;
        });
    }

    private void initializeLogic() {
        toolbar.setElevation((float) 4);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        fgMain.setTabCount(2);
        vpMain.setAdapter(fgMain);
        bnMain.getMenu().add(0, 0, 0, R.string.nav_calc).setIcon(R.drawable.calc);
        bnMain.getMenu().add(0, 1, 0, R.string.nav_vehicles).setIcon(R.drawable.car);
        if (Objects.requireNonNull(spDgUpShow.getString("change", "")).equals("") || Objects.requireNonNull(spDgUpShow.getString("change", "")).equals("v1.1")) {
            dgUp.setTitle("Release notes for version 1.2");
            dgUp.setMessage("Fixed OptionsMenu and AppBarLayout styles when on dark mode\nFixed gravity issues on about.xml and settings.xml\nFixed AppTheme not being applied on startup\nImproved light and dark mode colors\nChanged the app icon to suite the new theme\nMade view IDs more cohesive\nOther improvements related to the source code and performance");
            dgUp.setPositiveButton(R.string.diag_positive, (_dialog, _which) -> spDgUpShow.edit().putString("change", "v1.2").commit());
            dgUp.setNegativeButton(R.string.diag_negative, (_dialog, _which) -> {
            });
            dgUp.setCancelable(false);
            dgUp.create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.menu_settings);
        menu.add(0, 1, 0, R.string.menu_about);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int _id = item.getItemId();
        if (_id == 0) {
            mainIntent.setClass(getApplicationContext(), SettingsActivity.class);
            startActivity(mainIntent);
        }
        if (_id == 1) {
            mainIntent.setClass(getApplicationContext(), AboutActivity.class);
            startActivity(mainIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public static class FgMainFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        public FgMainFragmentAdapter(Context context, FragmentManager manager) {
            super(manager);
            this.context = context;
        }

        public void setTabCount(int tabCount) {
            this.tabCount = tabCount;
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        @Override
        public CharSequence getPageTitle(int _position) {

            return null;
        }

        @NonNull
        @Override
        public Fragment getItem(int _position) {
            if (_position == 0) {
                return new CalculatorFragmentActivity();
            }
            if (_position == 1) {
                return new VehiclesFragmentActivity();
            }
            return null;
        }
    }

}
