package com.example.claficados.ui.thing;

public class ProductsVo {
    private String mNames2;
    private String mImageUrls2;
    private String mDescription2;
    private String mNameBody;
    private String mDescriptionBody;
    private String textAdsTitle;
    private String textAds;



    public ProductsVo(String mNames2, String mImageUrls2, String mDescription2, String mNameBody, String mDescriptionBody, String textAdsTitle, String textAds) {
        this.mNames2 = mNames2;
        this.mImageUrls2 = mImageUrls2;
        this.mDescription2 = mDescription2;
        this.mNameBody = mNameBody;
        this.mDescriptionBody = mDescriptionBody;
        this.textAdsTitle = textAdsTitle;
        this.textAds = textAds;
    }

    public ProductsVo(){

    }

    public String getTextAdsTitle() {
        return textAdsTitle;
    }

    public void setTextAdsTitle(String textAdsTitle) {
        this.textAdsTitle = textAdsTitle;
    }

    public String getTextAds() {
        return textAds;
    }

    public void setTextAds(String textAds) {
        this.textAds = textAds;
    }

    public String getmNameBody() {
        return mNameBody;
    }

    public void setmNameBody(String mNameBody) {
        this.mNameBody = mNameBody;
    }

    public String getmDescriptionBody() {
        return mDescriptionBody;
    }

    public void setmDescriptionBody(String mDescriptionBody) {
        this.mDescriptionBody = mDescriptionBody;
    }

    ////////////
    public String getmNames2() {
        return mNames2;
    }

    public void setmNames2(String mNames2) {
        this.mNames2 = mNames2;
    }

    public String getmImageUrls2() {
        return mImageUrls2;
    }

    public void setmImageUrls2(String mImageUrls2) {
        this.mImageUrls2 = mImageUrls2;
    }

    public String getmDescription2() {
        return mDescription2;
    }

    public void setmDescription2(String mDescription2) {
        this.mDescription2 = mDescription2;
    }
}
