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
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String cadena = strings[0];
        String regreso = "";
        if (strings[1].equals("0")){ //Consultar todos los registros
            StringBuilder result = retornarSB(cadena);
            if (result != null){
                regreso = leerJSON(result,"", "all");
            }else{
                regreso = "No se pudo realizar la operacion, contacte a soporte 42";
            }

        }else if (strings[1].equals("1")){  //Consulta por ID
            StringBuilder result = retornarSB(cadena);
            String resultc="";
            if (result != null){
                String a = "\\n<p>";
                String b= "<\\/p>\\n";
                int intIndexini = result.indexOf(a);
                int intIndexfin = result.indexOf(b);
                //Guardar result en bd
                System.out.println(intIndexini);
                System.out.println(intIndexfin);
                resultc=result.toString().substring(intIndexini+5,intIndexfin).replace("*", "\"");
                System.out.println("69 TENSMOS====================\nresultc===================\n"+resultc);
                regreso = leerJSON(null,resultc, "cmp");
                String regreso2=JSONtoArray(resultc);
                System.out.println("65 TENSMOS====================\n===================\n"+regreso2 );
            }else{
                regreso = "No se pudo realizar la operacion, contacte a soporte 53";
            }
        }else if (strings[1].equals("2")){  //Insertar alumno
            regreso = funcionesRUD(cadena, "insertar");
        }else if (strings[1].equals("3")){  //Actualizar
            regreso = funcionesRUD(cadena, "actualizar");
        }else if (strings[1].equals("8")){  //consultar login
            regreso = funcionesRUD(cadena, "consultar");
            System.out.println("Verificando pas....");
        }else if (strings[1].equals("9")){  //consultar login
            regreso = funcionesRUD(cadena, "consultarg");
            System.out.println("Verificando pas....");
        }else if (strings[1].equals("4")){  //Borrar
            regreso = funcionesRUD(cadena, "borrar");
        }
        return regreso;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        //MainActivity.resultado.setText(s);
        //MainActivity.identificador.setText(null);
        //MainActivity.nombre.setText(null);
        //MainActivity.direction.setText(null);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    public static StringBuilder retornarSB (String cadena){    //
        URL url = null;
        String linea = "";
        StringBuilder result = null;
        boolean todoOK = false;
        try{
            url = new URL(cadena);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent","Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
            int respuesta = connection.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream()); //Se prepara la cadena de entrada
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  //Se introduce en un buffered reader

                /*
                   Ahora para utilizar el JSONObject es necesario un string, por tanto hay que convertir
                   El BufferedReader en una cadena String
                 */
                //A continuacion se lee lina por linea el buffered reader y se va asignando al StringBuilder
                while ((linea = reader.readLine()) != null){
                    result.append(linea);
                } //UNa vez finalizado tenemos un StringBuilder result que leera el JSONObject
                todoOK = true;  //Si realizo todos los pasos del try, esta variable pasara a true con el propito de validar que la operacion se realizo exitosamente
            }
            connection.disconnect();
        }catch (MalformedURLException e){
            e.printStackTrace();;
        }catch (IOException e){
            e.printStackTrace();
        }
        //Se borran datos contenidos en memoria para optimizar
        if (todoOK == false){
            return null;
        }else{
            return result;
        }
    }

    public static String JSONtoArray (String JSON){
        String a = "\"tour\":{";
        String b= "},\"seg\":[";
        int intIndexini = JSON.indexOf(a);
        int intIndexfin = JSON.indexOf(b);
        //Guardar result en bd
        int ultimo=JSON.length();
        System.out.println("160 " +JSON);
        String tour=JSON.substring(intIndexini+7,intIndexfin+1);
        String ventanas= JSON.substring(intIndexfin+8,ultimo-1 );
        String Provicional;

        GuaradrTour(tour);
        GuardarVentanas(ventanas);

        return tour+ventanas;
    }
    public static String GuardarVentanas (String Provicional) {
        int llave1 = 1;
        int llave0 = 0;
        int llave2 = 0;
        boolean bandera=true;
        while (bandera){

            llave1=Provicional.indexOf("{", llave0);
            llave2=Provicional.indexOf("}", llave1);
            if (llave1 >-1){
                System.out.println(Provicional.substring(llave1, llave2+1));
                //System.out.println("169 " + Provicional.substring(llave1,llave2+1));
                llave0=llave2;
            }
            else
            { bandera=false;}
        }
        return "Error";
    }

    public static String GuaradrTour (String Provicional) {
        int coma1 = Provicional.indexOf(",");
        int dospuntos1 = Provicional.indexOf(":");

        String cadena="";
        for (int i =1; i<7;i++) {
            cadena=cadena+  Provicional.substring(dospuntos1+1,coma1)+",";
            int coma2 = Provicional.indexOf(",", coma1 + 1);
            int dospuntos2=Provicional.indexOf(":", coma1+1);
            System.out.println("174 " + coma2);
            if ((coma2>-1) && (dospuntos2>-1)){
                coma1=coma2;
                dospuntos1=dospuntos2;
            }
            else
            {
                coma1=Provicional.length()-1;
                dospuntos1=dospuntos2;}
        }
        int n=cadena.length();
        String insert = "" ;//"INSERT INTO " + Utilities.tblTour +" (" + Utilities.idTour + " ,"+Utilities.iddueno
                //+ " ,"+ Utilities.privacidad+ " ,"+Utilities.inicio+ " ,"+Utilities.fin + " ,"+ Utilities.nventanas
                //+ ") VALUES   (" +cadena.substring(1,n-1) + ")";
        return insert;
    }


    public static String leerJSON (StringBuilder result,String S2builder, String mode){

        String regreso = "";

        try{
            JSONArray registrosJSON=null;
            JSONObject respuestaJSON =null;
            //Se crea objeto JSON para poder acceder a los atributos (campos) del objeto
            if (mode!="cmp") {
                respuestaJSON = new JSONObject(result.toString());
            }
            else{
                respuestaJSON=new JSONObject("{\"registro\":"+S2builder+"}");
            }
            String resultJSON = respuestaJSON.getString("registro"); //Estado es el nombre del campo en el JSON

            if (resultJSON.equals("1")) { //Hay registros para mostrar


                System.out.println("170 TENSMOS====================\n===================\n"+result.toString());
                System.out.println("170 TENSMOS====================\n===================\n"+S2builder);

                if (mode.equals("cmp")){

                    registrosJSON = respuestaJSON.getJSONArray("registros");
                    for (int i=0; i < registrosJSON.length(); i++){ //Se recorre el ARRAY y se asigna a la variable String
                        regreso = regreso +registrosJSON.getJSONObject(i).getString("x1") + " " +
                                registrosJSON.getJSONObject(i).getString("x2") + " " +
                                registrosJSON.getJSONObject(i).getString("x3") + " " +
                                registrosJSON.getJSONObject(i).getString("x4") + " " +
                                registrosJSON.getJSONObject(i).getString("x5") + " " +
                                registrosJSON.getJSONObject(i).getString("x6") + " " ;

                        /***
                         registrosJSON.getJSONObject(i).getString(Utilities.idVentana) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.tour)  + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c0) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x0) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y0) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z0) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v0)+ " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c1) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x1) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y1) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z1) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v1)+ " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c2) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x2) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y2) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z2) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v2)+ " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c3) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x3) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y3) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z3) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v3)+ " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c0) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x4) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y4) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z4) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v4)+" " +
                         registrosJSON.getJSONObject(i).getString(Utilities.c5) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.x5) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.y5) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.z5) + " " +
                         registrosJSON.getJSONObject(i).getString(Utilities.v5)+"\n";
                         ***/}

                }
                else if (mode.equals("all")){
                    registrosJSON = respuestaJSON.getJSONArray("registros");
                    /*
                    for (int i=0; i < registrosJSON.length(); i++){ //Se recorre el ARRAY y se asigna a la variable String
                        regreso = regreso + registrosJSON.getJSONObject(i).getString("idAlumno") + " " +
                                registrosJSON.getJSONObject(i).getString("nombre") + " " +
                                registrosJSON.getJSONObject(i).getString(Utilities.idVentana) + " " +
                                registrosJSON.getJSONObject(i).getString(Utilities.tour) + "\n";
                    }

                     */


                }else if (mode.equals("single")){
                    //registrosJSON = respuestaJSON.getJSONArray("registros");
                    regreso = regreso + respuestaJSON.getJSONObject("alumno").getString("idAlumno") +  " " +
                            respuestaJSON.getJSONObject("alumno").getString("nombre") + " " +
                            respuestaJSON.getJSONObject("alumno").getString("direccion") + "\n";
                }
            }else{
                regreso = "No se encontraron registros en nuestra base de datos";
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        System.gc();

        if (regreso.equals("")){
            return "243 No se pudo realizar la operacion, favor contactar a soporte";
        }else{
            //System.out.println(" 245 TENSMOS====================\n===================\n"+regreso);
            return regreso;
        }
    }

    public static String GenerarURL(String user, String consulta,String protocolo, String Filtro){
        String url="";

        url="https://www.comcop.co/tienda?"+consulta+"&"+Filtro+"&";
        //url = "https://www.comcop.co/run2.php?lleve={"+protocolo+"=por-"+user+"}";
    return url;
    }

    public String funcionesRUD(String cadena, String operacion){
        DataOutputStream printout;
        DataInputStream input;
        OutputStream os;
        JSONObject jsonParam = new JSONObject();
        JSONObject respuestaJSON = null;
        String result = null;
        String linea = "";
        String resultJSON = "";
        String regreso = "";
        Boolean flag = false;
        String variable1="nulo";
        try{
            //Se le asignan parametros al JSONObject
            System.out.println(" 279 TENSMOS====================\n===================\n"+(operacion));
            if (!(operacion.equals("consultar"))) {//Se refiere a consultar usuario exclusivamente
                if (operacion.equals("borrar")) {
                    jsonParam.put("idalumno", variable1); //Se crea objeto JSON con el parametro a enviar, ejemplo {"idalumno":"0"}
                } else if (operacion.equals("insertar")) {
                    jsonParam.put("nombre",variable1);
                    jsonParam.put("direccion", variable1);
                }else if (operacion.equals("consultarg")) {
                    jsonParam.put("nombre",variable1);
                    jsonParam.put("direccion", variable1);
                }

                else if (operacion.equals("actualizar")) {
                    jsonParam.put("idalumno", variable1); //Se crea objeto JSON con el parametro a enviar, ejemplo {"idalumno":"0"}
                    jsonParam.put("nombre", variable1);
                    jsonParam.put("direccion", variable1);
                }
            }else {
                jsonParam.put("password", login.etPassword.getText().toString());
                jsonParam.put("user", login.etEmail.getText().toString());
                System.out.println("TXRX "+  login.etPassword.getText().toString()+ login.etEmail.getText().toString());
                String paramas="?p1="+jsonParam.get("user")+"&p2="+jsonParam.get("password");
                String Doninio="http://www.comcop.com.co/persia/authen.php";
                //String Doninio="https://www.comcop.com.co/bikes/login.php";
                cadena=(Doninio+paramas);
                System.out.println(Doninio);
            }
            URL url = new URL(cadena);
            System.out.println(cadena);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Habilita conexion de entrada y salida
            connection.setDoInput(true);
            //System.out.println("384"+connection.getOutputStream());
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.connect();
            //Envio de parametros POST
            os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonParam.toString());
            writer.flush();
            writer.close();
            //Se verifica si fue posible la conexion
            System.out.println();
            int respuesta = connection.getResponseCode();

            //Si fue posible se crea un buffered reader y lo que lee se asigna a result (StringBuilder)
            if (respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(connection.getInputStream()); //Se prepara la cadena de entrada
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  //Se introduce en un buffered reader

                //A continuacion se lee lina por linea el buffered reader y se va asignando al StringBuilder
                while ((linea = reader.readLine()) != null){
                    result=(linea);
                } //UNa vez finalizado tenemos un StringBuilder result que leera el JSONObject

                //Se verifica si se realizo o no la operacion
                respuestaJSON = new JSONObject(result);
                int resultJSON2 = respuestaJSON.getInt("status");
                System.out.println("419 "+resultJSON2);
                if (resultJSON2==(2)) {
                    regreso = "Se debe almacenar la variable y ser accedida por login ";
                    System.out.println("ENCRIPTAR LLAVES DE ACCESO");
                    //ConecionSQLiteHelper conn = new ConecionSQLiteHelper( new MapsActivity(), "db_user4",null,6);
                    //SQLiteDatabase db = conn.getWritableDatabase();


                    ConecionSQLiteHelper conn = new ConecionSQLiteHelper( context, "db_user4",null,6);
                    SQLiteDatabase db = conn.getWritableDatabase();
/*
                    String insert = "INSERT INTO " + Utilities.tblConfiguracion +" (" + Utilities.idconfig + " ,"
                            + Utilities.cache1+ ")"+  " VALUES " +  " ('" + 1 + "','" + result+"'"+ ")";
                    //System.out.println("488+ se ha escrito el fichero en cache");
                    db.execSQL(insert);
                    db.close();

 */

                    //System.out.println("430  Guardando en la base de datos");
                    //Borrar odos los registros de la tabla  Utilities.tblMuestras
                    //Antes de guardar, formatearlo deacuerdo a las ventanas, solo se guarda cada 6 registros
                    //  {*idsub*:*miid*, *padre*:*''felipe''*, *x1*: *c,x,y,z,t,v*,  *x2*: *c,x,y,z,t,v*,  *x3*: *c,x,y,z,t,v* ,*x4*: *c,x,y,z,t,v* , *x5*: *c,x,y,z,t,v* ,  *x6*: *c,x,y,z,t,v*}
                    //db.execSQL(insert);
                    //db.close();
                } else if (resultJSON.equals("1")) {
                    regreso = "316 No se pudo realizar la operacion, favor contactar a soporte tecnico";
                }
            }
            connection.disconnect(); //Se termina la conexion

            flag = true;    //Si realiza completamente el try
            System.out.println(respuesta);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (flag == true){
            //No code here
        }else if (flag == false){
            regreso = "No se pudo realizar la operacion, contacte a soporte tecnico";
        }
        System.gc();
        System.out.println("TENSMOS====================\n===================\n"+regreso);
        return regreso; //Devuelve una cadena indicando si la operacion fue exitosa o no
    }
}
