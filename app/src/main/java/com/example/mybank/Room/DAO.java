package com.example.mybank.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DAO {

    @Insert(onConflict = REPLACE)
    public void AccountInsertion(Account account);

    /**
     * trasaction query
     *
     * @param sender_id
     * @param receiver_id
     * @param ammount
     */
    @Query("UPDATE Account SET Cust_balance = CASE " +
            "WHEN id = :sender_id THEN Cust_balance - :ammount " +
            "WHEN id = :receiver_id THEN Cust_balance + :ammount " +
            "ELSE Cust_balance " +
            "END")
    void sendMoney(int sender_id ,int receiver_id, int ammount);

    /**
     * GET ALL DATA QUERY
     *
     * @return
     */
    @Query("SELECT * FROM Account")
    List<Account> getAllAccounts();

    /**
     * get perticular account
     * @param id
     * @return
     */
    @Query("SELECT * FROM Account WHERE id = :id")
    Account getAccount(int id);

    /**
     * get receiver list (list without sender)
     * @param i_d
     * @return
     */
    @Query("SELECT * FROM Account WHERE id != :i_d")
    List<Account> getReceiverList(int i_d);

    /**
     * delete all database
     * @return
     */
    @Query("DELETE FROM Account")
    void DeleteRecords();


}
