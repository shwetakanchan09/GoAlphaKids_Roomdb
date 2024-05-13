package com.task.goalphakids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{
    Context context;
    List<InfoModel> infoModelList;
    AdapterListener adapterListener;

    public InfoAdapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        this.adapterListener = adapterListener;
        infoModelList = new ArrayList<>();
    }

    public void addUser(InfoModel infoModel){
        infoModelList.add(infoModel);
        notifyDataSetChanged();
    }
    public void clearData(){
        infoModelList.clear();
        notifyDataSetChanged();
    }
    public void removeUser(int position) {
        infoModelList.remove(position);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_info, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
        InfoModel infoModel = infoModelList.get(position);
        holder.name.setText(infoModel.getName());
        holder.email.setText(infoModel.getEmail());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onUpdate(infoModelList.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onDelete(infoModelList.get(position).getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            email = itemView.findViewById(R.id.txtEmail);
            edit = itemView.findViewById(R.id.imgEdit);
            delete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
