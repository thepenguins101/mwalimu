package com.example.kevoh.thepenguins;

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

public class Fee_payment extends AppCompatActivity {

    EditText stud_id,stud_name,fee_paid,loan_alloc,loan_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_payment);

        stud_id = (EditText)findViewById(R.id.stud_id);
        stud_name = (EditText)findViewById(R.id.stud_name);
        fee_paid = (EditText)findViewById(R.id.fee_paid);
        loan_alloc = (EditText)findViewById(R.id.loan_alloc);
        loan_clear = (EditText)findViewById(R.id.loan_clear);
    }

    public void update(View view)
    {
        String url = "http://192.168.43.56/fee_payment.php";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.trim().equals("success")) {

                            Toast.makeText(Fee_payment.this, "Update Successful", Toast.LENGTH_SHORT).show();
                            stud_id.setText(null);
                            stud_name.setText(null);
                            fee_paid.setText(null);
                            loan_alloc.setText(null);
                            loan_clear.setText(null);
                        } else {
                            Toast.makeText(Fee_payment.this, s, Toast.LENGTH_LONG).show();
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
                params.put("id", stud_id.getText().toString());
                params.put("name", stud_name.getText().toString());
                params.put("feepaid", fee_paid.getText().toString());
                params.put("loan_alloc", loan_alloc.getText().toString());
                params.put("loan_clear", loan_clear.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(Fee_payment.this).add(request);
    }
}
