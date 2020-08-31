package com.example.claficados.ui.gallery;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.claficados.MainActivity;
import com.example.claficados.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


public class GalleryFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    File fileImagen;
    Bitmap bitmap;
    Button btnFoto,btnRegistra;
    ProgressDialog progreso;
    EditText campoNom,campoPrecio,campoDescripcion;
    String photo = "photo.jpg";
    RequestQueue request;
    ImageView imgFoto;
    StringRequest stringRequest;
    private final int MIS_PERMISOS = 100;
    private static final int COD_SELECCIONA = 10;
    private static final int COD_FOTO = 20;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnFoto = (Button) view.findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_foto, popup.getMenu());
                popup.setOnMenuItemClickListener(GalleryFragment.this);
                popup.show();


            }
        });

        request= Volley.newRequestQueue(getContext());

        btnRegistra = (Button)view.findViewById(R.id.btnRegistrar);
        btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),"Estoy aqui",Toast.LENGTH_SHORT).show();
                cargarWebService();

            }
        });

        imgFoto = (ImageView) view.findViewById(R.id.imgFoto);

        campoNom = (EditText)view.findViewById(R.id.campoNom);
        campoPrecio = (EditText)view.findViewById(R.id.campoPrecio);
        campoDescripcion = (EditText)view.findViewById(R.id.campoDescripcion);
        //Permisos

        if (solicitaPermisosVersionesSuperiores()) {
            btnFoto.setEnabled(true);
            btnRegistra.setEnabled(true);
        } else {
            btnFoto.setEnabled(false);
            btnRegistra.setEnabled(false);
        }

        try {
            fileImagen = getPhotoFile(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cargarWebService() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        String url="https://www.comcop.com.co/persia/include/wsJSONRegistroMovil.php?";
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")){
                    campoNom.setText("");
                    campoPrecio.setText("");
                    campoDescripcion.setText("");
                    Toast.makeText(getContext(),"Se ha registrado con exito",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String nombre = campoNom.getText().toString().trim();
                String precio = campoPrecio.getText().toString();
                String descripcion = campoDescripcion.getText().toString();

                String imagen=convertirImgString(bitmap);

                Map<String,String> parametros=new HashMap<>();
                parametros.put("nombre",nombre);
                parametros.put("precio",precio);
                parametros.put("descripcion",descripcion);
                parametros.put("imagen",imagen);

                return parametros;
            }
        };
        request.add(stringRequest);
    }

    private String convertirImgString(Bitmap bitmap) {
        ByteArrayOutputStream array=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte=array.toByteArray();
        String imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);

        return imagenString;
    }

    private File getPhotoFile(String photo) throws IOException {
        File storageDictory=getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(photo,".jpg",storageDictory);
    }


    private boolean solicitaPermisosVersionesSuperiores() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){//validamos si estamos en android menor a 6 para no buscar los permisos
            return true;
        }

        //validamos si los permisos ya fueron aceptados
        if((getContext().checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&getContext().checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED){
            return true;
        }


        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MIS_PERMISOS);
        }

        return false;//implementamos el que procesa el evento dependiendo de lo que se defina aqui
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==MIS_PERMISOS){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){//el dos representa los 2 permisos
                Toast.makeText(getContext(),"Permisos aceptados",Toast.LENGTH_SHORT);
                btnFoto.setEnabled(true);
            }
        }else{
            solicitarPermisosManual();
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());//estamos en fragment
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getContext().getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.fotoGaleria:
                abriGaleria();
                return true;
            case R.id.fotoCamara:
                abriCamara();
                return true;
            default:
                return false;
        }

    }


    private void abriCamara() {
        //Intent i = new Intent(getContext(), CamaraActivity.class);
        //startActivity(i);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileImagen);
        Uri fileProvider=FileProvider.getUriForFile(getContext(),"com.example.claficados.fileprovider",fileImagen);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileProvider);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, COD_FOTO);
        }else{
            Toast.makeText(getContext(),"No se encuentra la camara",Toast.LENGTH_SHORT);
        }
    }



    private void abriGaleria() {
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case COD_SELECCIONA:
                try {
                    if(data!=null){
                        Uri miPath=data.getData();
                        imgFoto.setImageURI(miPath);

                        if(miPath!=null){
                        bitmap=MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),miPath);
                        imgFoto.setImageBitmap(bitmap);
                        }

                    }else{
                        Toast.makeText(getContext(),"No se eligio ninguna foto",Toast.LENGTH_SHORT).show();
                    }


                } catch (IOException e) {
                    e.printStackTrace();}

                break;
            case COD_FOTO:
                if (requestCode == COD_FOTO&& resultCode == RESULT_OK) {
                    //Bundle extras = data.getExtras();
                    //Bitmap imageBitmap = (Bitmap) extras.get("data");
                    bitmap = BitmapFactory.decodeFile(fileImagen.getAbsolutePath());
                    imgFoto.setImageBitmap(bitmap);
                }

                //Toast.makeText(getContext(),"Permisos aceptados",Toast.LENGTH_SHORT);
                //bitmap= BitmapFactory.decodeFile(path);
                //imgFoto.setImageBitmap(bitmap);
                break;
        }
    }



}