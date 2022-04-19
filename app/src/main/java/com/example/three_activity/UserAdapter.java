package com.example.three_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;
    private int layout;
    private List<User> users;

    public UserAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
        this.users = users;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.pic);
        TextView nameView = view.findViewById(R.id.name);

        User user = users.get(position);

        flagView.setImageResource(user.getPicResource());
        nameView.setText(user.getName());

        return view;
    }
}
