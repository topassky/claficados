package com.example.claficados.ui.adress;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptorAdress extends RecyclerView.Adapter<AdaptorAdress.ViewModels> {
    @NonNull
    @Override
    public ViewModels onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModels holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewModels extends RecyclerView.ViewHolder {
        public ViewModels(@NonNull View itemView) {
            super(itemView);
        }
    }
}
