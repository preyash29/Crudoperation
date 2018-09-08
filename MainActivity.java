package com.example.user.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Spinner s1;
    Button b1;
    DbOpenHelper db;
    int flag=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        s1=(Spinner)findViewById(R.id.s1);
        b1=(Button)findViewById(R.id.b1);

        String[] depart={"Select option","Engineering","Sales","Marketing","Planning","HR"};
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,depart);
        s1.setAdapter(adp);
        db=new DbOpenHelper(this);

        Intent i=getIntent();
        flag=i.getIntExtra("key",0);
        final int eid=i.getIntExtra("eid",0);
        if(flag==1)
        {
            e1.setText(i.getStringExtra("ename"));
            e2.setText(i.getStringExtra("salary"));
            e3.setText(i.getStringExtra("designation"));

            for(int j=0;j<s1.getCount();j++)
            {
                if(s1.getItemAtPosition(j).toString().equalsIgnoreCase(i.getStringExtra("department")))
                    s1.setSelection(j);

            }
        }



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(flag==0) {


                    long l = db.inserttemp(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), s1.getSelectedItem().toString());

                    if (1 > 0) {
                        Toast.makeText(getApplicationContext(), "Recored inserted.....", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error occured.......", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    long l = db.update(eid,e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), s1.getSelectedItem().toString());

                    if (1 > 0) {
                        Toast.makeText(getApplicationContext(), "Recored updated.....", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error occured.......", Toast.LENGTH_SHORT).show();

                    }
                }


                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);



                }


        });


    }



}
