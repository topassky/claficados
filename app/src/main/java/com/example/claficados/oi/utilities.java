package com.example.claficados.oi;

public class utilities {

    public static String idMuestra = "id";
    public static final String tblmuestra = "hojaVida";
    public static final String nombreUsuario = "usuario";
    public static final String fechainicio = "fechaInico";
    public static final String fechafinal = "fechaFinal";
    public static final String empresa = "Empresa";
    public static final String nombrejefe = "nombreJefe";
    public static final String teljefe = "telJefe";


    public static final String createTable0 = "CREATE TABLE " + tblmuestra + " (" + idMuestra + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            nombreUsuario + " TEXT ," + fechainicio + " TEXT ," + fechafinal + " TEXT ," + empresa + " TEXT ," + nombrejefe +
            " TEXT ," + teljefe + " TEXT)";


    public static final String tblconfiguraciones = "hdgsttsus";
    public static final String idconig = "jjchkc";

    public static final String json = "json";

    public static final String createTable1 = "CREATE TABLE " + tblconfiguraciones + " (" + idconig + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + json + " TEXT)";


    public static String[] urlCover={"http://www.comcop.com.co/persia/imgsApp/logoComcop.png",
            "https://i.redd.it/qn7f9oqu7o501.jpg",
            "https://i.redd.it/j6myfqglup501.jpg",
            "https://i.redd.it/0h2gm1ix6p501.jpg",
            "https://i.redd.it/k98uzl68eh501.jpg",
            "http://www.comcop.com.co/persia/imgsApp/agregar.jpg",
            "http://www.comcop.com.co/persia/imgsApp/agregar.jpg"

    };

    public static String[] urlmNames={"Trondheim",            "Portugal",            "Rocky Mountain National Park",
            "Mahahual",
            "Frozen Lake",
            "Ver más empresas",
            "Hola"

    };

}
