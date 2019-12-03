package com.example.connormonson.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<User> {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView col1 = (TextView) convertView.findViewById(R.id.col1);
            ImageView col2 = (ImageView) convertView.findViewById(R.id.col2);
            TextView col3 = (TextView) convertView.findViewById(R.id.col3);

            if (col1 != null) {
                col1.setText(user.getEntry1());
            }
            if (col2 != null) {
                Picasso.with(getContext()).load(user.getEntry2()).into(col2);
                //col2.setText((user.getEntry2()));
            }
            if (col3 != null) {
                col3.setText((user.getEntry3()));
            }
        }

        return convertView;
    }
}
