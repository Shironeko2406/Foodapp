package com.example.foodapp.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Adapter.CartAdapter;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Interface.ChangeNumberItemsListener;
import com.example.foodapp.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        bottomNavigation();
        calculateCart();
        checkCart();
    }
    TextView numInCart;
    private void checkCart() {
        numInCart = findViewById(R.id.numInCarttxt2);
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
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager. VERTICAL, false);
        recyclerViewList.setLayoutManager (linearLayoutManager);
        adapter = new CartAdapter(managementCart.getListCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        calculateCart();
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.textViewTotalFee);
        taxTxt=findViewById(R.id.textViewTax);
        deliveryTxt=findViewById(R.id.textViewDelivery);
        totalTxt=findViewById(R.id.textViewTotal);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.textViewEmpty);
    }
    private void calculateCart(){
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalFee() * percentTax)* 100.0) / 100.0;
        double itemTotal = Math.round (managementCart.getTotalFee()* 100.0) / 100.0;
        if(itemTotal == 0) delivery = 0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        totalFeeTxt.setText("$" + total);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("S" + itemTotal);
    }
}