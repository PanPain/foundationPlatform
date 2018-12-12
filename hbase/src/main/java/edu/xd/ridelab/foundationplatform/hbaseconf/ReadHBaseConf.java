package edu.xd.ridelab.foundationplatform.hbaseconf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载并返回HBase连接方面的配置
 *
 * @author QiHaiyang
 * @date 2018/5/7
 */
public class ReadHBaseConf {

    private static Properties property = null;

    static {

            InputStream file =  ReadHBaseConf.class.getClassLoader().getResourceAsStream("hbaseconf.properties");
            property = new Properties();
            try {
                property.load(file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("未找到配置文件");

            }

    }


    private ReadHBaseConf() {
    }

    static class InnerClass {
        private static ReadHBaseConf readHBaseConf = new ReadHBaseConf();
    }

    /**
     * 根据属性名获取属性的值
     *
     * @param key
     * @return String
     */
    public static String getProperty(String key) {
        return property.getProperty(key);
    }

}
