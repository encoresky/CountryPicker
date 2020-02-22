package com.android.countrypickerlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class CountryListAdapterDialog extends BaseAdapter {

    private final Context mContext;
    private static final String TAG = CountryListAdapterDialog.class.getSimpleName();
    private LayoutInflater inflater;
    private List<Country> countries;
    private boolean showDialingCode;

    public CountryListAdapterDialog(Context context, List<Country> countries, boolean showDialingCode) {
        mContext = context;
        this.countries = countries;
        this.showDialingCode = showDialingCode;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        Item item;
        Country country = countries.get(position);

        if (convertView == null) {
            item = new Item();
            itemView = inflater.inflate(R.layout.item_country, parent, false);
            item.setIcon((ImageView) itemView.findViewById(R.id.icon));
            item.setName((TextView) itemView.findViewById(R.id.name));
            itemView.setTag(item);
        } else {
            item = (Item) itemView.getTag();
        }

         item.getName().setText(new Locale(mContext.getResources().getConfiguration().locale.getLanguage(),
                 country.getIsoCode()).getDisplayCountry() + (showDialingCode ?
                 " (+" + country.getDialingCode() + ")" : ""));

        // Load drawable dynamically from country code
        String drawableName = country.getIsoCode().toLowerCase(Locale.ENGLISH) + "_flag";
        item.getIcon().setImageResource(Utils.getMipmapResId(mContext, drawableName));
        return itemView;
    }

    public static class Item {
        private TextView name;
        private ImageView icon;

        public ImageView getIcon() {
            return icon;
        }

        public void setIcon(ImageView icon) {
            this.icon = icon;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }
    }
}