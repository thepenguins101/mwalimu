package com.example.kevoh.thepenguins;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Adapters.CollegesAdapter;
import Adapters.RecyclerItemClickListener;
import Model.CollegeHolder;

/**
 * Created by KeVoH on 1/25/2017.
 */
public class CollegesFragment extends Fragment {

    RecyclerView recyclerView;
    CollegesAdapter adapter;
    ProgressDialog progressDialog;
    public List<CollegeHolder> list;
    private static final String TAG = "HealthNews";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.colleges,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_colleges);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";

        new DownloadTask().execute(url);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                                startActivity(new Intent(getActivity(),CollegeActivity.class));
            }
        }));



        return view;
    }

    public class DownloadTask extends AsyncTask<String,Void,Integer>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getActivity(),"Fetching Data","Please Wait...",false,false);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                //200 represents HTTP OK
                if (statusCode == 200)
                {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null)
                    {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; //Successful
                }
                else
                {
                    result = 0; //Failed to fetch data!
                }
            }catch (Exception e){
                Log.d(TAG,e.getLocalizedMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result == 1)
            {
                adapter = new CollegesAdapter(list,getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(getActivity(), ""+parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
            else
            {
                Toast.makeText(getActivity(),"Failed to fetch data!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String result)
    {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            list = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++)
            {
                JSONObject post = posts.optJSONObject(i);
                CollegeHolder item = new CollegeHolder();
                item.setName(post.optString("title"));
                item.setImage(post.optString("thumbnail"));
                list.add(item);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
