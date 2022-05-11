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
    //Отрисовывает xml файл в объект View
    private LayoutInflater inflater;
    private int layout;
    private List<User> users;

    public UserAdapter(Context context, int resource, List<User> users) {
        //Вызов к ArrayAdapter
        super(context, resource, users);
        this.users = users;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        User user = users.get(position);

        viewHolder.imageView.setImageResource(user.getPicResource());
        viewHolder.nameView.setText(user.getName());

        return convertView;
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        ViewHolder(View view){
            imageView = view.findViewById(R.id.pic);
            nameView = view.findViewById(R.id.name);
        }
    }
}
