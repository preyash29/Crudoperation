package com.example.user.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbOpenHelper extends SQLiteOpenHelper
{
    Context context;
    SQLiteOpenHelper db;

    public DbOpenHelper(Context context)
    {
        super(context, "empdb", null, 1);
        this.context = context;
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table emptb(eid integer primary key autoincrement,ename text,salary text,designation text,department text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {



    }

    public long inserttemp(String ename,String salary,String designation,String department)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ename",ename);
        cv.put("salary",salary);
        cv.put("designation",designation);
        cv.put("department",department);
        long l=db.insert("emptb",null,cv);
        return l;


    }

    public ArrayList<HashMap<String,String>> displayemp()
    {
        ArrayList<HashMap<String,String>> arylst=new ArrayList<HashMap<String,String>>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from emptb",null);
        while (c.moveToNext())
        {
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("eid",c.getString(c.getColumnIndex("eid")));
            map.put("ename",c.getString(c.getColumnIndex("ename")));
            map.put("salary",c.getString(c.getColumnIndex("salary")));
            map.put("designation",c.getString(c.getColumnIndex("designation")));
            map.put("department",c.getString(c.getColumnIndex("department")));
            arylst.add(map);
        }
        return arylst;
    }
        public long deleteemp(int id)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            long l=db.delete("emptb","eid="+id,null);
            return l;
        }

    public long update(int id,String ename,String salary,String designation,String department)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ename",ename);
        cv.put("salary",salary);
        cv.put("designation",designation);
        cv.put("department",department);
        long l=db.update("emptb",cv,"eid="+id,null);

        return l;


    }
}
