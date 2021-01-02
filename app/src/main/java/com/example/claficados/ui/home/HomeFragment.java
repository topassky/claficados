package com.example.claficados.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.claficados.R;
import com.example.claficados.oi.txrx;
import com.example.claficados.oi.Globales;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    private static final String TAG = "MainActivity";
    ArrayList<CoverPageVo> listCoverPageVo;

    RecyclerView recyclerView;
   // RadioButton r1,r0,r2,r3,r4,r5;
    TextView seeAllFe;
    Button btnPrductos;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    View viewg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewg =root;
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listCoverPageVo = new ArrayList<CoverPageVo>();

        seeAllFe = (TextView) view.findViewById(R.id.seeAllFe);


        seeAllFe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.thing);

            }
        });




        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);
        //contador = (TextView)view.findViewById(R.id.contador);
        LinearSnapHelper snapHelper  = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        request = Volley.newRequestQueue(getContext());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //Dragging
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    int position = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition()+1;
                    //Toast.makeText(getContext(), "indice"+position, Toast.LENGTH_SHORT).show();
                    //String numposition= position+"";
                    //contador.setText(numposition+"-"+listCoverPageVo.size());
                    //contador.setText(numposition+"-"+mImageUrls.size());
                }

            }

        });

        listCoverPageVo.clear();
        getImages();
    }

    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Consultado Home...");
        progressDialog.show();
        String lleveCMP= "?lleve={";// json con los parametros de preferencias de usuariopropongo
        ///////PROTOCOLO///////
        //version (sep-9-2020)
        String version="11.9.4";
        //Laves
        String llaves="PROVPROV";
        //Solicitud( codigo sesion, )
        String sesion="PROVPROV";//"Establecer desde contantes";
        // cadena de n bits que describan cada uno de los productos que de una u otra manera han sido motivo de reaccion del usuario
        //Ej  ropa   gimnasio    mascotas    corbatas    ...   mensajerìa        Veterinarios      taxis
        //       1         1          0           0        ...      0                   0              1
        //128bits=16 bytes
        String preferencias="asdfghjkloiuytrf";
        //pagina , reset y EOF
        lleveCMP=lleveCMP+version+"="+llaves+sesion+preferencias+"}";
        Globales consul=new Globales();
        String filtro= consul.getConsulta();
        String url = txrx.GenerarURL("2", "portada", "aaiL", filtro);//"https://www.comcop.co/run2.php?lleve={x1=x2}";//+ lleveCMP;
        //String url = "https://comcop.com.co/persia/include/wsJSONConsultarPortada.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        System.out.println("LOG CMP CA");
        System.out.println(jsonObjectRequest);
        request.add(jsonObjectRequest);
        progressDialog.hide();
    }

    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( getContext(), listCoverPageVo);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "initRecyclerView: init recyclerview");
        progressDialog.hide();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progressDialog.hide();

    }
    @Override
    public void onResponse(JSONObject response) {
        JSONArray json=response.optJSONArray("portada");

        /**
         * Total del contador esto es totalmente independiemente al proceos de llamado
         * de elementos
         */
        //int total = json.length();
        //contador.setText("1-"+total);

        /**
         * Inicio de llamado de los elementos del servidor
         */

        try {

            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                listCoverPageVo.add(new CoverPageVo(jsonObject.optString("Par0"),
                        jsonObject.optString("Par1")));

            }
            initRecyclerView(viewg);
            progressDialog.hide();
        }catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }

    }







}




