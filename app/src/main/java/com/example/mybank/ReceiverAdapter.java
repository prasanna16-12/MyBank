package com.example.mybank;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

import java.util.List;

public class ReceiverAdapter extends RecyclerView.Adapter<ReceiverAdapter.ViewHolder> {

    private List<Account> accountList;
    private Activity context;
    private MyDataBase dataBase;

    public ReceiverAdapter(List<Account> accountList, Activity context) {
        this.accountList = accountList;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ReceiverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_receiver, parent, false);
        return new ReceiverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiverAdapter.ViewHolder holder, int position) {
        final Account userData = accountList.get(position);
        holder.userName.setText(userData.getName());
        holder.userAccNo.setText(userData.getAcc_no());
        holder.UserIFSC.setText(userData.getIfsc());
        holder.addReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int receiver_id = userData.getId();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(ReceiverListActivity.RECEIVER_ID_KEY,receiver_id);
                context.setResult(Activity.RESULT_OK,resultIntent);
                context.finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public void filterList(List<Account> filterdList) {
        accountList = filterdList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView addReceiver;
        private TextView userName;
        private TextView userAccNo;
        private TextView UserIFSC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addReceiver = itemView.findViewById(R.id.add_receiver);
            userName = itemView.findViewById(R.id.receiver_name);
            userAccNo = itemView.findViewById(R.id.receiver_acc);
            UserIFSC = itemView.findViewById(R.id.receiver_ifsc);
        }
    }
}
