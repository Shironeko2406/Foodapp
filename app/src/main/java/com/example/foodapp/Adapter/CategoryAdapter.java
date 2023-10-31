package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String imgUrl ="";
        switch (position){
            case 0:
                imgUrl = "cat_1";
                break;
            case 1:
                imgUrl = "cat_2";
                break;
            case 2:
                imgUrl = "cat_3";
                break;
            case 3:
                imgUrl = "cat_4";
                break;
            case 4:
                imgUrl = "cat_5";
                break;
        }
        Context context =  holder.itemView.getContext();
        int drawableResourceId = context.getResources().getIdentifier(imgUrl,"drawable",context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.categoryImage);
    }
    @Override
    public int getItemCount(){
        return categoryDomains.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryImage;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
