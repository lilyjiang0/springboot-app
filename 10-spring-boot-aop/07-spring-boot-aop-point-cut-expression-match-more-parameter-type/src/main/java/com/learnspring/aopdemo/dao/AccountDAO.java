package com.learnspring.aopdemo.dao;

import com.learnspring.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean vipFlag);
}
