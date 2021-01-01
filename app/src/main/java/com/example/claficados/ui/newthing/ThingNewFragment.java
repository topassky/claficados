package com.example.claficados.ui.newthing;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.claficados.oi.txrx;
import com.example.claficados.ui.home.CoverPageVo;
import com.example.claficados.ui.home.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThingNewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThingNewFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<NewThingVo> listNewThingVo;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    View viewg;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThingNewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThingNewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThingNewFragment newInstance(String param1, String param2) {
        ThingNewFragment fragment = new ThingNewFragment();
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

        //listNewThingVo = new ArrayList<NewThingVo>();
        //recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewThingNews);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home_thing, container, false);
        viewg =root;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listNewThingVo = new ArrayList<NewThingVo>();
        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerViewThingNews);

        request = Volley.newRequestQueue(getContext());
        listNewThingVo.clear();
        getImages();


    }

    private void getImages() {

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
        String url = "https://www.comcop.co/tienda?producto&48&";//txrx.GenerarURL("2", "portada", "aaiL", filtro);//"https://www.comcop.co/run2.php?lleve={x1=x2}";//+ lleveCMP;
        //String url = txrx.GenerarURL("2", "Lista")
        //String url = "https://comcop.com.co/persia/include/wsJSONConsultarPortada.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        System.out.println("LOG CMP CA");
        System.out.println(jsonObjectRequest);
        request.add(jsonObjectRequest);
        progressDialog.hide();
    }


    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerViewThingNews);
        recyclerView.setLayoutManager(layoutManager);
        AdaptadorNewThing adapter = new AdaptadorNewThing( getContext(), listNewThingVo);
        recyclerView.setAdapter(adapter);
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

        JSONArray json=response.optJSONArray("Lista");
        JSONArray jsondetalles=response.optJSONArray("detalles");
        Log.d("ERROR2: ", ""+json);

        try{
            for (int i = 0; i < jsondetalles.length(); i++) {
                JSONObject jsonObject = null;
                jsonObject = jsondetalles.getJSONObject(i);
                jsonObject.optString("Par0");
                jsonObject.optString("Par1");
                jsonObject.optString("Par2");
                jsonObject.optString("Par3");
                jsonObject.optString("Par4");
                jsonObject.optString("Par5");
                jsonObject.optString("Par6");
                jsonObject.optString("Par7");

            }
            Log.d("ERROR: ", ""+listNewThingVo);
            initRecyclerView(viewg);

            progressDialog.hide();
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progressDialog.hide();
        }

        try {

            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                listNewThingVo.add(new NewThingVo(jsonObject.optString("Par0"),
                        jsonObject.optString("Par1")));

            }
            Log.d("ERROR: ", ""+listNewThingVo);
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