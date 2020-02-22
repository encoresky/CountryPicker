package com.android.countrypickerlibrary;

import androidx.annotation.NonNull;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private final String countryName;
    private String isoCode;
    private String dialingCode;

    Country(String isoCode, String dialingCode, String countryName) {
        this.isoCode = isoCode;
        this.dialingCode = dialingCode;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getDialingCode() {
        return dialingCode;
    }

    public void setDialingCode(String dialingCode) {
        this.dialingCode = dialingCode;
    }

    private Country(Parcel in) {
        countryName = in.readString();
        isoCode = in.readString();
        dialingCode = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Country{" +
                "countryName=" + countryName +
                ", isoCode='" + isoCode + '\'' +
                ", dialingCode='" + dialingCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(countryName);
        dest.writeString(isoCode);
        dest.writeString(dialingCode);
    }
}
