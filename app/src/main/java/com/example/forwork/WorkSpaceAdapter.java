package com.example.forwork;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkSpaceAdapter extends RecyclerView.Adapter<WorkSpaceAdapter.ViewHolder> {
    // Member variables.
    private ArrayList<WorkSpace> workSpaceData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param workSpaceData ArrayList containing the sports data.
     * @param context       Context of the application.
     */
    WorkSpaceAdapter(Context context, ArrayList<WorkSpace> workSpaceData) {
        this.workSpaceData = workSpaceData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public WorkSpaceAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.working_space_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(WorkSpaceAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        WorkSpace currentWorkSpace = workSpaceData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentWorkSpace);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return workSpaceData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView nameText;
        private TextView addressText;
        private ImageButton workSpaceImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            nameText = itemView.findViewById(R.id.working_space_name);
            addressText = itemView.findViewById(R.id.working_space_address);
            workSpaceImage = itemView.findViewById(R.id.working_space_img);

            // Set the OnClickListener to the entire view.
            workSpaceImage.setOnClickListener(this);
        }

        void bindTo(WorkSpace currentSport) {
            // Populate the textviews with data.
            nameText.setText(currentSport.getName());
            addressText.setText(currentSport.getAddress());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentSport.getImage()).into(workSpaceImage);
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            WorkSpace currentSport = workSpaceData.get(getAdapterPosition());
            Log.i("Testing", currentSport.getName());
        }
    }
}
