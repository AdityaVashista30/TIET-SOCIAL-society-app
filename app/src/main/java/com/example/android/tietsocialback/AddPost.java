package com.example.android.tietsocialback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddPost extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER =  2;
    // Firebase instance variables
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    ChildEventListener mChildEventListener;

    FirebaseStorage mFirebaseStorage;
    StorageReference mPhotosStorageReference;


    EditText mInputText;
    Button mSendButton;
    Button mAttachButton;
    String downloadUrl="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        final String ANONYMOUS = "anonymous";
        final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

        mInputText=(EditText) findViewById(R.id.post_input);
        mSendButton=(Button) findViewById(R.id.postButton);
        mAttachButton=(Button)findViewById(R.id.attachButton);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("posts");

        mFirebaseStorage=FirebaseStorage.getInstance();
        mPhotosStorageReference=mFirebaseStorage.getReference().child("photos");

        mInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                }
                else if(downloadUrl.length() > 0){mSendButton.setEnabled(true);}
                else {
                    mSendButton.setEnabled(false);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mInputText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post upPost = new post(mInputText.getText().toString(),"SEtest",null, downloadUrl);
                mMessagesDatabaseReference.push().setValue(upPost);
                // Clear input box
                mInputText.setText("");

                Intent j;
                j = new Intent(AddPost.this,MainActivity.class);
                startActivity(j);
            }
        });

        mAttachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });


    }
    public void addAttachment (View view){
        // Do something in response to button click

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            // Get a reference to store file at chat_photos/<FILENAME>
            StorageReference photoRef = mPhotosStorageReference.child(selectedImageUri.getLastPathSegment());

            // Upload file to Firebase Storage
            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // When the image has successfully uploaded, we get its download URL
                            downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                            Toast.makeText(AddPost.this, "ATTACHMENT SUCCESFUL",Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }
}
