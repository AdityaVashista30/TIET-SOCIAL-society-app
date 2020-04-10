package com.example.android.tietsocialback;

public class SocietyInfo {
    private String name;
    private String logoUrl;
    private String about ;
    private String president;
    private String gensec;
    private String finsec;
    private String jsec;
    private String mh;
    private String eh;
    private String th;
    private String ch;
    private String total;

    public SocietyInfo(){}

    public SocietyInfo(String name, String about, String logoUrl, String president, String gensec,String finsec,String jsec,String mh,String eh,String ch,String th,String total) {
        this.name=name;
        this.logoUrl=logoUrl;
        this.about=about ;
        this.president=president;
        this.gensec=gensec;
        this.finsec=finsec;
        this.jsec=jsec;
        this.mh=mh;
        this.eh=eh;
        this.th=th;
        this.ch=ch;
        this.total=total;
    }

    public String getAbout() {
        return about;
    }

    public String getFinsec() {
        return finsec;
    }

    public String getJsec() {
        return jsec;
    }

    public String getEh() {
        return eh;
    }

    public String getTh() {
        return th;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getPresident() {
        return president;
    }

    public String getMh() {
        return mh;
    }

    public String getName() {
        return name;
    }

    public String getCh() {
        return ch;
    }

    public String getTotal() {
        return total;
    }

    public String getGensec() {
        return gensec;
    }
}
