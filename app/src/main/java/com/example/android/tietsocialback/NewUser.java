package com.example.android.tietsocialback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUser extends AppCompatActivity {
    String PassKey="DEMOKEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        final EditText newUser=(EditText)findViewById(R.id.new_user);
        final EditText newPass=(EditText)findViewById(R.id.new_pass);
        final EditText key=(EditText)findViewById(R.id.passkey);
        final Button mSendButton=(Button) findViewById(R.id.add_log);

        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mMessagesDatabaseReference;
        ChildEventListener mChildEventListener;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Log");
        newUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        newPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(PassKey.equals(key.getText().toString())){
                    LoginClass upLog = new LoginClass(newUser.getText().toString(),newPass.getText().toString());
                    mMessagesDatabaseReference.push().setValue(upLog);
                    // Clear input box
                    newPass.setText("");
                    newUser.setText("");
                    key.setText("");

                    Intent j;
                    j = new Intent(NewUser.this,Login.class);
                    startActivity(j);

                }
                else {
                    Toast.makeText(NewUser.this, "Wrong Passkey",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
