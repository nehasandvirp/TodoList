package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText title,details,date;
    ArrayList<Model> listItem;
    // ArrayList<String> listtitle, listdetail, listdate;
    List<Model> arrayList = new ArrayList<Model>();

    ArrayAdapter adapter;
    ConnectionHelper DB;
    ListView userlist;
    // RecyclerView userlist;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        details=findViewById(R.id.details);
        imageView = findViewById(R.id.fButton);

        userlist = findViewById(R.id.listItem);
        //userlist = findViewById(R.id.recycle);
        DB = new ConnectionHelper(this);
        //listItem = new ArrayList<>();
        // ArrayList<Model> listitem = new ArrayList<Model>();
//        listtitle=new ArrayList<>();
//        listdetail=new ArrayList<>();
//        listdate=new ArrayList<>();

        userlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //final String item =(String)parent.getItemAtPosition(position);
                Model item = listItem.get(position);
               item.getTitle();
                item.getDetail();
                item.getDate();
//                String err = (item.getDetail()==null)?"Details not found":item.getDetail();
//                Log.e("sdcard-err2:", err);
                //Log.d("title ", item.getDetail());


               //Log.e("sizeof", String.valueOf(item));
                Cursor res =DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this," No entery exists",Toast.LENGTH_LONG).show();

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setTitle(item);
//                builder.setView(view);

                builder.setMessage(item.getTitle() + "\n" + item.getDetail() + "\n" + item.getDate());
                builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, Updatel.class);

                       intent.putExtra("title",item.getTitle());
//                        title.setText(""+item.getTitle());
                       // Log.d("title settext ",listItem.get(position).getTitle() );
                        //details.setText(""+listItem.get(position).getDetail());
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = DB.deleteuserdata(listItem.get(position).getid());
                        if (result >0)
                        {
                            Toast.makeText(MainActivity.this, " entery delete", Toast.LENGTH_LONG).show();
                            listItem.remove(listItem);
                            onBackPressed();
                            //adapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, " entery  not delete", Toast.LENGTH_LONG).show();
                        }
//                        Log.d("id", String.valueOf(id));
//                        DB.deleteuserdata(listItem.get(position).getid());
//                        listItem.remove(position);
//                        //notifyDataSetChanged();
////                        boolean checkdeletedata = DB.deleteuserdata((int) id);
////                        if (checkdeletedata == true)
////                            Toast.makeText(MainActivity.this, " entery delete", Toast.LENGTH_LONG).show();
////                        else
////                            Toast.makeText(MainActivity.this, "not deleted", Toast.LENGTH_LONG).show();
//                    }
                    }

                });
                builder.show();

                return true;
            }

        });

//

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Createl.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getdata1();
    }

    private void getdata1() {

        ArrayList<Model> list = new ArrayList<>(DB.getlist());
        if (list != null) {
            //Log.e("found", "" + list.get(1));
        }

        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Show", Toast.LENGTH_SHORT).show();
        } else {
//
            while (cursor.moveToNext()) {

//                adapter= new ArrayAdapter<>(this,R.layout.listview,listItem);
//                userlist.setAdapter(adapter);

        listItem = new ArrayList<>(DB.getlist());
//        userlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                AllList adapter = new AllList(this, listItem);
                userlist.setAdapter(adapter);
//                Log.d("size",String.valueOf(listItem.size()));
//                for (int i = 0; i <listItem.size() ; i++) {
//                    Log.d("element",listItem.get(i).title);
//                }

//            listItem = new ArrayList<Model>(DB.getlist());
//
//            userlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//            userlist.setAdapter(new AllList<Model>(getApplicationContext(), this, listItem));

                //             listItem.add(new AllList<model>(getApplicationContext()));
//               listItem.add(cursor.getString(1));
//
//                    while (cursor.moveToNext()) {
//
            }


//                    AllList all = new AllList(this, listItem);
//                    userlist.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
            //userlist.setAdapter(new AllList(this, android.R.layout.simple_list_item_1, listItem));
            // AllList adapter = new AllList(MainActivity. this, listItem);
//                userlist.setAdapter(adapter);


        }


    }
}