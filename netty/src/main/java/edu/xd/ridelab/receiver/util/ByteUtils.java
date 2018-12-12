package edu.xd.ridelab.receiver.util;

import lombok.experimental.UtilityClass;
import lombok.experimental.var;
import lombok.val;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utilities for bytes
 *
 * @author PanTeng
 */
@UtilityClass
public class ByteUtils {

    /**
     * 将字节数组转换成int （以4个字节为单位）
     *
     * @author PanTeng
     */
    public int bytesToInt(byte[] src, int offset) {
        return ((src[offset] & 0xFF) << 24)
            | ((src[offset + 1] & 0xFF) << 16)
            | ((src[offset + 2] & 0xFF) << 8)
            | (src[offset + 3] & 0xFF);
    }

    /**
     * int转换成字节数组 （以4个字节为单位）
     *
     * @author PanTeng
     */
    public byte[] intToBytes(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xff);         //最低位
        targets[2] = (byte) ((i >> 8) & 0xff);  //次低位
        targets[1] = (byte) ((i >> 16) & 0xff); //次高位
        targets[0] = (byte) (i >> 24);          // 最高位，无符号右移
        return targets;
    }

    public byte[] concat(byte[]... buffers) {
        val result = new byte[Arrays.stream(buffers).map(x -> x.length).reduce(0, (x, y) -> x + y)];
        int pos = 0;
        for (val buffer : buffers) {
            System.arraycopy(buffer, 0, result, pos, buffer.length);
            pos += buffer.length;
        }
        return result;
    }

    /**
     * 字节数组转换成String
     *
     * @author PanTeng
     */
    public String bytesToString(byte[] bytes){
        String content = null;
        try {
            content = new String(bytes, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return content;
    }

}
