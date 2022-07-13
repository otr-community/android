package com.android.otrsc;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout _app_bar;

    private ViewPager vpMain;
    private BottomNavigationView btMain;

    private FgMainFragmentAdapter fgMain;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        initializeLogic();
    }

    private void initialize() {
        _app_bar = findViewById(R.id._app_bar);
        Toolbar _toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(_v -> onBackPressed());
        vpMain = findViewById(R.id.vpMain);
        btMain = findViewById(R.id.btMain);
        fgMain = new FgMainFragmentAdapter(getApplicationContext(), getSupportFragmentManager());

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {
                btMain.getMenu().getItem(_position).setChecked(true);
                switch ((int) _position) {
                    case ((int) 0): {
                        Objects.requireNonNull(getSupportActionBar()).setTitle("Calculator");
                        _app_bar.setElevation(4);
                        break;
                    }
                    case ((int) 1): {
                        Objects.requireNonNull(getSupportActionBar()).setTitle("Vehicles");
                        _app_bar.setElevation(0);
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
            vpMain.setCurrentItem(_itemId);
            return true;
        });
    }

    private void initializeLogic() {
        _app_bar.setStateListAnimator(null);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        setTitle("Calculator");
        fgMain.setTabCount(2);
        vpMain.setAdapter(fgMain);
        btMain.getMenu().add(0, 0, 0, "Calculator").setIcon(R.drawable.ic_calculate);
        btMain.getMenu().add(0, 1, 0, "Speed List").setIcon(R.drawable.ic_car);
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
