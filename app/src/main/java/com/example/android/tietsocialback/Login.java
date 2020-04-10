package com.example.android.tietsocialback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        }


    public void checkUser(View view) {

        List<String> socIDL=new ArrayList<>();
        List<String> socPASL=new ArrayList<>();
        socIDL.add("Sample1");
        socPASL.add("adi");
        socIDL.add("SEtest");
        socPASL.add("test");

        int flag=0;
        String userStr;
        String passStr;
        EditText userInp=(EditText) findViewById(R.id.loginStr);
        EditText passInp=(EditText) findViewById(R.id.passStr);
        userStr=userInp.getText().toString();
        passStr=passInp.getText().toString();

        for(int i=0;i<2;i++){
            if(userStr.equals(socIDL.get(i)) && passStr.equals(socPASL.get(i))){flag=1;}
        }

        if(flag==1){
            Intent j = new Intent(this, MainActivity.class);
            startActivity(j);
            Toast.makeText(this, "WELCOME",Toast.LENGTH_LONG).show();
        }
        if(flag==0){
            Toast.makeText(this, "INVALID USERNAME/PASSWORD",Toast.LENGTH_LONG).show();

        }

        }



    public void openNewUser(View view) {
        Intent j = new Intent(this, NewUser.class);
        startActivity(j);
    }
}
