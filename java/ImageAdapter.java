package com.example.restaurant_management_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<ImageModel> imageList;
    private ArrayList id,name;
    public ImageAdapter(List<ImageModel> imageList,ArrayList name,ArrayList id) {
        this.imageList = imageList;
        this.id=id;
        this.name=name;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageModel imageModel = imageList.get(position);
        holder.imageView.setImageBitmap(imageModel.getImage());
        holder.tvNamerc1.setText(String.valueOf(name.get(position)));
        holder.tvId.setText(String.valueOf(id.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
TextView tvNamerc1,tvId;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvNamerc1 = itemView.findViewById(R.id.tvNamerc);
            tvId = itemView.findViewById(R.id.tvIdrc);
        }
    }
}
