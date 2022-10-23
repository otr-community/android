package org.otrsc.android;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class CalculatorFragmentActivity extends Fragment {

    private double rFDistance = 0;
    private double rSpeed = 0;
    private double rMph = 0;

    private TextView textResult;
    private EditText etTime;
    private EditText etDistance;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        View _view = _inflater.inflate(R.layout.calculator_fragment, _container, false);
        initialize(_view);
        return _view;
    }

    @SuppressLint("SetTextI18n")
    private void initialize(View _view) {
        MaterialButton btCalc = _view.findViewById(R.id.btCalc);
        textResult = _view.findViewById(R.id.textResult);
        etTime = _view.findViewById(R.id.etTime);
        etDistance = _view.findViewById(R.id.etDistance);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(requireContext());

        btCalc.setOnClickListener(_view1 -> {
            try {
                if (sp.getString("default_metric", "0").equals("0")) {
                    int rTime = (int) Float.parseFloat(etTime.getText().toString());

                    int rRDistance = (int) Float.parseFloat(etDistance.getText().toString());
                    rFDistance = (rRDistance / 100) * 60;
                    rSpeed = (rFDistance / rTime) * 6;
                    textResult.setText(rSpeed + "KPH");
                    textResult.setVisibility(View.VISIBLE);
                } else {
                    if (sp.getString("default_metric", "0").equals("1")) {
                        int rTime = (int) Float.parseFloat(etTime.getText().toString());

                        int rRDistance = (int) Float.parseFloat(etDistance.getText().toString());
                        rFDistance = (rRDistance / 100) * 60;
                        rSpeed = (rFDistance / rTime) * 6;
                        rMph = (rSpeed / 1.6);
                        textResult.setText(rMph + "MPH");
                        textResult.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(requireContext().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
