package com.example.android.tietsocialback;

public class post {

    private String text;
    private String name;
    private String logoUrl;
    private String photoUrl;


    public post() {
    }

    public post(String text, String name, String logoUrl,String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.logoUrl=logoUrl;

    }

    public String getText() {
        return text;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getLogoUrl(){
        return logoUrl;
    }
}