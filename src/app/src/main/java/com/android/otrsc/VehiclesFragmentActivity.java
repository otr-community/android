package com.android.otrsc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class VehiclesFragmentActivity extends Fragment {

    private androidx.appcompat.widget.Toolbar toolbar;

    private TabLayout tabCategory;
    private ViewPager vpVehicles;

    private FaCatFragmentAdapter faCat;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        View _view = _inflater.inflate(R.layout.vehicles_fragment, _container, false);
        initialize(_savedInstanceState, _view);
        initializeLogic();
        return _view;
    }

    private void initialize(Bundle _savedInstanceState, View _view) {
        tabCategory = _view.findViewById(R.id.tabCategory);
        vpVehicles = _view.findViewById(R.id.vpVehicles);
        faCat = new FaCatFragmentAdapter(getContext().getApplicationContext(), getActivity().getSupportFragmentManager());
    }

    private void initializeLogic() {
        faCat.setTabCount(4);
        vpVehicles.setAdapter(faCat);
        tabCategory.setupWithViewPager(vpVehicles);
    }

    public void _fragmentFix() {
    }

    public class FaCatFragmentAdapter extends FragmentStatePagerAdapter {
        // This class is deprecated, you should migrate to ViewPager2:
        // https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
        Context context;
        int tabCount;

        public FaCatFragmentAdapter(Context context, FragmentManager manager) {
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
            if (_position == 0) {
                return "Trucks";
            }
            if (_position == 1) {
                return "4x4";
            }
            if (_position == 2) {
                return "Rally";
            }
            if (_position == 3) {
                return "Others";
            }
            return null;
        }

        @Override
        public Fragment getItem(int _position) {
            if (_position == 0) {
                return new TrucksFragmentActivity();
            }
            if (_position == 1) {
                return new FourxfourFragmentActivity();
            }
            if (_position == 2) {
                return new RallyFragmentActivity();
            }
            if (_position == 3) {
                return new OthersFragmentActivity();
            }
            return null;
        }
    }

}
