package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;
import com.example.foodapp.Domain.FoodDomain;
import com.example.foodapp.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;
    public static int numInCart;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> foodDomains = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int i = 0; i < foodDomains.size(); i++) {
            if(foodDomains.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            foodDomains.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            foodDomains.add(item);
            numInCart ++;
        }
        tinyDB.putListObject("CardListNew",foodDomains);
        Toast.makeText(context,"Added to your card",Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CardListNew");
    }
    public void minusNumberFood (ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItensListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
            numInCart--;
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardListNew", listfood);
        changeNumberItensListener.changed();
    }
    public void plusNumberFood (ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CardListNew", listfood);
        changeNumberItemsListener.changed();
    }
    public int getNumInCart(){
        ArrayList<FoodDomain> foodDomains = tinyDB.getListObject("CardListNew");
        return foodDomains.size();
    }
    public Double getTotalFee() {
        ArrayList<FoodDomain> listfood2 = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood2.size(); i++) {
            fee = fee + (listfood2.get(i).getFee() * listfood2.get(i).getNumberInCart());
        }
        return fee;
    }
}
