package edu.xd.ridelab.receiver;

import edu.xd.ridelab.receiver.util.PropertiesUtils;

/**
 * @author PanTeng
 * @version 1.0
 * @file Main.java
 * @date 2018/5/5
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */

public class Main {

    public static final String VERSION = "2018-05-01";

    private static int port = Integer.parseInt(PropertiesUtils.getProperty("TCPport"));

    public static void main(String[] args) throws InterruptedException {
        // TODO
        System.out.println("****************************");
        System.out.println("    MAC 接受程序（Netty版）   ");
        System.out.println("    版本号：" + VERSION + "  ");
        System.out.println("****************************");
        new TcpServerBootstrap().start(port);
    }

}
