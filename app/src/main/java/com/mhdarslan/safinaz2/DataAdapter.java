package com.mhdarslan.safinaz2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    ArrayList<Data> list;

    public DataAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        Data data = list.get(position);
        holder.product.setText(data.getProduct());
        holder.category.setText(data.getCategory());
        holder.price.setText(data.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product, category, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product = itemView.findViewById(R.id.product);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
        }
    }
}
