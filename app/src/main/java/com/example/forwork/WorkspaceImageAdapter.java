package com.example.forwork;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WorkspaceImageAdapter extends RecyclerView.Adapter<WorkspaceImageAdapter.ViewHolder> {
    // Member variables.
    private ArrayList<String> imgUrlList;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param imgUrlList ArrayList containing the imageUrl
     * @param context    Context of the application.
     */
    WorkspaceImageAdapter(Context context, ArrayList<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
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
    public WorkspaceImageAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new WorkspaceImageAdapter.ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.workspace_img_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(WorkspaceImageAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        String currentWorkSpaceImg = imgUrlList.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentWorkSpaceImg);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return imgUrlList.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        // Member Variables for the TextViews
        private ImageView workSpaceImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            workSpaceImage = itemView.findViewById(R.id.workspace_detail_imgItem);

            // Set the OnClickListener to the entire view.
            workSpaceImage.setOnClickListener(this);
        }

        void bindTo(String currentImg) {

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentImg).into(workSpaceImage);
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
        }
    }
}
