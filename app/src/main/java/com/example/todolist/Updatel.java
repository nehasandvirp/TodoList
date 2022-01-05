package com.example.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Updatel extends AppCompatActivity {
    EditText title;
    EditText details;
    EditText date;
    EditText time;
    Button update,View;
    ArrayList<Model> listItem;
    ConnectionHelper DB;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatel);
        title =findViewById(R.id.title);
        date =findViewById(R.id.date);
//        time =findViewById(R.id.time);
        details =findViewById(R.id.details);
        update= findViewById(R.id.update);
        View=findViewById(R.id.view);
        DB = new ConnectionHelper(this);

        Intent intent=getIntent();
        String t = intent.getStringExtra("title");

        String Title = title.getText().toString();
        String detail = details.getText().toString();
        title.setText(t);

        update.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                title =findViewById(R.id.title);
                String Title = title.getText().toString();
                String detail = details.getText().toString();
                String Date= date.getText().toString();
//                //String Time = time.getText().toString();
//                title.setText(""+listItem.size());

                boolean checkupdatedata = DB.updateuserdata(Title,detail, String.valueOf(date));
                if(checkupdatedata == true) {

                    Toast.makeText(Updatel.this, "entry updated", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                else Toast.makeText(Updatel.this,"not update",Toast.LENGTH_LONG).show();

            }
        });
//        View.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View v) {
//                Cursor res =DB.getdata();
//                if(res.getCount()==0){
//                    Toast.makeText(Updatel.this," No entery exists",Toast.LENGTH_LONG).show();
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext())
//                {
//                    buffer.append("Id:"+res.getString(0)+"\n");
//                    buffer.append("Title:"+res.getString(1)+"\n");
//                    buffer.append("Date:"+res.getString(3)+"\n");
//                    buffer.append("Details:"+res.getString(2)+"\n");
//
//                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(Updatel.this);
//                builder.setCancelable(true);
//                builder.setTitle("user enteries");
//                builder.setMessage(buffer.toString());
//                builder.show();
//            }
//        });

    }
}