package com.example.gpsmarker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Coordinates> coordinatesList;
    private QueryController queryController;


    public RecyclerAdapter(ArrayList<Coordinates> coordinatesList){
        this.coordinatesList = coordinatesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView savedLatTextView;
        private TextView savedLongTextView;
        private ImageButton deleteButton;

        public MyViewHolder(final View view){
            super (view);
            savedLatTextView = view.findViewById(R.id.savedLatTextView);
            savedLongTextView = view.findViewById(R.id.savedLongTextView);
            deleteButton = view.findViewById((R.id.deleteButton));
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View coordinatesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(coordinatesView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String latitude = coordinatesList.get(position).getLatitude();
        String longitude = coordinatesList.get(position).getLongitude();
        holder.savedLatTextView.setText(latitude);
        holder.savedLongTextView.setText(longitude);



        holder.deleteButton.setOnClickListener(v -> {
            queryController = new QueryController();
            queryController.deleteRow(position);
            coordinatesList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return coordinatesList.size();
    }
}
