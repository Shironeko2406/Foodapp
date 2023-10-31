package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.FoodDomain;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemsListener;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<FoodDomain> ListFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<FoodDomain> ListFoodSelected,Context context,ChangeNumberItemsListener changeNumberItemsListener) {
        this.ListFoodSelected = ListFoodSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new CartAdapter.ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position){
        holder.title.setText(ListFoodSelected.get(position).getTitle());
        holder.feeEach.setText("$"+ListFoodSelected.get(position).getFee());
        holder.totalEach.setText("$"+ListFoodSelected.get(position).getFee()*ListFoodSelected.get(position).getNumberInCart());
        holder.num.setText(String.valueOf(ListFoodSelected.get(position).getNumberInCart()));

        Context context =  holder.itemView.getContext();
        int drawableResourceId = context.getResources()
                .getIdentifier(ListFoodSelected.get(position).getPic(),"drawable",
                    context.getPackageName());
        Glide.with(context).load(drawableResourceId).into(holder.img);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(ListFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(ListFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
    }
    @Override
    public int getItemCount(){
        return ListFoodSelected.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView feeEach,totalEach,num;
        ImageView img,plusItem,minusItem;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            img = itemView.findViewById(R.id.imgEach);
            feeEach = itemView.findViewById(R.id.textViewFeeEach);
            totalEach = itemView.findViewById(R.id.textViewTotalEach);
            plusItem = itemView.findViewById(R.id.plusCart);
            minusItem = itemView.findViewById(R.id.minusCart);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
