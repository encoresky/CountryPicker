package com.android.countrypickerlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SelectCountryActivity extends AppCompatActivity {
    private List<Country> countryList;
    private boolean showDialingCode;
    private RecyclerView recyclerView;
    private SelectCountryActivity context;
    private EditText searchView;
    private CountryListAdapter countryListAdapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_picker);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchForCountry(String.valueOf(s));
                } else {
                    countryListAdapter.setList(countryList);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        countryList = Utils.parseCountries(Objects.requireNonNull(Utils.getCountriesJSON(context)), context);
        countryListAdapter = new CountryListAdapter(SelectCountryActivity.this, showDialingCode);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(countryListAdapter);
        countryListAdapter.setList(countryList);
        countryListAdapter.setOnItemClickListener(new CountryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Country country) {
                Intent intent = new Intent();
                intent.putExtra("data", country);
                SelectCountryActivity.this.setResult(CountryPickerConstants.SELECT_COUNTRY_REQUEST_CODE, intent);
                SelectCountryActivity.this.finish();
            }
        });
    }


    @SuppressLint("CheckResult")
    private void searchForCountry(final String searchKey) {
        Observable.fromIterable(countryList)
                .filter(new Predicate<Country>() {
                    @Override
                    public boolean test(Country item) throws Exception {
                        return item.getCountryName().toLowerCase().contains(searchKey.toLowerCase());
                    }
                })
                .toList()
                .subscribe(new Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> filteredList) throws Exception {
                        countryListAdapter.setList(filteredList);
                    }
                });
    }


}
