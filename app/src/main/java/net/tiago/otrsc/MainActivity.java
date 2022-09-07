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
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Intent i = new Intent();
    private Toolbar _toolbar;
    private ViewPager vpMain;
    private BottomNavigationView btMain;
    private FgMainFragmentAdapter fgMain;
    private AlertDialog.Builder upDate;
    private SharedPreferences showUp;
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
        setContentView(R.layout.main);
        initialize();
        initializeLogic();
    }

    private void initialize() {
        AppBarLayout _app_bar = findViewById(R.id._app_bar);
        CoordinatorLayout _coordinator = findViewById(R.id._coordinator);
        _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(_v -> onBackPressed());
        vpMain = findViewById(R.id.vpMain);
        btMain = findViewById(R.id.btMain);
        fgMain = new FgMainFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        upDate = new AlertDialog.Builder(this);
        showUp = getSharedPreferences("showUp", Activity.MODE_PRIVATE);
        themeEng = getSharedPreferences("themeEng", Activity.MODE_PRIVATE);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {
                btMain.getMenu().getItem(_position).setChecked(true);
                switch ((int) _position) {
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

        btMain.setOnNavigationItemSelectedListener(item -> {
            final int _itemId = item.getItemId();
            vpMain.setCurrentItem((int) _itemId);
            return true;
        });
    }

    private void initializeLogic() {
        _toolbar.setElevation((float) 4);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        fgMain.setTabCount(2);
        vpMain.setAdapter(fgMain);
        btMain.getMenu().add(0, 0, 0, R.string.nav_calc).setIcon(R.drawable.calc);
        btMain.getMenu().add(0, 1, 0, R.string.nav_vehicles).setIcon(R.drawable.car);
        if (Objects.requireNonNull(showUp.getString("change", "")).equals("") || Objects.requireNonNull(showUp.getString("change", "")).equals("v1.1")) {
            upDate.setTitle("Release notes for version 1.2");
            upDate.setMessage("Fixed about layout gravity\nFixed ThemeLight not being applied in AndroidManifest\nFixed CALCULATE button color in dark mode\nFixed settings layout weird padding\nImproved dark mode colors");
            upDate.setPositiveButton(R.string.diag_positive, (_dialog, _which) -> showUp.edit().putString("change", "v1.2").commit());
            upDate.setNegativeButton(R.string.diag_negative, (_dialog, _which) -> {
            });
            upDate.setCancelable(false);
            upDate.create().show();
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
        final String _title = (String) item.getTitle();
        if (_id == 0) {
            i.setClass(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        }
        if (_id == 1) {
            i.setClass(getApplicationContext(), AboutActivity.class);
            startActivity(i);
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
