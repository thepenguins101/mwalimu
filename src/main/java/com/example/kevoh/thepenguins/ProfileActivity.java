package com.example.kevoh.thepenguins;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView a,b,c,d,e,f;
    CircleImageView g;
    String url;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        CircleImageView circleImageView = (CircleImageView) findViewById(R.id.studentdp);
        a = (TextView) findViewById(R.id.text_student_name);
        b = (TextView) findViewById(R.id.text_student_id);
        c = (TextView) findViewById(R.id.text_student_fee);
        d = (TextView) findViewById(R.id.text_student_fee_paid);
        e = (TextView) findViewById(R.id.text_student_fee_due);
        f = (TextView) findViewById(R.id.text_student_college);

    }

    class Download extends AsyncTask<Void,Void,Void>
    {
        public String name,id,fee,feePaid,feeDue,college;
        @Override
        protected Void doInBackground(Void... params) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> params  = new HashMap<>();
                    params.put("ID","ID");
                    return params;
                }
            };
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("Please Wait");
            progressDialog.setTitle("Loading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.show();

        }
    }
}
