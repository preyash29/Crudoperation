package com.example.user.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,String>> Arlyst;

    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> arlyst) {
        this.context = context;
        Arlyst = arlyst;
    }

    @Override
    public int getCount() {
        return Arlyst.size();
    }

    @Override
    public Object getItem(int i) {
        return Arlyst.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.list,viewGroup,false);
        }

        TextView t1=(TextView)view.findViewById(R.id.t1);
        TextView t2=(TextView)view.findViewById(R.id.t2);
        TextView t3=(TextView)view.findViewById(R.id.t3);
        TextView t4=(TextView)view.findViewById(R.id.t4);

        HashMap<String,String> map=Arlyst.get(i);
        t1.setText(map.get("ename"));
        t2.setText(map.get("salary"));
        t3.setText(map.get("designation"));
        t4.setText(map.get("department"));
        return view;
    }
}
