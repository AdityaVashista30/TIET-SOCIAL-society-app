package com.example.android.tietsocialback;

public class LoginClass {
    private String name;
    private String password;

    public LoginClass(){}
    public LoginClass(String name,String pass){
        this.name=name;
        this.password=pass;
    }


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }
}