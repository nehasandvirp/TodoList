package com.example.todolist;

import android.content.Context;
import android.view.Display;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private List<Model> list;

    public ListAdapter(@NonNull Context context, int resource, List<Model> items) {
        super(context, resource,items);
        this.list=items;
    }


}