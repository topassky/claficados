package com.example.claficados.ui.adress;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.claficados.R;
import com.example.claficados.ui.home.CategoryVO;
import com.example.claficados.ui.home.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adressMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adressMain extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerViewAdress;
    private Button BtnContinueToPayment;
    private Button BtnAddAddress;

    ArrayList<AdressVo> listAddress;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    View viewg;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adressMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adress.
     */
    // TODO: Rename and change types and number of parameters
    public static adressMain newInstance(String param1, String param2) {
        adressMain fragment = new adressMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_adress, container, false);
        viewg =root;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAdress = (RecyclerView)view.findViewById(R.id.recyclerViewAdress);
        BtnContinueToPayment = (Button) view.findViewById(R.id.BtnContinueToPayment);
        BtnAddAddress = (Button)view.findViewById(R.id.BtnAddAddress);
        listAddress = new ArrayList<AdressVo>();


        BtnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.addAdress);
            }
        });

        BtnContinueToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.payment);
            }
        });

        request = Volley.newRequestQueue(getContext());
        listAddress.clear();

        getAddress();


    }

    private void getAddress() {

        String url = "https://comcop.co/mypythonapp/getaddress/1";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json=response.optJSONArray("get_address");
        try {

            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                listAddress.add(new AdressVo(
                        jsonObject.optString("address_1"),
                        jsonObject.optString("city"),
                        jsonObject.optString("zone_id")));


            }

            initRecyclerView(viewg);

        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();

        }

    }

    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerViewAdress);
        recyclerView.setLayoutManager(layoutManager);
        AdaptorAdress adapter = new AdaptorAdress( getContext(), listAddress);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "initRecyclerView: init recyclerview del la direccion");
    }
}