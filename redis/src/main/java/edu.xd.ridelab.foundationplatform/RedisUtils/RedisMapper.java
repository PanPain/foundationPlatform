package edu.xd.ridelab.foundationplatform.RedisUtils;


import edu.xd.ridelab.foundationplatform.RedisPool.RedisUtils;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatistics;
import edu.xd.ridelab.foundationplatform.mysql.vo.RecordPerDayStatisticsKey;
import edu.xd.ridelab.foundationplatform.mysqlAlone.SqlUtils;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.*;

import static edu.xd.ridelab.foundationplatform.RedisUtils.DataTypeUtils.getBooleanFromString;

/**
 * @Author ChenXiang
 * @Date 2018/05/10,19:32
 * <p>
 * redis存储的数据类型和实体类之间的转换
 */
public class RedisMapper {
    Jedis jedis = RedisUtils.getJedis();
    /**
     * @Fields fieldRegex : 字段之间的分隔符，如机具:压缩:AP_MAC
     */
    String fieldRegex = ":";
    /**
     * @Fields format:日期工具
     */
    DateUtil format = null;
    String key = null;
    public RedisMapper(String key) {
        format = new DateUtil();
        this.key = key;
        //jedis.select(2);
    }


    /**
     * @return
     * @methodname toRecordPerDayStatistics
     * @description 将redis中的hash转化为RecordPerDayStatistics实体列表
     * @author ChenXiang
     * @date 20:23,2018/5/10
     * @para
     */
    public List<RecordPerDayStatistics> toRecordPerDayStatistics() throws ParseException {
        //每天只有一个hash，按日期取即可
        System.out.println(format.getNowString() + "来了！！");
        List<RecordPerDayStatistics> entities = new ArrayList<RecordPerDayStatistics>();
        if (jedis.exists(format.getNowString())) {
            System.out.println("开始转换...");
            //总字段数
            int num = jedis.hlen(key).intValue();
            System.out.println("fields.size:" + num);

            /**
             * 这样的输出格式如下：
             * {f6=python, f7=edg, f8=we, f1=hello, f2=java, f3=world, f4=cpp, f5=php}
             * 可以看出，并不会按照插入顺序拿到数据
             */
            Map<String, String> map = jedis.hgetAll(key);
            System.out.println(map);
            List<String> fields = new ArrayList<String>(map.keySet());
            List<String> values = new ArrayList<String>(map.values());
            System.out.println(fields);
            //先把所有的MacNo取出来
            Set<String> macNos = new HashSet<>();
            for (int i = 0; i < num; i++) {
                String[] strings = fields.get(i).split(fieldRegex);
                macNos.add(strings[0]);
            }
            /**
             * 先遍历机具号，再拿每个机具收集的数据
             * 这块效率比较低
             */

            Set<RecordPerDayStatisticsKey> recordPerDayStatisticsKeys = new HashSet<RecordPerDayStatisticsKey>();
            for (int i = 0; i < fields.size(); i++) {
                //每一个单独的字段名
                String string = fields.get(i);
                System.out.println("fields.size()" + fields.size());
                String[] strings = string.split(fieldRegex);
                String macNo = strings[0];
                boolean isCompressed = getBooleanFromString(strings[1]);
                RecordPerDayStatisticsKey recordPerDayStatisticsKey = new RecordPerDayStatisticsKey();
                //RecordPerDayStatistics recordPerDayStatistics = new RecordPerDayStatistics();
                recordPerDayStatisticsKey.setRecordDate(format.getDateByFormat(key));
                recordPerDayStatisticsKey.setMacMachineNo(macNo);
                recordPerDayStatisticsKey.setCompression(isCompressed);
                recordPerDayStatisticsKeys.add(recordPerDayStatisticsKey);
            }
            for (RecordPerDayStatisticsKey recordPerDayStatisticsKey : recordPerDayStatisticsKeys) {
                RecordPerDayStatistics recordPerDayStatistics = new RecordPerDayStatistics(recordPerDayStatisticsKey);
                entities.add(recordPerDayStatistics);
            }

            for (RecordPerDayStatistics recordPerDayStatistics : entities) {
                System.out.println("fields" + fields);
                //System.out.println("keys" + recordPerDayStatisticsKey.getCompression());
                //RecordPerDayStatistics recordPerDayStatistics = new RecordPerDayStatistics(recordPerDayStatisticsKey);
                RecordPerDayStatisticsKey recordPerDayStatisticsKey = new RecordPerDayStatisticsKey(recordPerDayStatistics);
                RecordPerDayStatistics oldRecordPerDayStatistics = SqlUtils.queryRecordPerDayStatisticsFromMysql(recordPerDayStatisticsKey);
                if (oldRecordPerDayStatistics == null) {
                    oldRecordPerDayStatistics = new RecordPerDayStatistics();
                }

                for (String field : fields) {
                    int i = fields.indexOf(field);
                    String[] subField = field.split(fieldRegex);
                    if (recordPerDayStatistics.getMacMachineNo().equals(subField[0]) & recordPerDayStatistics.getCompression().equals(getBooleanFromString(subField[1]))) {
                        String fieldEndsWith = subField[subField.length - 1];
                        switch (fieldEndsWith) {
                            /**
                             * 每一类数据的数量更新都是redis前一段时间监测到的数据量加上从数据库查询到的之前的count值,这个操作已经在spark做好，这里直接取值
                             */
                            case "StationMac":
                                recordPerDayStatistics.setMacCount(Integer.parseInt(values.get(i)));
                                break;
                            case "ApMac":
                                recordPerDayStatistics.setApMacCount(Integer.parseInt(values.get(i)));
                                break;
                            case "tian-ya-lun-tan":
                                recordPerDayStatistics.setTianYaLunTan(Integer.parseInt(values.get(i)));
                                break;
                            case "bai-du-tie-ba":
                                recordPerDayStatistics.setBaiDuTieBa(Integer.parseInt(values.get(i)));
                                break;
                            case "mao-pu":
                                recordPerDayStatistics.setMaoPu(Integer.parseInt(values.get(i)));
                                break;
                            case "wang-yi-163":
                                recordPerDayStatistics.setWangYi163(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-kong-jian":
                                recordPerDayStatistics.setQqKongJian(Integer.parseInt(values.get(i)));
                                break;
                            case "sina-wei-bo":
                                recordPerDayStatistics.setSinaWeiBo(Integer.parseInt(values.get(i)));
                                break;
                            case "dou-ban":
                                recordPerDayStatistics.setDouBan(Integer.parseInt(values.get(i)));
                                break;
                            case "teng-xun-wei-bo":
                                recordPerDayStatistics.setTengXunWeiBo(Integer.parseInt(values.get(i)));
                                break;
                            case "ren-ren":
                                recordPerDayStatistics.setRenRen(Integer.parseInt(values.get(i)));
                                break;
                            case "jing-dong":
                                recordPerDayStatistics.setJingDong(Integer.parseInt(values.get(i)));
                                break;
                            case "you-ku-mobile":
                                recordPerDayStatistics.setYouKuMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "jing-dong-mobile":
                                recordPerDayStatistics.setJingDongMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "tao-bao-mobile":
                                recordPerDayStatistics.setTaoBaoMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "mei-tuan-mobile":
                                recordPerDayStatistics.setMeiTuanMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ai-qi-yi-mobile":
                                recordPerDayStatistics.setAiQiYiMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ku-gou-mobile":
                                recordPerDayStatistics.setKuGouMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "da-zhong-dian-pin-mobile":
                                recordPerDayStatistics.setDaZhongDianPingMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "bai-du-di-tu-mobile":
                                recordPerDayStatistics.setBaiDuDiTuMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "bao-feng-ying-yin-mobile":
                                recordPerDayStatistics.setBaoFengYingYinMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "PPTV-mobile":
                                recordPerDayStatistics.setPptvMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ku-wo-yin-yue-mobile":
                                recordPerDayStatistics.setKuWoYinYueMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "duo-mi-yin-yue-mobile":
                                recordPerDayStatistics.setDuoMiYinYueMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-news-mobile":
                                recordPerDayStatistics.setQqNewsMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "tou-tiao-mobile":
                                recordPerDayStatistics.setTouTiaoMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "bai-du-yin-yue-mobile":
                                recordPerDayStatistics.setBaiDuYinYueMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ali-yun-mobile":
                                recordPerDayStatistics.setAliYunMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "tian-tian-dong-ting-mobile":
                                recordPerDayStatistics.setTianTianDongTingMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ren-ren-mobile":
                                recordPerDayStatistics.setRenRenMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "wifi-wan-neng-yao-shi":
                                recordPerDayStatistics.setWifiWanNengYaoShi(Integer.parseInt(values.get(i)));
                                break;
                            case "mo-ji-tian-qi-mobile":
                                recordPerDayStatistics.setMoJiTianQiMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "hao-dou-cai-pu-mobile":
                                recordPerDayStatistics.setHaoDouCaiPuMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "le-an-quan":
                                recordPerDayStatistics.setLeAnQuan(Integer.parseInt(values.get(i)));
                                break;
                            case "le-shi-mobile":
                                recordPerDayStatistics.setLeShiMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-video-mobile":
                                recordPerDayStatistics.setQqVideoMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "tong-hua-shun-mobile":
                                recordPerDayStatistics.setTongHuaShunMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "wang-yi-news-mobile":
                                recordPerDayStatistics.setWangYiNewsMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "bai-du-wei-shi":
                                recordPerDayStatistics.setBaiDuWeiShi(Integer.parseInt(values.get(i)));
                                break;
                            case "mang-guo-TV-mobile":
                                recordPerDayStatistics.setMangGouTvMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "mei-tu-xiu-xiu-mobile":
                                recordPerDayStatistics.setMeiTuXiuXiuMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "bai-du-tie-ba-mobile":
                                recordPerDayStatistics.setBaiDuTieBaMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "mei-pai-mobile":
                                recordPerDayStatistics.setMeiPaiMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "12306-mobile":
                                recordPerDayStatistics.setTrain12306Mobile(Integer.parseInt(values.get(i)));
                                break;
                            case "58-tong-cheng-mobile":
                                recordPerDayStatistics.setTongCheng58Mobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-yue-du-mobile":
                                recordPerDayStatistics.setQqYueDuMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "san-guo-sha-mobile":
                                recordPerDayStatistics.setSanGuoShaMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "you-dao-ci-dian-mobile":
                                recordPerDayStatistics.setYouDaoCiDianMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "hua-shu-tv-mobile":
                                recordPerDayStatistics.setHuaShuTvMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-chat":
                                recordPerDayStatistics.setQqChat(Integer.parseInt(values.get(i)));
                                break;
                            case "wang-wang-chat":
                                recordPerDayStatistics.setWangWangChat(Integer.parseInt(values.get(i)));
                                break;
                            case "fetion-chat":
                                recordPerDayStatistics.setFetionChat(Integer.parseInt(values.get(i)));
                                break;
                            case "smtp":
                                recordPerDayStatistics.setSmtp(Integer.parseInt(values.get(i)));
                                break;
                            case "pop3":
                                recordPerDayStatistics.setPop3(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-chat-mobile":
                                recordPerDayStatistics.setQqChatMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "sina-weibo-mobile":
                                recordPerDayStatistics.setSinaWeiboMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-weibo-mobile":
                                recordPerDayStatistics.setQqWeiboMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "chang-ba-mobile":
                                recordPerDayStatistics.setChangBaMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "di-di-chu-xing":
                                recordPerDayStatistics.setDiDiChuXing(Integer.parseInt(values.get(i)));
                                break;
                            case "e-le-me-mobile":
                                recordPerDayStatistics.seteLeMeMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "yi-xin":
                                recordPerDayStatistics.setYiXin(Integer.parseInt(values.get(i)));
                                break;
                            case "lai-wang":
                                recordPerDayStatistics.setLaiWang(Integer.parseInt(values.get(i)));
                                break;
                            case "mo-mo":
                                recordPerDayStatistics.setMoMo(Integer.parseInt(values.get(i)));
                                break;
                            case "wei-xin-mobile":
                                recordPerDayStatistics.setWeiXinMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "wei-pin-hui-mobile":
                                recordPerDayStatistics.setWeiPinHuiMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "qq-kong-jian-mobile":
                                recordPerDayStatistics.setQqKongJianMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "dang-dang-mobile":
                                recordPerDayStatistics.setDangDangMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "yi-hao-dian-mobile":
                                recordPerDayStatistics.setYiHaoDianMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "139-mail-mobile":
                                recordPerDayStatistics.setMail139Mobile(Integer.parseInt(values.get(i)));
                                break;
                            case "zhi-fu-bao-mobile":
                                recordPerDayStatistics.setZhiFuBaoMobile(Integer.parseInt(values.get(i)));
                                break;
                            case "ding-ding-mobile":
                                recordPerDayStatistics.setDingDingMobile(Integer.parseInt(values.get(i)));
                                break;
                            default:
                                System.out.println("a wrong field name");
                        }
                    }
                }
            }
        } else {
            System.out.print("no hash key today");
        }
        return entities;
    }
}