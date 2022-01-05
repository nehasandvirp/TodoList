package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ConnectionHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "Todo.db";
//    Connection con;
//    String uname,pass,ip,port,database;
//
//    public Connection connectionclass()
//    {
//        ip="192.168.0.102/24";
//        database="Neha1.db";
//        uname="sandvirp";
//        pass="P@ssw0rd";
//        port="1433";
//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Connection connection= null;
//        String ConnectionURL= null;
//        try
//        {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            ConnectionURL="jdbc:jtds:sqlserver://"+ ip +":" + port+";"+database+";user="+uname+";password="+pass+";";
//            connection= (Connection) DriverManager.getConnection(ConnectionURL);
//        }
//        catch (Exception ex){
//            Log.e("ERROR:",ex.getMessage());
//        }
//
//        return  connection;
//    }

    public static final String table = "todolist";

    public ConnectionHelper(Context context) {

        super(context, DATABASE, null, 1);
        Log.e("in", " database created");
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create table todolist(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,title Text,detail Text, Timestamp Text)");

        Log.e("table", "todolist tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("drop table if exists todolist");
    }

    public boolean insertuserdata(String title, String detail, String Timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss ");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("detail", detail);
        contentValues.put(" Timestamp", new SimpleDateFormat("yyyy-MM-dd ").format(new Date()));

        Log.e("values creates", "values created");
        long result = DB.insert(table, null, contentValues);

        if (result == -1) {
            return true;

        } else {
            return false;
        }
    }

    public boolean updateuserdata(String title, String detail,String Timestamp) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("detail", detail);
        contentValues.put("title", title);
        contentValues.put(" Timestamp", new SimpleDateFormat("yyyy-MM-dd ").format(new Date()));
        Cursor cursor = DB.rawQuery("select * from todolist where title=?", new String[]{title});
        Log.e("data inserted", "data succesfully inserted");
//        DB.update(table, contentValues, "title=?", new String[]{"" + title});
//        DB.close();
        if (cursor.getCount() > 0) {
            long result = DB.update("todolist", contentValues, "title=?", new String[]{title});
            if (result == -1) {
                return false;

            } else {
                return true;
            }
        } else {
            return false;
        }

    }
//    public void delete(String userId) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        sqLiteDatabase.delete(table, "userId=?", new String[]{"" + userId});
//    }

    public int deleteuserdata(Integer id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        int result = DB.delete(table, "id=?" ,new String[]{String.valueOf(id)});
        Log.d("deleted: ", ": " + result);
        DB.close();

//        Cursor cursor = DB.rawQuery("select * from todolist where id=?",new String[]{});
//        if(cursor.getCount()>0) {
//            long result = DB.delete("todolist", "id=?", new String[]{});
//            if (result == -1) {
//                return false;
//
//            } else {
//                return true;
//            }
//        }
//        else
//        {
//            return  false;
//        }

        return result;
    }




    public Cursor getdata()
    {
        SQLiteDatabase DB= this.getReadableDatabase();
        String query=" SELECT * FROM "+ table;
        Cursor cursor = DB.rawQuery(query, null);
        Log.d("data size: ", String.valueOf(cursor.getCount()));

        return  cursor ;
    }

    public ArrayList<Model> getlist() {

        ArrayList<Model> arrayList = new ArrayList<>();
        // select all query
        String select_query= "SELECT * FROM " + table;

        SQLiteDatabase db = this .getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);
        Log.d("list size: ", String.valueOf(cursor.getCount()));
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model noteModel = new Model();
                noteModel.setid(Integer.valueOf(String.valueOf(cursor.getInt(0))));
                noteModel.setTitle(cursor.getString(1));
               noteModel.setDetail(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
}