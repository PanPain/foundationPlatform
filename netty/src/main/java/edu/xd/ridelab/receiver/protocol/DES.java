package edu.xd.ridelab.receiver.protocol;

import com.jcraft.jzlib.ZInputStream;
import edu.xd.ridelab.receiver.util.PropertiesUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author PanTeng
 * @Description
 * @file DES.java
 * @date 2018/5/10
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class DES {

    static byte[] key = PropertiesUtils.getProperty("key").getBytes();  //秘钥
    static byte[] iv = PropertiesUtils.getProperty("iv").getBytes();   //初始向量

    static String encoding = "UTF-8";

    /**
     * @description 对协议数据先解密，后解压缩
     * @author PanTeng
     * @date 下午7:50  2018/5/10
     * @param data
     * @return
     */
    public static String decode(byte[] data) throws IOException {

        //解密
        byte[] deByte = DES.CBCDecrypt(data);
        //解压
        byte[] output = UnCompress(deByte);
        //明文字符串
        String outputStr = new String(output, encoding);

        return outputStr;
    }

    /**
     * @description 解压数据
     * @author PanTeng
     * @date 下午8:06  2018/5/10
     * @param object
     * @return
     */
    private static byte[] UnCompress(byte[] object) throws IOException {
        byte[] data = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(object);
            ZInputStream zIn = new ZInputStream(in);
            byte[] buf = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int count = -1;
            int len = 0;
            while ((count = zIn.read(buf, 0, 1024)) != -1) {
                out.write(buf);
                len += count;
            }
            out.flush();
            data = new byte[len];
            System.arraycopy(out.toByteArray(), 0, data, 0, len);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @description DES解密函数
     * @author PanTeng
     * @date 下午7:56  2018/5/10
     * @param  data
     * @return
     */
    private static byte[] CBCDecrypt(byte[] data) {

        try {
            //从原始密匙数据树立一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key);
            //树立一个密匙工厂，用它把DESKeySpec对象转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);
            //若采用NoPadding方式，data长度必须是8的倍数
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
            //用密匙原始化Cipher对象
            IvParameterSpec param = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, param);
            //执行解密操作
            byte[] decrytedData = cipher.doFinal(data);
            return decrytedData;
        } catch (Exception e) {
            System.out.println("DES算法，解密出错！");
            e.printStackTrace();
        }
        return null;
    }
}
