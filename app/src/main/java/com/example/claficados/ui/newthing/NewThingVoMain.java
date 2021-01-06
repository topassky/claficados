package com.example.claficados.ui.newthing;

public class NewThingVoMain {
    private String UrlThingMain;
    private String name_main_photo;
    private String NewThingCost;
    private String StarsNewThingMain;
    private String QualificationNewThingMain;
    private String TituleDescriptionThingnewMain;
    private String DescriptionNewthingMain;

    public NewThingVoMain(){

    }

    public NewThingVoMain(String urlThingMain, String name_main_photo, String newThingCost, String starsNewThingMain, String qualificationNewThingMain, String tituleDescriptionThingnewMain, String descriptionNewthingMain) {
        UrlThingMain = urlThingMain;
        this.name_main_photo = name_main_photo;
        NewThingCost = newThingCost;
        StarsNewThingMain = starsNewThingMain;
        QualificationNewThingMain = qualificationNewThingMain;
        TituleDescriptionThingnewMain = tituleDescriptionThingnewMain;
        DescriptionNewthingMain = descriptionNewthingMain;
    }

    public String getUrlThingMain() {
        return UrlThingMain;
    }

    public void setUrlThingMain(String urlThingMain) {
        UrlThingMain = urlThingMain;
    }

    public String getName_main_photo() {
        return name_main_photo;
    }

    public void setName_main_photo(String name_main_photo) {
        this.name_main_photo = name_main_photo;
    }

    public String getNewThingCost() {
        return NewThingCost;
    }

    public void setNewThingCost(String newThingCost) {
        NewThingCost = newThingCost;
    }

    public String getStarsNewThingMain() {
        return StarsNewThingMain;
    }

    public void setStarsNewThingMain(String starsNewThingMain) {
        StarsNewThingMain = starsNewThingMain;
    }

    public String getQualificationNewThingMain() {
        return QualificationNewThingMain;
    }

    public void setQualificationNewThingMain(String qualificationNewThingMain) {
        QualificationNewThingMain = qualificationNewThingMain;
    }

    public String getTituleDescriptionThingnewMain() {
        return TituleDescriptionThingnewMain;
    }

    public void setTituleDescriptionThingnewMain(String tituleDescriptionThingnewMain) {
        TituleDescriptionThingnewMain = tituleDescriptionThingnewMain;
    }

    public String getDescriptionNewthingMain() {
        return DescriptionNewthingMain;
    }

    public void setDescriptionNewthingMain(String descriptionNewthingMain) {
        DescriptionNewthingMain = descriptionNewthingMain;
    }
}
