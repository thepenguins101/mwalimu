


package com.example.kevoh.thepenguins;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sa90.materialarcmenu.ArcMenu;
import com.sa90.materialarcmenu.StateChangeListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapters.CollegeDetailsAdapter;
import Adapters.CollegesAdapter;
import Model.CollegeDetailsHolder;
import Model.CollegeHolder;

public class CollegeActivity extends AppCompatActivity {

    ArcMenu arcMenu;
    ImageView imageView;
    FloatingActionButton fab1,fab2,fab3;
    ListView listView;
    ProgressDialog progressDialog;
    List<CollegeDetailsHolder> collegeDetailsHolders;
    CollegeDetailsAdapter collegeDetailsAdapter;
    public ArrayList arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);


        fab1 = (FloatingActionButton) findViewById(R.id.fabprofile);
        fab2 = (FloatingActionButton) findViewById(R.id.fabloan);
        fab3 = (FloatingActionButton) findViewById(R.id.fabregister);
        imageView = (ImageView) findViewById(R.id.IvCollege1);
        listView = (ListView) findViewById(R.id.listviewinfo);


        arcMenu = (ArcMenu) findViewById(R.id.arcmenu);
        arcMenu.setStateChangeListener(new StateChangeListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
                arcMenu.toggleMenu();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                arcMenu.toggleMenu();
            }
        });

        //new Download().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    class Download extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {

            String url = "https://api.flickr.com/services/feeds/photos_public.gne?tags=kitten&format=json";


            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try
                    {

                        JSONArray jsonArray = response.getJSONArray("");
                        for(int i=0;i<jsonArray.length();i++)
                        {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String location = jsonObject.getString("location");
                            String email = jsonObject.getString("email");
                            String bio = jsonObject.getString("bio");
                            String contact = jsonObject.getString("Contact");
                            String exam = jsonObject.getString("exam");
                            String fee = jsonObject.getString("fee");
                            String image = jsonObject.getString("image");
                            String [] details = {name,location,bio,contact,email,exam,fee};
                            collegeDetailsAdapter = new CollegeDetailsAdapter(arrayList,getApplicationContext(),details);

                            Picasso.with(getApplicationContext()).load(image).fit().into(imageView);



                        }

                    }catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjectRequest);

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
            progressDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
