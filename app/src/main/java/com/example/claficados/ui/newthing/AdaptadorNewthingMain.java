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

public class AdaptadorNewthingMain extends RecyclerView.Adapter<AdaptadorNewthingMain.ViewNewthingMain>  {

    ArrayList<NewThingVoMain> listNewThingVoMain;
    private Context mContext;

    public AdaptadorNewthingMain( Context mContext, ArrayList<NewThingVoMain> listNewThingVoMain) {
        this.listNewThingVoMain = listNewThingVoMain;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewNewthingMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_newthing, parent, false);
        return new ViewNewthingMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNewthingMain holder, int position) {
        /*
        Glide.with(mContext)
                .asBitmap()
                .load(listNewThingVoMain.get(position).getUrlThingMain())
                .into(holder.UrlThingMain2);

         */
        holder.name_main_photo.setText(listNewThingVoMain.get(position).getName_main_photo());
        holder.NewThingCost.setText(listNewThingVoMain.get(position).getNewThingCost());
        holder.StarsNewThingMain.setText(listNewThingVoMain.get(position).getStarsNewThingMain());
        holder.QualificationNewThingMain.setText(listNewThingVoMain.get(position).getQualificationNewThingMain());
        holder.TituleDescriptionThingnewMain.setText(listNewThingVoMain.get(position).getTituleDescriptionThingnewMain());
        holder.DescriptionNewthingMain.setText(listNewThingVoMain.get(position).getDescriptionNewthingMain());
        holder.reviews.setText(listNewThingVoMain.get(position).getReviwes());
    }

    @Override
    public int getItemCount() {
        return listNewThingVoMain.size();
    }

    public class ViewNewthingMain extends RecyclerView.ViewHolder {

        //ImageView UrlThingMain2;
        TextView name_main_photo;
        TextView NewThingCost;
        TextView StarsNewThingMain;
        TextView QualificationNewThingMain;
        TextView TituleDescriptionThingnewMain;
        TextView DescriptionNewthingMain;
        TextView reviews;

        public ViewNewthingMain(@NonNull View itemView) {
            super(itemView);
            //UrlThingMain2 =(ImageView) itemView.findViewById(R.id.UrlThingMain2);
            name_main_photo = (TextView) itemView.findViewById(R.id.name_newthingMain);
            NewThingCost = (TextView) itemView.findViewById(R.id.NewThingCost);
            StarsNewThingMain = (TextView) itemView.findViewById(R.id.StarsNewThingMain);
            QualificationNewThingMain = (TextView) itemView.findViewById(R.id.QualificationNewThingMain);
            TituleDescriptionThingnewMain = (TextView) itemView.findViewById(R.id.TituleDescriptionThingnewMain);
            DescriptionNewthingMain = (TextView) itemView.findViewById(R.id.DescriptionNewthingMain);
            reviews = (TextView) itemView.findViewById(R.id.comentarios);

        }
    }
}
