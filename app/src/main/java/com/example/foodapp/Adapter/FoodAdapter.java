package com.example.foodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.Activities.ShowDetailActivity;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.Domain.FoodDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodDomain> foodDomains;

    public FoodAdapter(ArrayList<FoodDomain> foodDomains) {
        this.foodDomains = foodDomains;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food,parent,false);
        return new FoodAdapter.ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position){
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodDomains.get(position).getFee()));

        Context context =  holder.itemView.getContext();
        int drawableResourceId = context.getResources()
                .getIdentifier(foodDomains.get(position).getPic(),"drawable",
                    context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.img);

        holder.addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",foodDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return foodDomains.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView fee;
        ImageView img,addBtn;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            img = itemView.findViewById(R.id.img);
            addBtn = itemView.findViewById(R.id.adddBtn);

        }
    }
}
