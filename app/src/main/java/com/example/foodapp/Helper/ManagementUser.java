package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;
import com.example.foodapp.Domain.UserDomain;

import java.util.ArrayList;

public class ManagementUser {
    private Context context;
    private TinyDB tinyDB;
    public static UserDomain user ;
    public ManagementUser(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public boolean register(UserDomain userDomain){
        ArrayList<UserDomain> userDomains = GetUserList();
        boolean existAlready = false;
        int n=0;
        for (int i = 0; i < userDomains.size(); i++) {
            if(userDomains.get(i).getPhone().equals(userDomain.getPhone())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            Toast.makeText(context,"User's Phone already exist !",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            userDomains.add(userDomain);
            tinyDB.putListUser("UserList",userDomains);
            Toast.makeText(context,"Register Successfully !",Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    public ArrayList<UserDomain> GetUserList() {
        return tinyDB.getListUser("UserList");
    }
}
