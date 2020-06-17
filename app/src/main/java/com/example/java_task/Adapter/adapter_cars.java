package com.example.java_task.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.java_task.Model.Data;
import com.example.java_task.R;


import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class adapter_cars extends RecyclerView.Adapter<adapter_cars.ViewHolder> {
    private Context context;
    private ArrayList<Data> arrayList;

    public adapter_cars(Context context,ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_car, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(context)
                .load(arrayList.get(i).getImageUrl())
                .into(viewHolder.carImage);

        viewHolder.carName.setText(arrayList.get(i).getBrand());
        if (arrayList.get(i).getIsUsed().equals("true")) {
            viewHolder.txtUsed.setText("is used");
        } else {
            viewHolder.txtUsed.setText("new");
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView carImage;
        TextView carName, txtUsed;

        ViewHolder(View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.carsImage);
            carName = itemView.findViewById(R.id.carName);
            txtUsed = itemView.findViewById(R.id.used);

        }
    }


}

