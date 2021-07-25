package com.example.recyclerviewex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int i = 0;


    String[] items = new String[]{"Java", "JavaScript", "Python", "C", "C++", "Rust", "Go", "Ruby", "Kotlin", "Scala"};



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.rcv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();



        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);




        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        try {
                            i++;

                            MainData mainData = new MainData(i + "  " + items[i - 1]);
                            arrayList.add(mainData);
                            mainAdapter.notifyDataSetChanged();

                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }


            }
        });
    }
}