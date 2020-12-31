package com.example.claficados.ui.thing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.claficados.R;
import com.example.claficados.oi.Globales;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
//import static com.example.claficados.oi.utilities.urlCover;
//import static com.example.claficados.oi.utilities.urlmNames;


public class thing extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    /*
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private ArrayList<String> mDescription2 = new ArrayList<>();

     */

    ArrayList<ProductsVo> lisrProductsVo;

    RecyclerView recyclerView2;

    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    View viewg;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_thing, container, false);

        recyclerView2 =(RecyclerView)root.findViewById(R.id.recyclerViewThing);
        request = Volley.newRequestQueue(getContext());
        viewg =root;
        getImages();
        return root;
    }

    private void getImages() {

        /*
        mImageUrls2.add("http://www.comcop.com.co/persia/imgsApp/logoComcop.png");
        mNames2.add("Estes es un nombre");
        mDescription2.add("Esta es una descripcion");

        mImageUrls2.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames2.add("Estes es un nombre");
        mDescription2.add("Esta es una descripcion");

         */
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado...");
        progressDialog.show();
        //String url = "https://comcop.com.co/persia/include/wsJSONConsultarProductos.php";// Cambiarlo por comcop.co
        Globales x= new Globales();

        String url= "https://www.comcop.co/tienda?productos&"+x.getConsulta()+"&" ;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);



       // initRecyclerView(root);

    }

    private void initRecyclerView(final View root) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerViewThing);
        recyclerView.setLayoutManager(layoutManager);
        horizontalrecyclerview2 adapter2 = new horizontalrecyclerview2( getContext(),lisrProductsVo);
         /*
        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),""+lisrProductsVo.get
                        (recyclerView2.getChildAdapterPosition(v)).getmNames2(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), PorductPresentation.class);
                startActivity(intent);




                Globales consulta= new Globales();
                consulta.setconsulta(""+lisrProductsVo.get
                        (recyclerView2.getChildAdapterPosition(v)).getmNames2());




            }
        });

          */
        recyclerView.setAdapter(adapter2);
        Log.d(TAG, "initRecyclerView: init recyclerview");
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray json=response.optJSONArray("productos");
        lisrProductsVo =new ArrayList<>();
        try {

            for (int i = 0; i < json.length(); i++) {
                //portada = new Portada();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                //portada.setUrl(jsonObject.optString("urlfoto"));
                //portada.setNombre(jsonObject.optString("nombre"));
                lisrProductsVo.add(new ProductsVo(jsonObject.optString("nombre"),
                        jsonObject.optString("Par1"),
                        jsonObject.optString("Par2"),
                        "Esto es el nombre del cuerpo",
                        "Esto es la descripción del cuerpo","Estes el decuento",
                        "Aqui se calcula el descuento"));


            }
            progressDialog.hide();
            initRecyclerView(viewg);
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progressDialog.hide();


    }
}