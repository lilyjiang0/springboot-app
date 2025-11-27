package com.learnspring.aopdemo.dao;

import com.learnspring.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String name;

    private String account;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work: adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": do work");

        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": get name");

        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": set name");

        this.name = name;
    }

    public String getAccount() {
        System.out.println(getClass() + ": get account");

        return account;
    }

    public void setAccount(String account) {
        System.out.println(getClass() + ": set account");

        this.account = account;
    }
}
