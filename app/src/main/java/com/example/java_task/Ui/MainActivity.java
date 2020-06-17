package com.example.java_task.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.example.java_task.Model.Cars;
import com.example.java_task.Model.Data;
import com.example.java_task.Adapter.adapter_cars;
import com.example.java_task.Network.Api;
import com.example.java_task.Network.Service;
import com.example.java_task.R;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerCars;
    private ArrayList<Data> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private adapter_cars adapter_cars;
    private int mPage = 1;
    private boolean isEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onRefresh();
    }

    private void init() {

        /// init view
        recyclerCars = findViewById(R.id.recycleMain);
        swipeRefreshLayout = findViewById(R.id.swipe_container);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerCars.setLayoutManager(linearLayoutManager);
        adapter_cars = new adapter_cars(MainActivity.this, data);
        recyclerCars.setAdapter(adapter_cars);

        getCars(mPage);

        recyclerCars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isEnded) {
                    mPage += 1;
                    getCars(mPage);
                }
            }
        });
    }


    private void onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                swipeRefreshLayout.setRefreshing(true);
                data.clear();
                adapter_cars.notifyDataSetChanged();
                getCars(mPage);
            }
        });
    }

    private void getCars(int page) {
        swipeRefreshLayout.setRefreshing(false);
        Api api = Service.getService().creatRetrofite();
        Call<Cars> carsCall = api.getCars(page);
        carsCall.enqueue(new Callback<Cars>() {
            @Override
            public void onResponse(Call<Cars> call, Response<Cars> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            data.add(response.body().getData().get(i));
                        }
                        setCars();
                    } else {
                        isEnded = true ;
                      // Toast.makeText(MainActivity.this, getResources().getString(R.string.someThingerror), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Cars> call, Throwable t) {
                if (t.getClass().equals(TimeoutError.class)) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.time_out), Toast.LENGTH_SHORT).show();
                } else if (t.getClass().equals(ServerError.class)) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.err_try), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    private void setCars() {
        adapter_cars = new adapter_cars(MainActivity.this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerCars.setLayoutManager(linearLayoutManager);
        recyclerCars.setItemAnimator(new DefaultItemAnimator());
        recyclerCars.setAdapter(adapter_cars);
        adapter_cars.notifyDataSetChanged();

    }

}
