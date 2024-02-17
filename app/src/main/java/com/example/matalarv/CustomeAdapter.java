package com.example.matalarv;

import android.app.Dialog;
import android.content.Context;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    DataModel clickedItem = filteredDataSet.get(adapterPosition);

                    showPopup(holder.itemView.getContext(), clickedItem);
                }
            }
        });

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
            String filterPattern = query.toLowerCase().trim();
            for (DataModel data : dataset) {
                if (data.getName().toLowerCase().contains(filterPattern)) {
                    filteredDataSet.add(data);
                }
            }
        }

        notifyDataSetChanged();
    }

    private void showPopup(Context context, DataModel data) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.cardlayout);

        TextView textViewName = dialog.findViewById(R.id.textView);
        TextView textViewDescription = dialog.findViewById(R.id.textView2);
        ImageView imageView = dialog.findViewById(R.id.imageView);
        textViewName.setText(data.getName());
        textViewDescription.setText(data.getDescription());
        imageView.setImageResource(data.getImage());

        dialog.show();
    }
}
