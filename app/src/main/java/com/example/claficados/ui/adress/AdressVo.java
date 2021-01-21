package com.example.claficados.ui.adress;

public class AdressVo {

    private String addressVo;
    private String CityVo;
    private String ZoneVo;
    boolean isSelected;
    public AdressVo() {

    }

    public AdressVo(String addressVo, String cityVo, String zoneVo) {
        this.addressVo = addressVo;
        CityVo = cityVo;
        ZoneVo = zoneVo;

    }

    public String getAddressVo() {
        return addressVo;
    }

    public void setAddressVo(String addressVo) {
        this.addressVo = addressVo;
    }

    public String getCityVo() {
        return CityVo;
    }

    public void setCityVo(String cityVo) {
        CityVo = cityVo;
    }

    public String getZoneVo() {
        return ZoneVo;
    }

    public void setZoneVo(String zoneVo) {
        ZoneVo = zoneVo;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

