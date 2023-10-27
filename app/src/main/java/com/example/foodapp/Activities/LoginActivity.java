package com.example.foodapp.Activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.foodapp.Domain.UserDomain;
import com.example.foodapp.Helper.ManagementCart;
import com.example.foodapp.Helper.ManagementUser;
import com.example.foodapp.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText phone,phoneRegister,password,passwordRegister,nameRegister;
    TextView loginBtn,goLoginBtn,registerBtn,goRegisterBtn;
    ConstraintLayout loginLayout,registerLayout;
    UserDomain userlogged;
    ManagementUser managementUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        managementUser = new ManagementUser(this);

        initView();
    }

    private void initView() {
        phone = findViewById(R.id.phone);
        phoneRegister = findViewById(R.id.phoneregister);
        password = findViewById(R.id.password);
        passwordRegister = findViewById(R.id.passwordregister);
        nameRegister = findViewById(R.id.nameregister);
        loginBtn = findViewById(R.id.loginBtn);
        goLoginBtn = findViewById(R.id.goLogin);
        registerBtn = findViewById(R.id.registerBtn);
        goRegisterBtn = findViewById(R.id.goRegister);
        loginLayout = findViewById(R.id.loginLayout);
        registerLayout = findViewById(R.id.registerLayout);

        goRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
            }
        });
        goLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginLayout.setVisibility(View.VISIBLE);
                registerLayout.setVisibility(View.GONE);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }

    private void Register() {
        String phonetxt = phoneRegister.getText().toString();
        String nametxt = nameRegister.getText().toString();
        String passtxt = password.getText().toString();
        UserDomain newUser = new UserDomain(nametxt,phonetxt,passtxt);
        boolean ok = managementUser.register(newUser);
        if (ok) {
            Intent intent = new Intent(this, MainActivity.class);
            ManagementUser.user = userlogged;
            intent.putExtra("user", userlogged);
            startActivity(intent);
        }
    }

    private void Login() {
        String phonetxt = phone.getText().toString();
        String passtxt = password.getText().toString();
        ArrayList<UserDomain> userList = managementUser.GetUserList();
        boolean ok = false;

        for (UserDomain user :   userList) {
            if(user.getPhone().equals(phonetxt)&&user.getPassword().equals(passtxt)) {
                ok = true;
                userlogged = user;
            }
        }
        if(ok){
            Intent intent = new Intent(this, MainActivity.class);
            ManagementUser.user = userlogged;
            intent.putExtra("user",userlogged);
            startActivity(intent);
            Toast.makeText(this,"Login successfully !",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Wrong phone or password !",Toast.LENGTH_SHORT).show();
        }
    }
}