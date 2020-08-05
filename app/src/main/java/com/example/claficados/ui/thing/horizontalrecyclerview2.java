package com.example.claficados.ui.thing;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.claficados.R;

import java.util.ArrayList;

public class horizontalrecyclerview2 extends RecyclerView.Adapter<horizontalrecyclerview2.viewHolder2> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private ArrayList<String> mDescription2 = new ArrayList<>();
    private Context mContext2;


    public horizontalrecyclerview2 (Context context, ArrayList<String> names, ArrayList<String> imageUrls,
                                    ArrayList<String> Description2) {
        mNames2 = names;
        mImageUrls2 = imageUrls;
        mDescription2 = Description2;
        mContext2 = context;
    }
    @NonNull
    @Override
    public viewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lisitem_thing, parent, false);
        return new viewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder2 holder, final int position) {
        Glide.with(mContext2)
                .asBitmap()
                .load(mImageUrls2.get(position))
                .into(holder.imageViewThing);

        holder.textViewNameThing.setText(mNames2.get(position));
        holder.textViewdescriptionThing.setText(mDescription2 .get(position));

        holder.imageViewThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames2.get(position));
                Toast.makeText(mContext2, mNames2.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls2.size();
    }

    public class viewHolder2 extends RecyclerView.ViewHolder{
        ImageView imageViewThing;
        TextView textViewNameThing,textViewdescriptionThing;

        public viewHolder2(@NonNull View itemView) {
            super(itemView);
            imageViewThing = itemView.findViewById(R.id.idImagen);
            textViewNameThing =  itemView.findViewById(R.id.idNombre);
            textViewdescriptionThing = itemView.findViewById(R.id.idInfo);
        }
    }
}
