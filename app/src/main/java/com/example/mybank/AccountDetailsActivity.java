package com.example.mybank;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

import java.text.NumberFormat;
import java.util.Locale;

public class AccountDetailsActivity extends AppCompatActivity {
    public static final String SENDR_ID = "sender_id";

    private TextView profileAlphabet;
    private TextView name, email, balance, accountNo, phoneNo, ifscNo;
    private Button send;
    private MyDataBase dataBase;
    private Account currentUser;


    public static String convertCurrancy(int balance) {
        Locale currentLocale = new Locale("en", "IN");
        NumberFormat formater = NumberFormat.getCurrencyInstance(currentLocale);
        return formater.format(balance);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);


        final Intent intent = getIntent();

        final int id = intent.getIntExtra(AccountsAdapter.USER_ID, -1);
        final String nameInitial = intent.getStringExtra(AccountsAdapter.USER_APLABET);
        dataBase = MyDataBase.getInstance(this);
        currentUser = dataBase.dao().getAccount(id);

        profileAlphabet = findViewById(R.id.profileTVAlphabet);
        name = findViewById(R.id.customerName);
        email = findViewById(R.id.customerEmail);
        balance = findViewById(R.id.customerBalance);
        send = findViewById(R.id.sendMoney);
        accountNo = findViewById(R.id.accountNo);
        phoneNo = findViewById(R.id.phoneNo);
        ifscNo = findViewById(R.id.Ifcs_no);

        profileAlphabet.setText(nameInitial);
        name.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        accountNo.setText(currentUser.getAcc_no());
        phoneNo.setText(currentUser.getPhoneNumber());
        ifscNo.setText(currentUser.getIfsc());
        convertCurrancy(currentUser.getBalance());
        balance.setText("Click here");
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog cheackPinDialog = new Dialog(AccountDetailsActivity.this);

                String[] pins = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};


                cheackPinDialog.setContentView(R.layout.dialog);
                final NumberPicker N_P1, N_P2, N_P3, N_P4;
                ImageView check_btn;

                N_P1 = cheackPinDialog.findViewById(R.id.pin_1);
                N_P1.setMinValue(0);
                N_P1.setMaxValue(9);
                N_P1.setDisplayedValues(pins);
                N_P1.setWrapSelectorWheel(false);

                N_P2 = cheackPinDialog.findViewById(R.id.pin_2);
                N_P2.setMinValue(0);
                N_P2.setMaxValue(9);
                N_P2.setDisplayedValues(pins);
                N_P2.setWrapSelectorWheel(false);

                N_P3 = cheackPinDialog.findViewById(R.id.pin_3);
                N_P3.setMinValue(0);
                N_P3.setMaxValue(9);
                N_P3.setDisplayedValues(pins);
                N_P3.setWrapSelectorWheel(false);

                N_P4 = cheackPinDialog.findViewById(R.id.pin_4);
                N_P4.setMinValue(0);
                N_P4.setMaxValue(9);
                N_P4.setDisplayedValues(pins);
                N_P4.setWrapSelectorWheel(false);

                //Toast.makeText(AccountDetailsActivity.this,""+currentUser.getPin(),Toast.LENGTH_SHORT).show();

                check_btn = cheackPinDialog.findViewById(R.id.check_btn);
                check_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userMpin = "" + N_P1.getValue() + N_P2.getValue() + N_P3.getValue() + N_P4.getValue();
//                        Toast.makeText(AccountDetailsActivity.this,""+userMpin,Toast.LENGTH_SHORT).show();
                        if (Integer.toString(currentUser.getPin()).equals(userMpin)) {
                           balance.setText(convertCurrancy(currentUser.getBalance()));
                           balance.setEnabled(false);
                            cheackPinDialog.dismiss();
                        } else {
                            cheackPinDialog.dismiss();
                        }

                    }
                });
                cheackPinDialog.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog cheackPinDialog = new Dialog(AccountDetailsActivity.this);

                String[] pins = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};


                cheackPinDialog.setContentView(R.layout.dialog);
                final NumberPicker N_P1, N_P2, N_P3, N_P4;
                ImageView check_btn;

                N_P1 = cheackPinDialog.findViewById(R.id.pin_1);
                N_P1.setMinValue(0);
                N_P1.setMaxValue(9);
                N_P1.setDisplayedValues(pins);
                N_P1.setWrapSelectorWheel(false);

                N_P2 = cheackPinDialog.findViewById(R.id.pin_2);
                N_P2.setMinValue(0);
                N_P2.setMaxValue(9);
                N_P2.setDisplayedValues(pins);
                N_P2.setWrapSelectorWheel(false);

                N_P3 = cheackPinDialog.findViewById(R.id.pin_3);
                N_P3.setMinValue(0);
                N_P3.setMaxValue(9);
                N_P3.setDisplayedValues(pins);
                N_P3.setWrapSelectorWheel(false);

                N_P4 = cheackPinDialog.findViewById(R.id.pin_4);
                N_P4.setMinValue(0);
                N_P4.setMaxValue(9);
                N_P4.setDisplayedValues(pins);
                N_P4.setWrapSelectorWheel(false);

                check_btn = cheackPinDialog.findViewById(R.id.check_btn);
                check_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userMpin = "" + N_P1.getValue() + N_P2.getValue() + N_P3.getValue() + N_P4.getValue();
                        if (Integer.toString(currentUser.getPin()).equals(userMpin)) {
                            Intent intent = new Intent(AccountDetailsActivity.this, TransactionActivity.class);
                            intent.putExtra(SENDR_ID,id);
                            startActivity(intent);
                            cheackPinDialog.dismiss();
                        } else {
                            cheackPinDialog.dismiss();
                        }

                    }
                });
                cheackPinDialog.show();
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }
}