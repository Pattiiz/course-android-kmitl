package com.thitiphat.espressotest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phatm on 10/29/2017.
 */

public class UserList {

    private List<UserInfo> userList = new ArrayList<>();

    public UserList() {

    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    public void addUserList(UserInfo userList) {
        this.userList.add(userList);
    }

}
