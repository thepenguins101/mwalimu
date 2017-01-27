package com.example.kevoh.thepenguins;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register_students extends AppCompatActivity {

    EditText collegename,location,exambody,email,phonenumber,slots,fees,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_students);

        collegename = (EditText)findViewById(R.id.college_name);
        location = (EditText)findViewById(R.id.location);
        exambody = (EditText)findViewById(R.id.exam_body);
        email = (EditText)findViewById(R.id.email);
        phonenumber = (EditText)findViewById(R.id.phone);
        slots = (EditText)findViewById(R.id.slots);
        fees = (EditText)findViewById(R.id.fees);
        desc = (EditText)findViewById(R.id.desc);
    }

    public void register(View view)
    {
        String url = "http://192.168.43.56/college_info.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.trim().equals("success")) {

                            Toast.makeText(Register_students.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register_students.this,MainActivity.class));
                        } else {
                            Toast.makeText(Register_students.this, s, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", collegename.getText().toString());
                params.put("locate", location.getText().toString());
                params.put("exam", exambody.getText().toString());
                params.put("email", email.getText().toString());
                params.put("phone", phonenumber.getText().toString());
                params.put("slots", slots.getText().toString());
                params.put("desc", desc.getText().toString());
                params.put("fees",fees.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(Register_students.this).add(request);
        startActivity(new Intent(Register_students.this,Main2Activity.class));
    }
}


