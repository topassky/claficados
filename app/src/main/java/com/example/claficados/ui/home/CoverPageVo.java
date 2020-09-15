package com.example.claficados.ui.home;

public class CoverPageVo {




    private String mNames;
    private String mImageUrls;

    public CoverPageVo(String mNames, String mImageUrls) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
    }

    public CoverPageVo() {

    }
    public String getmNames() {
        return mNames;
    }

    public void setmNames(String mNames) {
        this.mNames = mNames;
    }

    public String getmImageUrls() {
        return mImageUrls;
    }

    public void setmImageUrls(String mImageUrls) {
        this.mImageUrls = mImageUrls;
    }

}
