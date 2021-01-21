package com.example.claficados.ui.adress;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claficados.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAdress#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAdress extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Spinner spinnerCountry, spinnerZone, spinnerId;
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> zoneList = new ArrayList<>();
    ArrayList<String> IdList = new ArrayList<>();
    ArrayList<String> arrayZone = new ArrayList<>();


    ArrayAdapter<String> countryAdapter;
    ArrayAdapter<String> cityAdapter;
    ArrayAdapter<String> IdAdapter;
    RequestQueue requestQueue, request;

    EditText Add_adress_name, Add_adress_City, Add_code_postal;
    Button Add_adress_btn;

    StringRequest stringRequest;
    String SelectedCountryglobal;
    String SelectZone;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddAdress() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAdress.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAdress newInstance(String param1, String param2) {
        AddAdress fragment = new AddAdress();
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
        return inflater.inflate(R.layout.fragment_add_adress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Add_adress_name = (EditText)view. findViewById(R.id.Add_adress_name);
        Add_adress_City = (EditText)view .findViewById(R.id.Add_adress_City);
        Add_code_postal = (EditText)view. findViewById(R.id.Add_code_postal);

        requestQueue = Volley.newRequestQueue(getContext());
        request = Volley.newRequestQueue(getContext());
        spinnerCountry = (Spinner)view.findViewById(R.id.spinner_coutry);
        spinnerZone = (Spinner)view.findViewById(R.id.spiner_Zone);
        spinnerId = (Spinner)view.findViewById(R.id.spinnerId);
        Add_adress_btn = (Button)view.findViewById(R.id.Add_adress_btn);

        Add_adress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargar_web_service();
            }
        });

        Zone();


    }

    private void cargar_web_service(){
        String url = "https://comcop.co/mypythonapp/address";



        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("registra")){
                    Add_adress_name.setText("");
                    Add_adress_City.setText("");
                    Add_code_postal.setText("");
                    Toast.makeText(getContext(), "Se registro existozamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "No registro", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error"+ error , Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String address = Add_adress_name.getText().toString();
                String city = Add_adress_City.getText().toString();
                String Numberpostal = Add_code_postal.getText().toString();
                String SelectedCountryglobal2 = SelectedCountryglobal;
                String SelectZone2 = SelectZone;

                Map <String,String> parameters = new HashMap<>();
                parameters.put("address",address);
                parameters.put("city",city);
                parameters.put("Numberpostal",Numberpostal);
                parameters.put("Country",""+SelectedCountryglobal2);
                parameters.put("Zone",""+SelectZone2);
                return parameters;
            }
        };
        request.add(stringRequest);
    }

    private void Zone() {


        String url = "https://www.comcop.co/tienda?Pais&&";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Pais");
                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String CountryName = jsonObject.optString("Par1");
                        countryList.add(CountryName);
                        countryAdapter = new ArrayAdapter<> (getContext(),
                                R.layout.support_simple_spinner_dropdown_item, countryList);
                        countryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinnerCountry.setAdapter(countryAdapter);

                        String IdName = jsonObject.optString("Par0");
                        IdList.add(IdName);
                        IdAdapter = new ArrayAdapter<> (getContext(),
                                R.layout.support_simple_spinner_dropdown_item, IdList);
                        IdAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinnerId.setAdapter(IdAdapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Aqui hay un error de carga", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);
        spinnerCountry.setOnItemSelectedListener(this);
        spinnerZone.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                zoneList.clear();
                SelectedCountryglobal = IdList.get(position);
                Toast.makeText(getContext(), ""+SelectedCountryglobal, Toast.LENGTH_SHORT).show();
                final String selectedCountry = IdList.get(position);
                String url = "https://www.comcop.co/tienda?Region&" + selectedCountry + "&";
                requestQueue = Volley.newRequestQueue(getContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Region");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String ZoneName = jsonObject.optString("Par2");

                                zoneList.add(ZoneName);
                                cityAdapter = new ArrayAdapter<>(getContext(),
                                        R.layout.support_simple_spinner_dropdown_item, zoneList);
                                cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                spinnerZone.setAdapter(cityAdapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

            spinnerZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    arrayZone.clear();
                    String url = "https://www.comcop.co/tienda?Region&" + selectedCountry + "&";
                    requestQueue = Volley.newRequestQueue(getContext());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                            url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("Region");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String ZoneName = jsonObject.optString("Par0");

                                    arrayZone.add(ZoneName);

                                }
                                SelectZone = arrayZone.get(position);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(jsonObjectRequest);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


                requestQueue.add(jsonObjectRequest);

            }
            /*
            else if (parent.getId() == R.id.spiner_Zone){
               // Toast.makeText(getContext(), "Estoy aqui"  , Toast.LENGTH_SHORT).show();
                SelectZone = parent.getSelectedItemId()+1;

            }

             */







    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}