package com.example.todolist;

import static java.sql.Types.NULL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class AllList extends ArrayAdapter<Model> {

    public AllList(@NonNull Context context, ArrayList<Model> user  ) {

        super( context,0,user);

    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        // convertView which is recyclable view


        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        }


        Model currentNumberPosition = getItem(position);

        TextView textView1 = currentItemView.findViewById(R.id.tView);
        textView1.setText((currentNumberPosition.getTitle()));


        TextView textView2 = currentItemView.findViewById(R.id.tView2);
        textView2.setText(currentNumberPosition.getDate());

        return currentItemView;
    }
}