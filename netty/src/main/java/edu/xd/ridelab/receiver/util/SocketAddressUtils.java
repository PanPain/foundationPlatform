package edu.xd.ridelab.receiver.util;

import java.net.SocketAddress;

/**
 * @author PanTeng
 * @Description 将SocketAddress 转换成 String
 * @file SocketAddressUtils.java
 * @date 2018/5/11
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class SocketAddressUtils {

    public static String parseIp(SocketAddress address){
        return parseIpAndPort(address)._1;
    }

    private static Tuple2<String, Integer> parseIpAndPort(SocketAddress address) {
        return parseIpAndPort(address.toString());
    }

    /**
     * @description
     * @author PanTeng
     * @date 下午1:32  2018/5/11
     * @param  "host/192.168.0.1:12121"
     * @return （ip, port)
     */
    private static Tuple2<String,Integer> parseIpAndPort(String address) {
        final String ipAndPortString = address.substring(address.indexOf("/") + 1);
        final int indexOfSep = ipAndPortString.lastIndexOf(":");
        final String ip = ipAndPortString.substring(0, indexOfSep);
        final String port = ipAndPortString.substring(indexOfSep + 1);
        return Tuple2.of(ip, Integer.parseInt(port));
    }
}
