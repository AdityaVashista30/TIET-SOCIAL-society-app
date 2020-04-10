package com.example.android.tietsocialback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class info extends AppCompatActivity {


    private static final int RC_PHOTO_PICKER =  2;
    String downloadUrl="";
    // Firebase instance variables
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    ChildEventListener mChildEventListener;

    FirebaseStorage mFirebaseStorage;
    StorageReference mPhotosStorageReference;
    Button mUpload;
    Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mUpload=(Button)findViewById(R.id.logoChange);
        mSave=(Button)findViewById(R.id.saveInfo);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Socities");

        mFirebaseStorage=FirebaseStorage.getInstance();
        mPhotosStorageReference=mFirebaseStorage.getReference().child("photos");

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView mName = (TextView) findViewById(R.id.info_name);
                EditText mPresident = (EditText) findViewById(R.id.info_president);
                EditText mAbout = (EditText) findViewById(R.id.info_about);
                EditText mGs = (EditText) findViewById(R.id.info_gs);
                EditText mFs = (EditText) findViewById(R.id.info_fs);
                EditText mJs = (EditText) findViewById(R.id.info_js);
                EditText mTh = (EditText) findViewById(R.id.info_th);
                EditText mCh = (EditText) findViewById(R.id.info_ch);
                EditText mMh = (EditText) findViewById(R.id.info_mh);
                EditText mEmh = (EditText) findViewById(R.id.info_emh);
                EditText mTotal=(EditText) findViewById(R.id.info_mem);
                String mLogo = downloadUrl;
                SocietyInfo info= new SocietyInfo();
            }
        });


        ArrayList<SocietyInfo> info = new ArrayList<SocietyInfo>();

        final infoAdapter itemAdapter = new infoAdapter(this, R.layout.activity_info, info);


        mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SocietyInfo feed = dataSnapshot.getValue(SocietyInfo.class);
                itemAdapter.add(feed);
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);





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
                            Toast.makeText(info.this, "ATTACHMENT SUCCESFUL",Toast.LENGTH_LONG).show();

                        }
                    });
        }
    }

}
