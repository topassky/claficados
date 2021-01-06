package com.example.claficados.ui.newthing;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorNewthingMain extends RecyclerView.Adapter<AdaptadorNewthingMain.ViewNewthingMain>  {
    @NonNull
    @Override
    public ViewNewthingMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNewthingMain holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewNewthingMain extends RecyclerView.ViewHolder {
        public ViewNewthingMain(@NonNull View itemView) {
            super(itemView);
        }
    }
}
