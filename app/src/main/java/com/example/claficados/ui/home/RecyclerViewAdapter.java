package com.example.claficados.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.claficados.oi.Globales;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.claficados.R;
import com.example.claficados.oi.txrx;
import com.example.claficados.ui.newthing.NewThingVo;
import com.example.claficados.ui.thing.ProductsVo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    //vars
    //private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();
    private Context mContext;
    ArrayList<CoverPageVo> listCoverPagerVo;

    public RecyclerViewAdapter(Context mContext, ArrayList<CoverPageVo> listCoverPagerVo) {
        this.mContext = mContext;
        this.listCoverPagerVo = listCoverPagerVo;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        //ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
        //layoutParams.height = (int) (parent.getHeight() * 0.3);
        //view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(listCoverPagerVo.get(position).getmImageUrls())
                .into(holder.image);

        holder.name.setText(listCoverPagerVo.get(position).getmNames());


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image: " + listCoverPagerVo.get(position).getmNames());
                Toast.makeText(mContext, "indice"+listCoverPagerVo.get(position).getmNames(), Toast.LENGTH_SHORT).show();
                Globales consul= new Globales();
                consul.setconsulta(""+listCoverPagerVo.get(position).getmNames());

            }
        });

    }

    @Override
    public int getItemCount() {

        return listCoverPagerVo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}
