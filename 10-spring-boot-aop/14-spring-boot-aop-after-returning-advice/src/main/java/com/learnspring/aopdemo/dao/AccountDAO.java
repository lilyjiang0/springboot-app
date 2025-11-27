package com.learnspring.aopdemo.dao;

import com.learnspring.aopdemo.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    // Allow us to call getter and setter methods via the accountdao interface.
    public String getName();

    public void setName(String name);

    public String getAccount();

    public void setAccount(String account);

    List<Account> findAccounts();
}
