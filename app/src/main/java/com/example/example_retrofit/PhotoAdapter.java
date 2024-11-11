package com.example.example_retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> photoList;
    private Context context;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.textTitle.setText(photo.getTitle());

        // Загрузка изображения с помощью Glide
        Glide.with(context)
                .load(photo.getThumbnailUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textTitle;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textTitle = itemView.findViewById(R.id.textTitle);
        }
    }
}
