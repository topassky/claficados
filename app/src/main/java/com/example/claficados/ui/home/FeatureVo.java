package com.example.claficados.ui.home;

public class FeatureVo {

    private String nombre;
    private String cost;
    private String urlcost;

    public FeatureVo(){

    }

    public FeatureVo(String nombre, String cost, String urlcost) {
        this.nombre = nombre;
        this.cost = cost;
        this.urlcost = urlcost;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUrlcost() {
        return urlcost;
    }

    public void setUrlcost(String urlcost) {
        this.urlcost = urlcost;
    }
}
