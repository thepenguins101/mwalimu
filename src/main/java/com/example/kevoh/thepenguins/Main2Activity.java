package com.example.kevoh.thepenguins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import Adapters.ImageAdapter;

public class Main2Activity extends AppCompatActivity {

    int myposition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                myposition=position;
                if(myposition==0)
                {
                    startActivity(new Intent(Main2Activity.this,Registered_ua.class));
            }
                if(myposition==1)
                {
                    startActivity(new Intent(Main2Activity.this,Fee_payment.class));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();




    }
}