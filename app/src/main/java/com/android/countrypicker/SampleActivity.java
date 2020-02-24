package com.android.countrypicker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.countrypickerlibrary.Country;
import com.android.democountrypicker.R;

public class SampleActivity extends AppCompatActivity {
    private static final String TAG = "SampleActivity";
    private static final int REQUEST_CODE_SELECT_COUNTRY = 1;
    Button btnSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_activity);
        btnSelect = findViewById(R.id.buttonSelect);
        btnSelect.setOnClickListener(v -> startActivityForResult(new Intent(SampleActivity.this, com.android.countrypickerlibrary.SelectCountryActivity.class), REQUEST_CODE_SELECT_COUNTRY));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SELECT_COUNTRY) {
                assert data != null;
                Country country = data.getParcelableExtra("data");
                Log.e(TAG, "onActivityResult: country " + country + "");
            }
        }
    }
}

