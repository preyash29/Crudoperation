package com.example.user.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    ListView l1;
    ArrayList<HashMap<String,String>> arylst=new ArrayList<HashMap<String, String>>();
    CustomAdapter c;
    DbOpenHelper db;
    int eid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l1=(ListView)findViewById(R.id.l1);

        db=new DbOpenHelper(this);
        arylst=db.displayemp();

        c=new CustomAdapter(this,arylst);
        l1.setAdapter(c);


        registerForContextMenu(l1);




    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,v.getId(),0,"Delete");
        menu.add(0,v.getId(),1,"update");



        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int position=info.position;
        HashMap<String,String> h1=arylst.get(position);

        eid= Integer.parseInt(h1.get("eid"));

        if(item.getTitle()=="Delete")
        {
            Log.d("", "onContextItemSelected: id:"+eid);
             AlertDialog.Builder builder=new AlertDialog.Builder((this));
            builder.setTitle("ALert");
            builder.setMessage("are y sure");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    long l= db.deleteemp(eid);
                    arylst.remove(position);
                    c.notifyDataSetChanged();
                    if(l>0)
                    {
                        Toast.makeText(Main2Activity.this,"Record deleted",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Main2Activity.this,"record error",Toast.LENGTH_SHORT).show();
                    }
                }
            });

                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();





                        }
                    });
            builder.show();
            return true;

        }
        else if(item.getTitle()=="update")
        {
            Intent i=new Intent(Main2Activity.this,MainActivity.class);
            i.putExtra("eid",eid);
            i.putExtra("ename",h1.get("ename"));
            i.putExtra("salary",h1.get("salary"));
            i.putExtra("designation",h1.get("designation"));
            i.putExtra("department",h1.get("department"));
            i.putExtra("key",1);
            startActivity(i);
        }


        return super.onContextItemSelected(item);
    }
}
