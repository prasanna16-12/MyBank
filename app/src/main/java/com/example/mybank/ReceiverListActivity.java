package com.example.mybank;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

import java.util.ArrayList;
import java.util.List;

public class ReceiverListActivity extends AppCompatActivity {

    public static String RECEIVER_ID_KEY = "receiver_id_key";
    private LinearLayoutManager mlinearLayoutManager;
    private EditText search;
    private ImageView notFound;
    private RecyclerView receiverList;
    private MyDataBase dataBase;
    private List<Account> accountListReceivers = new ArrayList<>();
    private List<Account> receivers = new ArrayList<>();
    private ReceiverAdapter receiverAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_list);

        Intent intent = getIntent();
        int sender_id = intent.getIntExtra(TransactionActivity.SENDER_ID, -1);

        search = findViewById(R.id.editTextTextReceiverName);
        notFound = findViewById(R.id.datanotfound);
        receiverList = findViewById(R.id.receiverList);

        notFound.setVisibility(View.INVISIBLE);
        receiverList.setVisibility(View.VISIBLE);

        dataBase = MyDataBase.getInstance(this);
        accountListReceivers = dataBase.dao().getReceiverList(sender_id);
        System.out.println(""+sender_id);
        //accountListReceivers.remove(accountListReceivers.indexOf(dataBase.dao().getAccount(sender_id)));
        //accountListReceivers.remove(dataBase.dao().getAccount(sender_id));


        mlinearLayoutManager = new LinearLayoutManager(this);
        receiverList.setLayoutManager(mlinearLayoutManager);
        receiverAdapter = new ReceiverAdapter(accountListReceivers, this);
        receiverList.setAdapter(receiverAdapter);






        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                filter(charSequence.toString());
                if (receiverAdapter.getItemCount() == 0){
                    notFound.setVisibility(View.VISIBLE);
                } else {
                    notFound.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    private void filter(String text) {
        List<Account> filterdList = new ArrayList<>();
        for (Account account : accountListReceivers) {
            if (account.getName().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(account);
            }
        }
        receiverAdapter.filterList(filterdList);
    }
}