package com.example.recyclerviewex2_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context mContext;
    private List<MainData> mData;

    public MainAdapter(Context mContext, List<MainData> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(mData.get(position).getId());
        holder.title.setText(mData.get(position).getTitle());
        holder.description.setText(mData.get(position).getDescription());


        Glide.with(mContext)
                .load(mData.get(position).getImage())
                .load(holder.image);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView title;
        TextView description;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.idtx);
            title = itemView.findViewById(R.id.titletx);
            description = itemView.findViewById(R.id.descriptiontx);
            image = itemView.findViewById(R.id.iv);
        }
    }




}
