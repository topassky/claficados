package com.example.claficados.oi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.claficados.ConecionSQLiteHelper;
import com.example.claficados.ui.user.login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class txrx  extends AsyncTask<String, Void, String> {
    private Context context;
    public txrx( Context current){
        this.context = current;

    }


    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    public static String GenerarURL(String user, String consulta,String protocolo, String Filtro){
        String url="";
        //protocolo necesita datos requeridos para manipular la información en el receptor, especifique aqui
        //B=Buscar , A=Escribir, D=Delete (No se borran los datos, se cambia el estado)
        //User contiene el token de actualización
        url="https://www.comcop.co/tienda?"+consulta+"&"+Filtro+"&"+protocolo+user;
        //url = "https://www.comcop.co/run2.php?lleve={"+protocolo+"=por-"+user+"}";
    return url;
    }


}
