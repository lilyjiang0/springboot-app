package com.learnspring.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public boolean addMemberAccount() {
        System.out.println(getClass() + ": Doing my db work: adding a membership account");

        return true;
    }

    @Override
    public void getMembershipPoint() {
        System.out.println(getClass() + ": xx points");
    }
}
