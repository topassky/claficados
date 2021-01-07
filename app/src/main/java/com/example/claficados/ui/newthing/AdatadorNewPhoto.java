package com.example.claficados.ui.newthing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.claficados.R;

import java.util.ArrayList;

public class AdatadorNewPhoto extends RecyclerView.Adapter<AdatadorNewPhoto.ViewHoldersPhoto> {

    ArrayList<NewthingVoPhoto> listNewthingPhoto;
    private Context mContext;

    public AdatadorNewPhoto(Context mContext, ArrayList<NewthingVoPhoto> listNewthingPhoto) {
        this.listNewthingPhoto = listNewthingPhoto;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHoldersPhoto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lititem_mewthing_photo, parent, false);
        return new ViewHoldersPhoto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersPhoto holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(listNewthingPhoto.get(position).getUrlPhoto())
                .into(holder.UrlPhoto);

    }

    @Override
    public int getItemCount() {
        return listNewthingPhoto.size();
    }

    public class ViewHoldersPhoto extends RecyclerView.ViewHolder {
        ImageView UrlPhoto;
        public ViewHoldersPhoto(@NonNull View itemView) {
            super(itemView);

            UrlPhoto = (ImageView) itemView.findViewById(R.id.UrlThingMain2);
        }
    }
}
