package com.example.claficados.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.claficados.R;
import com.example.claficados.ui.gallery.Image;

import java.util.ArrayList;

public class FeatureViewAdapter extends RecyclerView.Adapter<FeatureViewAdapter.ViewHoldersFeacture> {

    ArrayList<FeatureVo> listcoverFeature;
    private Context mContext;

    public FeatureViewAdapter(Context mContext, ArrayList<FeatureVo> listcoverFeature ) {
        this.listcoverFeature = listcoverFeature;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHoldersFeacture onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_features, parent, false);
        return new ViewHoldersFeacture(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersFeacture holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(listcoverFeature.get(position).getUrlcost())
                .into(holder.imagefeature);

        holder.namefeature.setText(listcoverFeature.get(position).getNombre());
        holder.costfeature.setText(listcoverFeature.get(position).getCost());

    }

    @Override
    public int getItemCount() {
        return listcoverFeature.size();
    }

    public class ViewHoldersFeacture extends RecyclerView.ViewHolder {

        ImageView imagefeature;
        TextView costfeature, namefeature;

        public ViewHoldersFeacture(@NonNull View itemView) {
            super(itemView);
            imagefeature = itemView.findViewById(R.id.f_img);
            costfeature = itemView.findViewById(R.id.f_cost);
            namefeature = itemView.findViewById(R.id.f_name);
        }
    }
}
