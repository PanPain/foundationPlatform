package edu.xd.ridelab.receiver.util;

import lombok.val;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author PanTeng
 * @version 1.0
 * @file PropertiesUtils.java
 * @date 2018/5/9
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */
public class PropertiesUtils {

    private static Properties properties = new Properties();

    static {
        try {
            val file = new FileInputStream(System.getProperty("user.dir") + "/netty/src/main/resources/netty.properties");
            try {
                properties.load(file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("配置文件错误");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到配置文件");
        }
    }


    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
