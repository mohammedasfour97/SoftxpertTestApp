package com.mvvm.softxperttestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mvvm.softxperttestapp.databinding.ActivityMainBinding;
import com.mvvm.softxperttestapp.databinding.ActivityMainBindingImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    Application application;

    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private List<Car> carList;
    private CarsAdapter carsAdapter;

    /// Variables for endless recyclerview

    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int p_n = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //// init layout
        activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        /// Inject the context
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        initRecyclerView();

        // init swiperefresher

        swipeRefreshLayout = activityMainBinding.activityMainSwipeToRefresh;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
            public void onRefresh() {

              carList.clear();
                callCars(1);

                }
            });



        /// call cars
        callCars(1);

    }


    private void initRecyclerView(){

        carList = new ArrayList<>();
        carsAdapter = new CarsAdapter(carList , application);
        recyclerView = activityMainBinding.activityMainRecyclerview;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(carsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                           callCars(p_n);
                            p_n++;
                            loading = true;
                        }
                    }
                }
            }
        });
    }



    private void callCars(int page_num){

        activityMainBinding.mainActivityProgressBar.setVisibility(View.VISIBLE);

        RestApiService apiService = retrofit.create(RestApiService.class);

        Call<CarResponse> call = apiService.getUserList(page_num);
        call.enqueue(new Callback<CarResponse>() {
            @Override
            public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                activityMainBinding.mainActivityProgressBar.setVisibility(View.INVISIBLE);

                int statuscode = response.code();
                List<Car> carList = response.body().getData();
                if (carList!=null)
                MainActivity.this.carList.addAll(carList);
                carsAdapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<CarResponse> call, Throwable t) {

                Log.d("err_ret", t.getMessage().toString());
            }
        });

    }
}