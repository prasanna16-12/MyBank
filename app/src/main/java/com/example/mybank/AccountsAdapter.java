package com.example.mybank;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybank.Room.Account;
import com.example.mybank.Room.MyDataBase;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    public static final String USER_ID = "USER-ID";
    public static final String USER_APLABET = "USER-ALPABET";

    private List<Account> accountList;
    private Activity context;
    private MyDataBase dataBase;

    public AccountsAdapter(List<Account> accountList, Activity context) {
        this.accountList = accountList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_accounts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountsAdapter.ViewHolder holder, int position) {
        final Account userData = accountList.get(position);

        holder.userProfile.setImageResource(R.drawable.circular_drawable_background);
        final String nameInitial = userData.getName().toUpperCase().charAt(0)+"";
        holder.profileText.setText(nameInitial);
        holder.userName.setText(userData.getName());
        holder.userEmail.setText(userData.getEmail());
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pair[] pairs = new Pair[4];
                Object first;
                Object second;
                pairs[0] = new Pair<View,String>(holder.userProfile,"profileTransition");
                pairs[1] = new Pair<View,String>(holder.userName,"nameTransition");
                pairs[2] = new Pair<View,String>(holder.userEmail,"emailTransition");
                pairs[3] = new Pair<View,String>(holder.constraintLayout, "BackgroundTransition");


                Intent intent = new Intent(context,AccountDetailsActivity.class);
                intent.putExtra(USER_ID,userData.getId());
                intent.putExtra(USER_APLABET,nameInitial.toString());

                //intent.putExtra("USER_PIN",userData.getmPin());


                ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(context,pairs);
                context.startActivity(intent,option.toBundle());
            }
        });


    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout constraintLayout;
        private Button details;
        private ImageView userProfile;
        private TextView userName;
        private TextView userEmail;
        private TextView profileText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.view_details);
            userProfile = itemView.findViewById(R.id.cust_profile);
            userName = itemView.findViewById(R.id.cust_Name);
            userEmail = itemView.findViewById(R.id.cust_Email);
            profileText = itemView.findViewById(R.id.profileText);
            constraintLayout = itemView.findViewById(R.id.row_item_card);
        }
    }
}
