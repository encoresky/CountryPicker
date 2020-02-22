package com.android.countrypickerlibrary;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private Context context;
    private List<Country> countries;
    private boolean showDialingCode;
    private OnItemClickListener onItemClickListener;

    public CountryListAdapter(Context context, boolean showDialingCode) {
        this.context = context;
        this.countries = new ArrayList<>();
        this.showDialingCode = showDialingCode;
    }

    @NonNull
    @Override
    public CountryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new CountryListAdapter.ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
    this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListAdapter.ViewHolder holder, int position) {
        final Country country = countries.get(position);
        holder.name.setText(country.getCountryName());
        Log.d("languages", "onBindViewHolder: " + country.getCountryName());
        String drawableName = country.getIsoCode().toLowerCase(Locale.ENGLISH) + "_flag";
        holder.icon.setImageResource(Utils.getMipmapResId(context, drawableName));
        if(onItemClickListener!= null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                onItemClickListener.onItemClick(country);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setList(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(Country country);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
