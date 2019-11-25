package com.example.forwork;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {
    private final List<String> mLocationList;
    private final LayoutInflater mInflater;
    private Context mContext;

    public LocationListAdapter(Context context, List<String> locationList) {
        mInflater = LayoutInflater.from(context);
        this.mLocationList = locationList;
        this.mContext = context;
    }

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView locationItemView;
        final LocationListAdapter mAdapter;
        public static final String MESSAGE8 = "com.example.android.forwork.MESSAGE8";

        private LocationViewHolder(View itemView, LocationListAdapter adapter) {
            super(itemView);
            locationItemView = itemView.findViewById(R.id.location_item);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("Testing", locationItemView.getText().toString());
            Intent intent = new Intent(mContext, ResultActivity.class);
            intent.putExtra(MESSAGE8, locationItemView.getText().toString());
            mContext.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mLocationView = mInflater.inflate(R.layout.locationlist_item, parent, false);
        return new LocationViewHolder(mLocationView, this);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        String mCurrent = mLocationList.get(position);
        holder.locationItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }
}
