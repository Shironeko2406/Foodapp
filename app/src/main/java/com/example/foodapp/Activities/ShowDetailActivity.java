package com.example.foodapp.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.foodapp.Domain.FoodDomain;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.R;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titletxt, feetxt, description, numberOrder, totalPrice, startxt, caloritxt, time;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numOfOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        iniView();
        getBundle();
        checkCart();
        bottomNavigation();
    }
    TextView numInCart;
    private void checkCart() {
        numInCart = findViewById(R.id.numInCarttxt3);
        if(ManagementCart.numInCart != 0){
            numInCart.setVisibility(View.VISIBLE);
            numInCart.setText(ManagementCart.numInCart + "");
        }
    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, CartActivity.class));
            }
        });
    }
    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId)
                .into(picFood);
        titletxt.setText(object.getTitle());
        feetxt.setText("$" + object.getFee());
        description.setText(object.getDescription());
        numberOrder.setText(String.valueOf(numOfOrder));
        caloritxt.setText(object.getCalories() + " calories");
        startxt.setText(object.getStar() + "");
        time.setText(object.getTime() + " minutes");

        totalPrice.setText("$"+Math.round(numOfOrder * object.getFee()));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfOrder = numOfOrder + 1;
                numberOrder.setText(String.valueOf(numOfOrder));
                totalPrice.setText("$"+Math.round(numOfOrder * object.getFee()));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numOfOrder > 1) {
                    numOfOrder = numOfOrder - 1;
                }
                numberOrder.setText(String.valueOf(numOfOrder));
                totalPrice.setText("$"+Math.round(numOfOrder * object.getFee()));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numOfOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void iniView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titletxt = findViewById(R.id.titletxt);
        feetxt = findViewById(R.id.pricetxt);
        description = findViewById(R.id.descriptiontxt);
        numberOrder = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCart);
        minusBtn = findViewById(R.id.minusCart);
        picFood = findViewById(R.id.foodPic);
        totalPrice = findViewById(R.id.totalPrice);
        startxt = findViewById(R.id.startxt);
        time = findViewById(R.id.timetxt);
        caloritxt = findViewById(R.id.calotxt);
    }
}