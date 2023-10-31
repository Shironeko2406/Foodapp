package com.example.foodapp.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Adapter.FoodAdapter;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.Domain.FoodDomain;
import com.example.foodapp.Domain.UserDomain;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Helper.ManagementUser;
import com.example.foodapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoriesList,recyclerViewFood;
    private UserDomain object;
    TextView nametxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        object = ManagementUser.user;
        nametxt = findViewById(R.id.nametxt);
        nametxt.setText("Hi "+object.getName());
        recyclerviewCategory();
        recylerviewFood();
        bottomNavigation();
        checkCart();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    private void recylerviewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFood = findViewById(R.id.view2);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodDomains = new ArrayList<>();
        foodDomains.add(new FoodDomain ("Pepperoni pizza","pizza1","slices pepperoni, mozzarella cheese, fresh oregano",13.0,5,20,1000));
        foodDomains.add(new FoodDomain("Chesse Burger","burger","beef, Gouda Cheese, Special sauce, Lettuce, tomato ",15.0,4,18,1500));
        foodDomains.add(new FoodDomain("Vagetable pizza","pizza3", " olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes",11.0,3,20,2000));

        adapter2 = new FoodAdapter(foodDomains);
        recyclerViewFood.setAdapter(adapter2);

    }
    TextView numInCart;
    private void checkCart() {
        ManagementCart m = new ManagementCart(this);
        ManagementCart.numInCart = m.getNumInCart();
        numInCart = findViewById(R.id.numInCarttxt);
        if(ManagementCart.numInCart != 0){
            numInCart.setVisibility(View.VISIBLE);
            numInCart.setText(ManagementCart.numInCart + "");
        }
    }

    private void recyclerviewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoriesList = findViewById(R.id.view1);
        recyclerViewCategoriesList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza","cat_1"));
        categoryDomains.add(new CategoryDomain("Burger","cat_2"));
        categoryDomains.add(new CategoryDomain("HotDog","cat_3"));
        categoryDomains.add(new CategoryDomain("Drink","cat_4"));
        categoryDomains.add(new CategoryDomain("Donut","cat_5"));

        adapter = new CategoryAdapter(categoryDomains);
        recyclerViewCategoriesList.setAdapter(adapter);

        
    }
}