package com.android.countrypicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.countrypickerlibrary.Country;
import com.android.countrypickerlibrary.CountryPickerConstants;
import com.android.democountrypicker.R;

public class SampleActivity extends AppCompatActivity {
    private static final String TAG = "SampleActivity";
    Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_activity);
        btnSelect = findViewById(R.id.buttonSelect);
        btnSelect.setOnClickListener(v -> startActivityForResult(new Intent(SampleActivity.this, com.android.countrypickerlibrary.SelectCountryActivity.class), CountryPickerConstants.SELECT_COUNTRY_REQUEST_CODE));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CountryPickerConstants.SELECT_COUNTRY_REQUEST_CODE) {
                assert data != null;
                Country country = data.getParcelableExtra("data");
                Log.e(TAG, "onActivityResult: country " + country + "");
            }
        }
    }
}

