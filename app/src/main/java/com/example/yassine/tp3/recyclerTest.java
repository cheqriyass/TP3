package com.example.yassine.tp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class recyclerTest extends AppCompatActivity implements MyAdapter.ListItemClickListener{

    private static int NUM_LIST_ITEMS = 10;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
        recyclerView = (RecyclerView) findViewById(R.id.namesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter(NUM_LIST_ITEMS, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onListItemClick(String name) {
        String message = "you clicked " + name;
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
