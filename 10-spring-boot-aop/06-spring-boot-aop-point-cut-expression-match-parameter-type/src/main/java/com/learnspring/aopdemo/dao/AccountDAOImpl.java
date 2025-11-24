package com.learnspring.aopdemo.dao;

import com.learnspring.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + ": Doing my db work: adding an account");
    }
}
