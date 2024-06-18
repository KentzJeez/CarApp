package com.example.carapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.bind(car);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView yearTextView;
        private TextView originTextView;
        private TextView accelerationTextView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            originTextView = itemView.findViewById(R.id.originTextView);
            accelerationTextView = itemView.findViewById(R.id.accelerationTextView);
        }

        public void bind(Car car) {
            nameTextView.setText("Name: " + car.getName());
            yearTextView.setText("Year: " + car.getYear());
            originTextView.setText("Origin: " + car.getOrigin());
            accelerationTextView.setText("Acceleration: " + String.valueOf(car.getAcceleration()));
        }
    }
}