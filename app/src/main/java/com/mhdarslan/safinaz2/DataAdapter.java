package com.mhdarslan.safinaz2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context mContext;
    private List<Data> mData;

    FirebaseUser fuser;

    public DataAdapter(Context mContext, List<Data> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.data_item,parent,false);
        return new DataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        Data data = mData.get(position);

        holder.category_data.setText(data.getCategory());
        holder.product_data.setText(data.getProduct());
        holder.price_data.setText(data.getPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category_data, product_data, price_data;
        public ViewHolder(View itemView) {
            super(itemView);
            category_data = itemView.findViewById(R.id.category_data);
            product_data = itemView.findViewById(R.id.product_data);
            price_data = itemView.findViewById(R.id.price_data);
        }
    }
}
