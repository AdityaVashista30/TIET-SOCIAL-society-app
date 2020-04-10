package com.example.android.tietsocialback;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;
public class postAdapter extends ArrayAdapter<post> {
    public postAdapter(Context context,int resource, ArrayList<post> objects) {
        super(context, resource, objects);
    }

    public postAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Nullable
    @Override
    public post getItem(int position) {
        return super.getItem(getCount() - position - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_post, parent, false);
        }

        ImageView logoImageView = (ImageView) convertView.findViewById(R.id.soc_logo);
        TextView postTextView = (TextView) convertView.findViewById(R.id.post_content);
        TextView socTextView = (TextView) convertView.findViewById(R.id.soc_name);
        ImageView picImageView = (ImageView) convertView.findViewById(R.id.pic);

        post message = getItem(position);

        postTextView.setText(message.getText());
        socTextView.setText(message.getName());
        Glide.with(picImageView.getContext())
                .load(message.getPhotoUrl())
                .into(picImageView);
        Glide.with(logoImageView.getContext())
                .load(message.getLogoUrl())
                .into(logoImageView);


        return convertView;
    }
}