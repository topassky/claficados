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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.claficados.R;

import java.util.ArrayList;

public class horizontalrecyclerview2 extends RecyclerView.Adapter<horizontalrecyclerview2.viewHolder2>
    implements View.OnClickListener {

    private static final String TAG = "RecyclerViewAdapter";
    private View.OnClickListener listener;
    //vars
    private Context mContext2;
    ArrayList<ProductsVo> lisrProductsVo;

    public horizontalrecyclerview2(Context mContext2, ArrayList<ProductsVo> lisrProductsVo) {
        this.mContext2 = mContext2;
        this.lisrProductsVo = lisrProductsVo;
    }


    @NonNull
    @Override
    public viewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lisitem_thing, parent, false);

        view.setOnClickListener(this);

        return new viewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder2 holder, final int position) {
        //Url para esta ventana:
        //https://www.comcop.co/tienda?producto&&
        //SELECT * FROM `ocaw_product_description` WHERE `product_id` = 48
        //Json retorna 7 parámetros (Par3 es la descripción)
        //Par0, Par1, Par2, Par3, Par4, Par5, Par6, Par7
        Glide.with(mContext2)
                .asBitmap()
                .load(lisrProductsVo.get(position).getmImageUrls2())
                .into(holder.imageViewThing);

        holder.textViewNameThing.setText(lisrProductsVo.get(position).getmNames2());
        holder.textViewdescriptionThing.setText(lisrProductsVo.get(position).getmDescription2());
        holder.textTitleBody.setText(lisrProductsVo.get(position).getmNameBody());
        holder.textDescriptionBody.setText(lisrProductsVo.get(position).getmDescriptionBody());
        holder.textAdsTitle.setText(lisrProductsVo.get(position).getTextAdsTitle());
        holder.textAds.setText(lisrProductsVo.get(position).getTextAds());

        holder.imageViewThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + lisrProductsVo.get(position).getmNames2());
               Toast.makeText(mContext2, lisrProductsVo.get(position).getmNames2(), Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.thingNewFragment);
            }
        });


    }

    @Override
    public int getItemCount() {
        return lisrProductsVo.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public class viewHolder2 extends RecyclerView.ViewHolder{
        ImageView imageViewThing;
        TextView textViewNameThing,textViewdescriptionThing
                ,textTitleBody, textDescriptionBody,
                textAdsTitle,textAds;

        public viewHolder2(@NonNull View itemView) {
            super(itemView);
            imageViewThing = itemView.findViewById(R.id.idImagen);
            textViewNameThing =  itemView.findViewById(R.id.idNombre);
            textViewdescriptionThing = itemView.findViewById(R.id.idInfo);
            textTitleBody = itemView.findViewById(R.id.textTitleBody);
            textDescriptionBody = itemView.findViewById(R.id.textDescriptionBody );
            textAdsTitle =itemView.findViewById(R.id.textAdsTitle);
            textAds = itemView.findViewById(R.id.textAds);
        }
    }
}
