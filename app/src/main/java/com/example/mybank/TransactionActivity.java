package com.example.mybank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

public class TransactionActivity extends AppCompatActivity {

    public static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    public static final String SENDER_ID = "sender_id";
    private MyDataBase dataBase;
    private TextView senderName, senderBal, receiverName, receiverAccNo, balanceRemaining;
    private EditText editTextAmount;
    private CardView receiverCard;
    private ImageView changeReceiver, addReceiver, payAmount;
    private Account senderAcc, receiverAcc;
    private int sendableAmount, senderId, receiverId;

    private boolean isMoneySendable, isReceiverAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        final Intent intent = getIntent();
        final int id = intent.getIntExtra(AccountDetailsActivity.SENDR_ID, -1);
        senderId = id;
        bindView();


        dataBase = MyDataBase.getInstance(this);

        senderAcc = dataBase.dao().getAccount(id);
        senderName.setText(senderAcc.getName());
        senderBal.setText(AccountDetailsActivity.convertCurrancy(senderAcc.getBalance()));
        balanceRemaining.setText(AccountDetailsActivity.convertCurrancy(senderAcc.getBalance()).substring(2));

        receiverCard.setVisibility(View.INVISIBLE);
        addReceiver.setVisibility(View.VISIBLE);

        payAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isMoneySendable && isReceiverAvailable) {
                    int sender_id = senderAcc.getId();
                    int receiver_id = receiverAcc.getId();
                    dataBase.dao().sendMoney(sender_id, receiver_id, sendableAmount);
                    Toast.makeText(TransactionActivity.this," Transaction Successful", Toast.LENGTH_LONG).show();
                    updateUI();
                }

            }
        });

        addReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentToReceiverList = new Intent(TransactionActivity.this, ReceiverListActivity.class);
                intentToReceiverList.putExtra(SENDER_ID, senderId);
                startActivityForResult(intentToReceiverList, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        changeReceiver.setVisibility(View.INVISIBLE);
        changeReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentToReceiverList = new Intent(TransactionActivity.this, ReceiverListActivity.class);
                intentToReceiverList.putExtra(SENDER_ID, senderId);
                startActivityForResult(intentToReceiverList, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        editTextAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                int amount;

                if (s.toString().length() < 7){
                    if (s.toString().equals("") || Integer.parseInt(s.toString()) == 0 || Integer.parseInt(s.toString()) > senderAcc.getBalance()) {

                        if (s.toString().equals("") || Integer.parseInt(s.toString()) == 0){
                            balanceRemaining.setText(AccountDetailsActivity.convertCurrancy(senderAcc.getBalance()).substring(2));

                        }else {
                            balanceRemaining.setText("Invalid Amount");
                        }
                        editTextAmount.setTextColor(Color.parseColor("#EC3535"));
                        isMoneySendable = false;
                    } else {

                        amount = Integer.parseInt(s.toString());
                        editTextAmount.setTextColor(Color.parseColor("#27AE60"));
                        String balance_remain = AccountDetailsActivity.convertCurrancy(senderAcc.getBalance() - amount);
                        balanceRemaining.setText(balance_remain.substring(2));
                        isMoneySendable = true;
                        sendableAmount = amount;
                    }
                }else {
                    isMoneySendable =false;
                    balanceRemaining.setText("Invalid Amount");
                }




//
//                int amount;
//
//                if (s.toString().equals("")) {
//
//
//                    amount = 0;
//                    String balance_remain = AccountDetailsActivity.convertCurrancy(senderAcc.getBalance() - amount);
//                    balanceRemaining.setText(balance_remain.substring(2));
//                    isMoneySendable = false;
//
//                } else {
//
//                    if (Integer.parseInt(s.toString()) == 0) {
//
//                        editTextAmount.setTextColor(Color.parseColor("#EC3535"));
//                        amount = 0;
//                        String balance_remain = AccountDetailsActivity.convertCurrancy(senderAcc.getBalance() - amount);
//                        balanceRemaining.setText(balance_remain.substring(2));
//                        isMoneySendable = false;
//
//
//                    } else {
//
//                            amount = Integer.parseInt(s.toString());
//                            if (amount > senderAcc.getBalance()) {
//
//                                editTextAmount.setTextColor(Color.parseColor("#EC3535"));
//                                balanceRemaining.setText("Invalid Amount");
//                                isMoneySendable = false;
//
//                            } else {
//
//                                editTextAmount.setTextColor(Color.parseColor("#27AE60"));
//                                String balance_remain = AccountDetailsActivity.convertCurrancy(senderAcc.getBalance() - amount);
//                                balanceRemaining.setText(balance_remain.substring(2));
//                                isMoneySendable = true;
//                                sendableAmount = amount;
//
//                            }
//                        }
//                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void updateUI() {
        senderAcc = dataBase.dao().getAccount(senderId);
        senderName.setText(senderAcc.getName());
        senderBal.setText(AccountDetailsActivity.convertCurrancy(senderAcc.getBalance()));
        balanceRemaining.setText(AccountDetailsActivity.convertCurrancy(senderAcc.getBalance()).substring(2));
        editTextAmount.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                int receiver_id = data.getIntExtra(ReceiverListActivity.RECEIVER_ID_KEY, -1);
                receiverId = receiver_id;
                receiverAcc = dataBase.dao().getAccount(receiver_id);
                receiverCard.setVisibility(View.VISIBLE);
                addReceiver.setVisibility(View.INVISIBLE);
                changeReceiver.setVisibility(View.VISIBLE);
                receiverName.setText(receiverAcc.getName());
                receiverAccNo.setText(receiverAcc.getAcc_no());
                isReceiverAvailable = true;

            }
            if (resultCode == RESULT_CANCELED) {
                receiverCard.setVisibility(View.INVISIBLE);
                addReceiver.setVisibility(View.VISIBLE);
                changeReceiver.setVisibility(View.INVISIBLE);
                isReceiverAvailable = false;
            }
        }
    }

    private void bindView() {

        senderName = findViewById(R.id.sender_name);
        senderBal = findViewById(R.id.sender_bal);
        receiverCard = findViewById(R.id.receiver_card);
        receiverName = findViewById(R.id.receiver_name);
        receiverAccNo = findViewById(R.id.receiver_acc_no);
        balanceRemaining = findViewById(R.id.balance_remain);
        editTextAmount = findViewById(R.id.editTextAmount);
        changeReceiver = findViewById(R.id.change_receiver_btn);
        addReceiver = findViewById(R.id.add_receiver);
        payAmount = findViewById(R.id.pay_btn);
        isMoneySendable = false;
        isReceiverAvailable = false;

    }
}