package com.example.sqllite_b_20200140053;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite_b_20200140053.Adapter.TemanAdapter;
import com.example.sqllite_b_20200140053.Database.DBController;
import com.example.sqllite_b_20200140053.Database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController controler = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TemanBaru.class);
                startActivity(intent);
            }
        });

    }

    public  void BacaData(){
        ArrayList<HashMap<String,String>> daftarTeman = controler.getAllTeman();
        temanArrayList = new ArrayList<>();
        for(int i=0 ; i<daftarTeman.size() ; i++){
            Teman teman = new Teman();
            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelpon(daftarTeman.get(i).get("telpon").toString());
            temanArrayList.add(teman);
        }

    }
}