package com.example.claficados.ui.newthing;

public class NewThingVoMain {
    //private String UrlThingMain;
    private String name_main_photo;
    private String NewThingCost;
    private String StarsNewThingMain;
    private String QualificationNewThingMain;
    private String TituleDescriptionThingnewMain;
    private String DescriptionNewthingMain;
    private String Reviwes;


    public NewThingVoMain(){

    }

    public NewThingVoMain(String name_main_photo, String newThingCost, String starsNewThingMain, String qualificationNewThingMain, String tituleDescriptionThingnewMain, String descriptionNewthingMain, String reviwes) {
        this.name_main_photo = name_main_photo;
        NewThingCost = newThingCost;
        StarsNewThingMain = starsNewThingMain;
        QualificationNewThingMain = qualificationNewThingMain;
        TituleDescriptionThingnewMain = tituleDescriptionThingnewMain;
        DescriptionNewthingMain = descriptionNewthingMain;
        Reviwes = reviwes;
    }

    public String getReviwes() {
        return Reviwes;
    }

    public void setReviwes(String reviwes) {
        Reviwes = reviwes;
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
