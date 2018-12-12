
package edu.xd.ridelab.foundationplatform.definition;

/**
 * 虚拟身份协议类型
 *
 * @author 蔡依婷
 * @date 2018/5/7
 * @since 1.0
 */
public enum VIType {
    天涯论坛("tian-ya-lun-tan"),
    百度贴吧("bai-du-tie-ba"),
    猫扑("mao-pu"),
    网易163邮箱("wang-yi-163"),
    QQ空间("qq-kong-jian"),
    新浪微博("sina-wei-bo"),
    豆瓣("dou-ban"),
    腾讯微博("teng-xun-wei-bo"),
    人人("ren-ren"),
    京东("jing-dong"),
    优酷移动版("you-ku-mobile"),
    京东移动版("jing-dong-mobile"),
    淘宝移动版("tao-bao-mobile"),
    美团移动版("mei-tuan-mobile"),
    爱奇艺移动版("ai-qi-yi-mobile"),
    酷狗移动版("ku-gou-mobile"),
    大众点评移动版("da-zhong-dian-pin-mobile"),
    百度地图移动版("bai-du-di-tu-mobile"),
    暴风影音移动版("bao-feng-ying-yin-mobile"),
    PPTY移动版("PPTV-mobile"),
    酷我音乐移动版("ku-wo-yin-yue-mobile"),
    多米音乐移动版("duo-mi-yin-yue-mobile"),
    腾讯新闻移动版("qq-news-mobile"),
    头条移动版("tou-tiao-mobile"),
    百度音乐移动版("bai-du-yin-yue-mobile"),
    阿里云移动版("ali-yun-mobile"),
    天天动听移动版("tian-tian-dong-ting-mobile"),
    人人移动版("ren-ren-mobile"),
    wifi万能钥匙("wifi-wan-neng-yao-shi"),
    墨迹天气移动版("mo-ji-tian-qi-mobile"),
    好豆菜谱移动版("hao-dou-cai-pu-mobile"),
    乐安全("le-an-quan"),
    乐视移动版("le-shi-mobile"),
    腾讯视频移动版("qq-video-mobile"),
    同花顺移动版("tong-hua-shun-mobile"),
    网易新闻移动版("wang-yi-news-mobile"),
    百度卫士("bai-du-wei-shi"),
    芒果TV移动版("mang-guo-TV-mobile"),
    美图秀秀移动版("mei-tu-xiu-xiu-mobile"),
    百度贴吧移动版("bai-du-tie-ba-mobile"),
    美拍移动版("mei-pai-mobile"),
    _12306火车移动版("12306-mobile"),
    _58同城移动版("58-tong-cheng-mobile"),
    腾讯阅读移动版("qq-yue-du-mobile"),
    三国杀移动版("san-guo-sha-mobile"),
    有道词典移动版("you-dao-ci-dian-mobile"),
    华数视频移动版("hua-shu-tv-mobile"),
    QQ聊天("qq-chat"),
    旺旺聊天("wang-wang-chat"),
    飞信聊天("fetion-chat"),
    SMTP("smtp"),
    POP3("pop3"),
    QQ聊天移动版("qq-chat-mobile"),
    新浪微博移动版("sina-weibo-mobile"),
    腾讯微博移动版("qq-weibo-mobile"),
    唱吧移动版("chang-ba-mobile"),
    滴滴出行("di-di-chu-xing"),
    饿了没移动版("e-le-me-mobile"),
    翼讯("yi-xin"),
    来往("lai-wang"),
    陌陌("mo-mo"),
    微信移动版("wei-xin-mobile"),
    唯品会移动版("wei-pin-hui-mobile"),
    QQ空间移动版("qq-kong-jian-mobile"),
    当当移动版("dang-dang-mobile"),
    一号店移动版("yi-hao-dian-mobile"),
    _139邮箱移动版("139-mail-mobile"),
    支付宝移动版("zhi-fu-bao-mobile"),
    钉钉移动版("ding-ding-mobile");

    /** 协议名称 */
    private String protocol;
    VIType(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 根据协议名称获取虚拟身份类型
     * @param protocol 协议名称
     * @return 虚拟身份类型
     */
    public static VIType getType(String protocol) {
        for (VIType type : VIType.values()) {
            if (type.protocol.equals(protocol)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 获取协议名称
     * @return 协议名称
     */
    public String getProtocol() {
        return this.protocol;
    }

}
