package com.example.forwork;

import android.content.Context;
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

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {
    private final LinkedList<String> mLocationList;
    private final LayoutInflater mInflater;

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView locationItemView;
        final LocationListAdapter mAdapter;

        public LocationViewHolder(View itemView, LocationListAdapter adapter) {
            super(itemView);
            locationItemView = itemView.findViewById(R.id.location_item);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("Testing", locationItemView.getText().toString());
        }
    }

    public LocationListAdapter(Context context, LinkedList<String> locationList) {
        mInflater = LayoutInflater.from(context);
        this.mLocationList = locationList;
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
