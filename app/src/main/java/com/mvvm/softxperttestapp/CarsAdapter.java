package com.mvvm.softxperttestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    private List<Car> carList;
    Context context;


    public static class CarsViewHolder extends RecyclerView.ViewHolder {

        private TextView id , band , constraction_year , is_used;
        private ImageView car_img;


        public CarsViewHolder(View v) {
            super(v);

            band = v.findViewById(R.id.car_item_brand_txt);
            constraction_year = v.findViewById(R.id.car_item_constraction_year_txt);
            is_used = v.findViewById(R.id.car_item_is_used_txt);
            car_img = v.findViewById(R.id.car_item_img);
        }

    }

    public CarsAdapter(List<Car> carList, Context context) {
        this.carList = carList;
        this.context = context;

    }

    @Override
    public void onBindViewHolder(CarsViewHolder holder, int position) {

        Car car = carList.get(position);

        if (car.getBrand()!=null) holder.band.setText("Car brand :" + car.getBrand());
        if (car.getConstraction_year()!=null) holder.constraction_year.setText("Car constraction year :" + car.getConstraction_year());
        if (car.getIs_used()!=null) holder.is_used.setText("Car status :" +car.getIs_used());

        if (car.getImg_url()!=null) Glide.with(context).load(car.getImg_url()).into(holder.car_img);

    }

    @Override
    public CarsAdapter.CarsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarsViewHolder(view);

    }


    @Override
    public int getItemCount() {
        return carList.size();
    }
}