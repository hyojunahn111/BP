package com.example.recyclerex2_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray= jsonObject.getJSONArray("");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                id.add(userData.getString("id"));
                title.add(userData.getString("title"));
                description.add(userData.getString("description"));
                image.add(userData.getString("image"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MainAdapter mainAdapter = new MainAdapter(id,title,description,image,MainActivity.this);
        recyclerView.setAdapter(mainAdapter);
    }

    private String JsonDataFromAsset() {

        String json = null;
        try {
            InputStream inputStream = getAssets().open("trash.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;

    }
}