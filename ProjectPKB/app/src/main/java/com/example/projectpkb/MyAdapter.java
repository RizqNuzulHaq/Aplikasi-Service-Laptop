package com.example.projectpkb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private ArrayList scode, name, sdate, status;

    public MyAdapter(Context context, ArrayList scode, ArrayList name, ArrayList sdate, ArrayList status) {
        this.context = context;
        this.scode = scode;
        this.name = name;
        this.sdate = sdate;
        this.status = status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.serviceentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.scode_id.setText(String.valueOf(scode.get(position)));
        holder.name_id.setText(String.valueOf(name.get(position)));
        holder.sdate_id.setText(String.valueOf(sdate.get(position)));
        holder.status_id.setText(String.valueOf(status.get(position)));
    }

    @Override
    public int getItemCount() {
        return scode.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView scode_id, name_id, sdate_id, status_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scode_id = itemView.findViewById(R.id.textScode);
            name_id = itemView.findViewById(R.id.textName);
            sdate_id = itemView.findViewById(R.id.textSdate);
            status_id = itemView.findViewById(R.id.textStatus);
        }
    }
}
