package com.example.claficados.ui.newthing;

public class NewThingVo {
    private String nombre;
    private String urlImagen;

    public NewThingVo(){

    }

    public NewThingVo(String nombre, String urlImagen) {
        this.nombre = nombre;
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
