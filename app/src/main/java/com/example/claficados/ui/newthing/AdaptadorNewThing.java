package com.example.claficados.ui.newthing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.claficados.R;

import java.util.ArrayList;


public class AdaptadorNewThing extends RecyclerView.Adapter<AdaptadorNewThing.ViewHoldersNewThing> {

    ArrayList<NewThingVo> listNewThingVo;
    private Context mContext;



    public AdaptadorNewThing(Context mContext, ArrayList<NewThingVo> listNewThingVo) {
        this.mContext = mContext;
        this.listNewThingVo = listNewThingVo;
    }

    @NonNull
    @Override
    public ViewHoldersNewThing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_new_thing, parent, false);
        return new ViewHoldersNewThing(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersNewThing holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(listNewThingVo.get(position).getUrlImagen())
                .into(holder.image);
        holder.name.setText(listNewThingVo.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return listNewThingVo.size();
    }

    public class ViewHoldersNewThing extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        public ViewHoldersNewThing(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_newt_hing);
            name = itemView.findViewById(R.id.new_thing);


        }
    }
}
