package org.otrsc.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VehiclesFragmentActivity extends Fragment {


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        View _view = _inflater.inflate(R.layout.vehicles_fragment, _container, false);
        initialize();
        initializeLogic();
        return _view;
    }

    private void initialize() {
    }

    private void initializeLogic() {
    }

}
