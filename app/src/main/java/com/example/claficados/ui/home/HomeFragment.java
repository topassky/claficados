package com.example.claficados.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
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
import com.example.claficados.ui.gallery.GalleryFragment;
import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.ibrahimsn.lib.BottomBarItem;
import me.ibrahimsn.lib.BottomBarParser;


public class HomeFragment extends Fragment implements Response.Listener<JSONObject>,
        Response.ErrorListener{

    String url ="https://comcop.com.co/persia";
    private static final String TAG = "MainActivity";
    ArrayList<CoverPageVo> listCoverPageVo;

    RecyclerView recyclerView;
   // RadioButton r1,r0,r2,r3,r4,r5;
    TextView contador;
    Button btnPrductos;
    ProgressDialog progressDialog;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    View viewg;
    BottomBarItem bottomBarItem;

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
        btnPrductos = (Button)view.findViewById(R.id.Productos);



        /*PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_foto, popup.getMenu());
        popup.setOnMenuItemClickListener(HomeFragment.this);
        popup.show();
         */



        btnPrductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).navigate(R.id.thing);

            }
        });

        /*
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final WebView webMagento=root.findViewById(R.id.webMagento);
        webMagento.setWebViewClient(new MyWebViewClient());

        WebSettings settings=webMagento.getSettings();
        settings.setJavaScriptEnabled(true);
        webMagento.loadUrl(url);


        r0 =root.findViewById(R.id.radio0);
        r1 = root.findViewById(R.id.radio1);
        r2 = root.findViewById(R.id.radio2);
        r3 =root.findViewById(R.id.radio3);
        r4 = root.findViewById(R.id.radio4);
        r5 = root.findViewById(R.id.radio5);

         */
        recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);
        contador = (TextView)view.findViewById(R.id.contador);
        int total = listCoverPageVo.size();
        contador.setText("1-"+total);
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
                    String numposition= position+"";
                    contador.setText(numposition+"-"+listCoverPageVo.size());
                    //contador.setText(numposition+"-"+mImageUrls.size());
                }

            }

        });

        listCoverPageVo.clear();
        //mImageUrls.clear();
        //mNames.clear();
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
        String url = "https://www.comcop.co/run2.php?lleve={x1=x2}";//+ lleveCMP;
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
        //Portada portada = null;
        JSONArray json=response.optJSONArray("portada");

        try {

            for (int i = 0; i < json.length(); i++) {
                //portada = new Portada();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);

                //portada.setUrl(jsonObject.optString("urlfoto"));
                //portada.setNombre(jsonObject.optString("nombre"));
                //mImageUrls.add(i,jsonObject.optString("urlfoto"));
                //mNames.add(i,jsonObject.optString("nombre"));
                listCoverPageVo.add(new CoverPageVo(jsonObject.optString("nombre"),
                        jsonObject.optString("urlfoto")));

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




