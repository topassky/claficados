package com.example.claficados.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.claficados.R;
import com.example.claficados.ui.thing.thing;

import java.util.ArrayList;
import java.util.List;

import static com.example.claficados.oi.utilities.urlCover;
import static com.example.claficados.oi.utilities.urlmNames;

public class HomeFragment extends Fragment {

    String url ="https://comcop.com.co/persia";
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    RecyclerView recyclerView;
   // RadioButton r1,r0,r2,r3,r4,r5;
    TextView contador;
    Button btnPrductos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPrductos = (Button)view.findViewById(R.id.Productos);
        btnPrductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"hola",Toast.LENGTH_SHORT).show();

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
        contador.setText("1-"+urlCover.length);



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
                    contador.setText(numposition+"-"+mImageUrls.size());
/*
                    if (position==0){
                        r0.setChecked(true);
                    }
                    if (position==1){
                        r1.setChecked(true);
                    }
                    if (position==2){
                        r2.setChecked(true);
                    }
                    if (position==3) {
                        r3.setChecked(true);
                    }
                    if (position==4) {
                        r4.setChecked(true);
                    }
                    if (position==5) {
                        r5.setChecked(true);
                    }

 */



                }



            }

        });

        mImageUrls.clear();
        mNames.clear();
        getImages(view);
    }

    private void getImages(View root) {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        for (int i=0;i<urlCover.length;i++){
            mImageUrls.add(i,urlCover[i]);
            mNames.add(i,urlmNames[i]);

        }



        initRecyclerView(root);
    }

    private void initRecyclerView(View root) {


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        final RecyclerView recyclerView =root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( getContext(), mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "initRecyclerView: init recyclerview");
    }

    }
/*
    private class MyWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

 */



