//package com.example.todolist;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import java.util.List;
//
//public class Responce extends ArrayAdapter<Model> {
//
//
//    List<Model> ImageList;
//
////    public Responce(@NonNull Cursor context, MainActivity resource) {
////        super(context, resource);
////    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        View currentItemView = view;
//        if (currentItemView == null) {
//            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
//        }
//        Model currentNumberPosition = getItem(position);
//        assert currentNumberPosition != null;
//        TextView textView1 = currentItemView.findViewById(R.id.tView);
//        textView1.setText(String.valueOf(currentNumberPosition.getTitle()));
//
//        TextView textView2 = currentItemView.findViewById(R.id.tView2);
//        textView1.setText(String.valueOf(currentNumberPosition.getDate()));
//
////        ListView iv = currentItemView.findViewById(R.id.listItem);
////            iv.setOnItemLongClickListener();
//        return currentItemView;
//    }
//}
