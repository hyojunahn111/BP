package com.example.recyclerex2_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewClass> {

    ArrayList<String> id;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> image;
    Context context;

    public MainAdapter(ArrayList<String> id, ArrayList<String> title, ArrayList<String> description, ArrayList<String> image, Context context) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        MyViewClass myViewClass = new MyViewClass(view);

        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {

        holder.id.setText(id.get(position));
        holder.title.setText(title.get(position));
        holder.description.setText(description.get(position));
        holder.image.setImageResource(Integer.parseInt(image.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder{

        TextView id;
        TextView title;
        TextView description;
        ImageView image;


        public MyViewClass(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
