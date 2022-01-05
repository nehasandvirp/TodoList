package com.example.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;

public class Createl extends AppCompatActivity {
    EditText title, details, date;
    Button Login,Update,Delete,View;
    ConnectionHelper DB;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createl);
        Login = findViewById(R.id.insert);

        title =findViewById(R.id.title);
        details =findViewById(R.id.details);
        date =findViewById(R.id.date);
//        time =findViewById(R.id.time);


        Login = findViewById(R.id.insert);
       //Update = findViewById(R.id.update);
       Delete = findViewById(R.id.delete);
        View= findViewById(R.id.view);
        DB = new ConnectionHelper(this);



        Login.setOnClickListener(new android.view.View.OnClickListener() {
//            Long timeStamp = System.currentTimeMillis()/1000;
//            String currentTimeStamp = timeStamp.toString();
//             Calendar calendar = Calendar.getInstance();
            @Override

            public void onClick(View v) {
                String Title = title.getText().toString();
                String detail = details.getText().toString();
                String Date= date.getText().toString();

//               String Time = (currentTimeStamp);
//                String dateTime =SimpleDateFormat.format(Calendar.gettime());

                boolean checkinsertdata = DB.insertuserdata(Title,detail,Date );
                if(checkinsertdata == false) {
                    Toast.makeText(Createl.this, "new entery inserted", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                else Toast.makeText(Createl.this,"not inserted",Toast.LENGTH_LONG).show();

            }

        });


//        Update.setOnClickListener(new android.view.View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                String Title = title.getText().toString();
//                String detail = details.getText().toString();
//                String Date= date.getText().toString();
//                //String Time = time.getText().toString();
//
//                boolean checkupdatedata = DB.updateuserdata(Title,detail, String.valueOf(date));
//                if(checkupdatedata == true)
//                    Toast.makeText(Createl.this,"entry updated",Toast.LENGTH_LONG).show();
//                else Toast.makeText(Createl.this,"not update",Toast.LENGTH_LONG).show();
//
//            }
//        });


//
//        Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String Title = title.getText().toString();
//                boolean checkdeletedata = DB.deleteuserdata(Title);
//                if(checkdeletedata == true)
//                    Toast.makeText(Createl.this," entery delete",Toast.LENGTH_LONG).show();
//                else Toast.makeText(Createl.this,"not deleted",Toast.LENGTH_LONG).show();
//
//            }
//        });



        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor res =DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Createl.this," No entery exists",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Id:"+res.getString(0)+"\n");
                    buffer.append("Title:"+res.getString(1)+"\n");
                    buffer.append("Date:"+res.getString(3)+"\n");
                    buffer.append("Details:"+res.getString(2)+"\n");
                    
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Createl.this);
                builder.setCancelable(true);
                builder.setTitle("user enteries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



    }
}