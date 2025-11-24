package com.learnspring.aopdemo.dao;

import com.learnspring.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work: adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": do work");

        return false;
    }
}
