package edu.xd.ridelab.foundationplatform.service;

import edu.xd.ridelab.foundationplatform.mysql.vo.User;

import javax.servlet.http.HttpSession;

public interface PubService {
    public User userLoginCheck(String account , String password);
}
