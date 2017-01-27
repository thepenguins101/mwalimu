package com.example.kevoh.thepenguins;

/**
 * Created by KeVoH on 1/27/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogInActivity extends
        Activity
{
    private Button collegeEntryButton,student_button;
    private TextView moreInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        collegeEntryButton = (Button) findViewById(R.id.college_log_in);
        student_button= (Button) findViewById(R.id.student_access_button);

        student_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        collegeEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register_students.class));
            }
        });
    }
}