package edu.xd.ridelab.heartbeat;

import edu.xd.ridelab.receiver.util.PropertiesUtils;

/**
 * @author PanTeng
 * @Description
 * @file Main.java
 * @date 2018/5/19
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class Main {

    private static int port = Integer.parseInt(PropertiesUtils.getProperty("UDPport"));

    public static void main(String[] args) {
        new UdpServerBootstrap().start(port);
    }
}
