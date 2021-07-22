package com.example.recyclerviewex2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://run.mocky.io/v3/db2895a5-9d1f-4b54-b6a7-c1411d32a0c6";

    List<MainData> mainData;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainData = new ArrayList<>();
        recyclerView = findViewById(R.id.rc);


        GetData getData = new GetData();
        getData.execute();

    }


    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String current= "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){

                        current += (char) data;
                        data = isr.read();

                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("HOMEWORK");

                for (int i = 0; i < jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MainData model = new MainData();
                    model.setId(jsonObject1.getString("id"));
                    model.setTitle(jsonObject1.getString("title"));
                    model.setDescription(jsonObject1.getString("description"));
                    model.setImage(jsonObject1.getString("image"));

                    mainData.add(model);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(mainData);

        }
    }

    private void PutDataIntoRecyclerView(List<MainData> mainData){

        MainAdapter mainAdapter = new MainAdapter(this, mainData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(mainAdapter);

    }


}