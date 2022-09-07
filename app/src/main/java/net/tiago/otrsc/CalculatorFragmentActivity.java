package net.tiago.otrsc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    private SharedPreferences metricCalc;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
        View _view = _inflater.inflate(R.layout.calculator_fragment, _container, false);
        initialize(_view);
        initializeLogic();
        return _view;
    }

    @SuppressLint("SetTextI18n")
    private void initialize(View _view) {
        MaterialButton btCalc = _view.findViewById(R.id.btCalc);
        textResult = _view.findViewById(R.id.textResult);
        etTime = _view.findViewById(R.id.etTime);
        etDistance = _view.findViewById(R.id.etDistance);
        metricCalc = requireContext().getSharedPreferences("metricCalc", Activity.MODE_PRIVATE);

        btCalc.setOnClickListener(_view1 -> {
            try {
                if (metricCalc.getString("metric", "").equals("")) {
                    int rTime = (int) Float.parseFloat(etTime.getText().toString());

                    int rRDistance = (int) Float.parseFloat(etDistance.getText().toString());
                    rFDistance = (rRDistance / 100) * 60;
                    rSpeed = (rFDistance / rTime) * 6;
                    textResult.setText(rSpeed + "KPH");
                    textResult.setVisibility(View.VISIBLE);
                } else {
                    if (metricCalc.getString("metric", "").equals("1")) {
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

    private void initializeLogic() {
    }

}
