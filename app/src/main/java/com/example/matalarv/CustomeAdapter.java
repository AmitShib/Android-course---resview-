package com.example.matalarv;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataset;

    private ArrayList<DataModel> filteredDataSet;

    public CustomeAdapter(ArrayList<DataModel> dataSet) {
        this.dataset = dataSet;
        this.filteredDataSet = new ArrayList<>(dataSet);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder (View itemView){
            super(itemView);

            textViewName = itemView.findViewById(R.id.textView);
            textViewVersion = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout , parent , false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageView;

        textViewName.setText(filteredDataSet.get(position).getName());
        textViewVersion.setText((filteredDataSet.get(position).getDescription()));
        imageView.setImageResource(filteredDataSet.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return filteredDataSet.size();
    }


    public void filter(String query) {
        filteredDataSet.clear();

        if (query.isEmpty()) {
            filteredDataSet.addAll(dataset);
        } else {
            // Otherwise, filter data based on query
            String filterPattern = query.toLowerCase().trim();
            for (DataModel data : dataset) {
                if (data.getName().toLowerCase().contains(filterPattern)) {
                    filteredDataSet.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }
}
