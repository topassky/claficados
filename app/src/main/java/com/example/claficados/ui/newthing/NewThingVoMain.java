package com.example.claficados.ui.newthing;

public class NewThingVoMain {
    private String UrlThingMain;
    private String description_main_photo;
    private String NewThingCost;
    private String StarsNewThingMain;
    private String QualificationNewThingMain;
    private String TituleDescriptionThingnewMain;
    private String DescriptionNewthingMain;

    public NewThingVoMain(String urlThingMain, String description_main_photo, String newThingCost, String starsNewThingMain, String qualificationNewThingMain, String tituleDescriptionThingnewMain, String descriptionNewthingMain) {
        UrlThingMain = urlThingMain;
        this.description_main_photo = description_main_photo;
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

    public String getDescription_main_photo() {
        return description_main_photo;
    }

    public void setDescription_main_photo(String description_main_photo) {
        this.description_main_photo = description_main_photo;
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
