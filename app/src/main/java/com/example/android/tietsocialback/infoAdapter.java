package com.example.android.tietsocialback;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class infoAdapter extends ArrayAdapter<SocietyInfo> {
    public infoAdapter(Context context, int resource, ArrayList<SocietyInfo> objects) {
        super(context, resource, objects);
    }

    public infoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_info, parent, false);
        }

        TextView mName = (TextView) convertView.findViewById(R.id.info_name);
        EditText mPresident = (EditText) convertView.findViewById(R.id.info_president);
        EditText mAbout = (EditText) convertView.findViewById(R.id.info_about);
        EditText mGs = (EditText) convertView.findViewById(R.id.info_gs);
        EditText mFs = (EditText) convertView.findViewById(R.id.info_fs);
        EditText mJs = (EditText) convertView.findViewById(R.id.info_js);
        EditText mTh = (EditText) convertView.findViewById(R.id.info_th);
        EditText mCh = (EditText) convertView.findViewById(R.id.info_ch);
        EditText mMh = (EditText) convertView.findViewById(R.id.info_mh);
        EditText mEmh = (EditText) convertView.findViewById(R.id.info_emh);
        EditText mTotal=(EditText) convertView.findViewById(R.id.info_mem);
        String mLogo = "";

        SocietyInfo info = getItem(position);

        mName.setText(info.getName());
        mAbout.setText(info.getAbout());
        mPresident.setText(info.getPresident());
        mGs.setText(info.getGensec());
        mFs.setText(info.getFinsec());
        mJs.setText(info.getJsec());
        mTh.setText(info.getTh());
        mCh.setText(info.getCh());
        mMh.setText(info.getMh());
        mEmh.setText(info.getEh());
        mTotal.setText(info.getTotal());


        return convertView;
    }

}
