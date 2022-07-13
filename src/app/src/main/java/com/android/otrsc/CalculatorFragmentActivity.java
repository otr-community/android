package com.android.otrsc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CalculatorFragmentActivity extends Fragment {

    private final double rTime = 0;
    private final double rRDistance = 0;
    private double rFDistance = 0;
    private double rSpeed = 0;

    private LinearLayout btMain;
    private TextView txtResult;
    private EditText etTime;
    private EditText etDistance;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        View _view = _inflater.inflate(R.layout.calculator_fragment, _container, false);
        initialize(_view);
        initializeLogic();
        return _view;
    }

    private void initialize(View _view) {
        btMain = _view.findViewById(R.id.btMain);
        txtResult = _view.findViewById(R.id.txtResult);
        etTime = _view.findViewById(R.id.etTime);
        etDistance = _view.findViewById(R.id.etDistance);

        btMain.setOnClickListener(_view1 -> {
            try {
                txtResult.setVisibility(View.VISIBLE);
                int rTime = (int) Float.parseFloat(etTime.getText().toString());

                int rRDistance = (int) Float.parseFloat(etDistance.getText().toString());
                rFDistance = (rRDistance / 100) * 60;
                rSpeed = (rFDistance / rTime) * 6;
                txtResult.setText(rSpeed + "km/h");
            } catch (Exception e) {
                Toast.makeText(getContext().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeLogic() {
        txtResult.setVisibility(View.GONE);
    }

}
