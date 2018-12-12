package edu.xd.ridelab.receiver.util;

import lombok.val;

import java.io.*;

/**
 * @author PanTeng
 * @version 1.0
 * @file SerializeUtils.java
 * @date 2018/5/9
 * @attention Copyright (C),2004-2018,SSELab, SEI, XiDian University
 */
public class SerializeUtils {

    /**
     * @param object
     * @return
     * @description 序列化对象为byte数组
     */
    public static byte[] serialize(Object object) {

        try (
            val bos = new ByteArrayOutputStream();
            val oos = new ObjectOutputStream(bos)
        ) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param bytes
     * @return
     * @description byte数组反序列化为对象
     */
    public static Object deserialize(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();

            bais.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
