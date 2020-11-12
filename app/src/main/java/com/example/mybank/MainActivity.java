package com.example.mybank;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyDataBase dataBase;
    AccountsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private List<Account> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.accounts_recycler_view);

        dataBase = MyDataBase.getInstance(this);

        /*
                  dataBase.dao().AccountInsertion(new Account("Prasanna Kale","prasanna89kale@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Mark Zuckerberg","prasad@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Bill Gates","yogiraj@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Sunder Pichai","prasanna89kale@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Ganesh Gaitonde","prasad@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Alan Turing","yogiraj@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Jeff Bezoz","prasanna89kale@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Steve Jobs","prasad@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Elon Musk","yogiraj@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Satya Nadella","prasanna89kale@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Jack Ma","prasad@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
                  dataBase.dao().AccountInsertion(new Account("Tim Cook","yogiraj@gmail.com",1010,5000,"7028811590","XXXXXXXX11","CBIN282570"));
         */

        dataList = dataBase.dao().getAllAccounts();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AccountsAdapter(dataList, this);
        recyclerView.setAdapter(adapter);


    }
}