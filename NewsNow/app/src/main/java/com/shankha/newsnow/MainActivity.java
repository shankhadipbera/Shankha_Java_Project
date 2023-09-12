package com.shankha.newsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    Button b1,b2,b3,b4,b5,b6,b7;

    SearchView searchView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Loading News \n Please Wait");
        progressDialog.show();

        b1=findViewById(R.id.btn_business);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn_entertainment);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn_general);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn_health);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn_science);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn_sports);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn_technology);
        b7.setOnClickListener(this);

        searchView=findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager =new RequestManager(MainActivity.this);
                manager.getNewsHeadline(listener,"general",query);
                progressDialog.setTitle("Loading News of "+query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        RequestManager manager =new RequestManager(this);
        manager.getNewsHeadline(listener,"general",null);

    }
    private  final OnFetchDataListener<NewsApiResponse> listener =new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void OnfetchData(List<NewsHeadlines> list, String message) {
            if(!list.isEmpty()){
                showNews(list);
                progressDialog.dismiss();
            }else{
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recy_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        customAdapter=new CustomAdapter(this,list, this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void OnNewsClick(NewsHeadlines headlines) {

        startActivity(new Intent(MainActivity.this,DetailsActivity.class).putExtra("data",headlines));
    }

    @Override
    public void onClick(View v) {
        Button button= (Button) v;
        String catagory= button.getText().toString();
        progressDialog.setTitle("Loading News of "+catagory);
        progressDialog.show();
        RequestManager manager =new RequestManager(this);
        manager.getNewsHeadline(listener,catagory,null);

    }
}