package com.example.claficados.ui.adress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.claficados.R;
import com.example.claficados.ui.newthing.AdaptadorNewThing;
import com.example.claficados.ui.newthing.NewThingVo;

import java.util.ArrayList;

public class AdaptorAdress extends RecyclerView.Adapter<AdaptorAdress.ViewModels> {

    ArrayList<AdressVo> listAddress;
    private Context mContext;
    private RadioButton address_radBut;

    public AdaptorAdress(Context mContext, ArrayList<AdressVo> listAddress) {
        this.listAddress = listAddress;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewModels onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_address, parent, false);

        return new ViewModels(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModels holder, final int position) {
        holder.address_rcl.setText(listAddress.get(position).getAddressVo());
        holder.City_rcl.setText(listAddress.get(position).getCityVo());
        holder.Zone_rcl.setText(listAddress.get(position).getZoneVo());
        holder.address_radBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AdressVo address:listAddress){
                    address.setSelected(false);
                }

                listAddress.get(position).setSelected(true);

                if(address_radBut!=null){
                    address_radBut.setChecked(false);
                }

                address_radBut = (RadioButton) v;
                address_radBut.setChecked(true);

            }
        });

    }

    @Override
    public int getItemCount() {

        return listAddress.size();
    }

    public class ViewModels extends RecyclerView.ViewHolder {
        private TextView address_rcl, City_rcl, Zone_rcl;
        private RadioButton address_radBut;

        public ViewModels(@NonNull View itemView) {
            super(itemView);

            address_rcl = (TextView)itemView.findViewById(R.id.address_rcl);
            City_rcl = (TextView)itemView.findViewById(R.id.City_rcl);
            Zone_rcl = (TextView)itemView.findViewById(R.id.Zone_rcl);
            address_radBut = (RadioButton)itemView.findViewById(R.id.address_radBut);

        }
    }
}
