package com.example.android.tietsocialback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase instance variables
        FirebaseDatabase mFirebaseDatabase;
        final DatabaseReference mMessagesDatabaseReference;
        ChildEventListener mChildEventListener;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("posts");


        ArrayList<post> posts = new ArrayList<post>();

        final postAdapter itemAdapter = new postAdapter(this, R.layout.item_post, posts);
        ListView listView = (ListView) findViewById(R.id.postListView);
        listView.setAdapter(itemAdapter);

        // Initialize progress bar


        mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                post feed = dataSnapshot.getValue(post.class);
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
    public void openAddPost (View view){
        // Do something in response to button click
        Intent i = new Intent(MainActivity.this, AddPost.class);
        startActivity(i);
    }


    public void openInfo (View view){
        // Do something in response to button click
        Intent j = new Intent(MainActivity.this, info.class);
        startActivity(j);
    }
}
