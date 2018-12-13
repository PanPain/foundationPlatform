package edu.xd.ridelab.foundationplatform.model;

import lombok.Data;

/**
 * @author 翟佳豪
 * @date 2018/05/24
 * @since Version 1.0
 */
public class ViCountModel {

    private Long qqChat;

    private Long sinaWeiBo;

    private Long weiXinMobile;

    private Long others;

    private Long viCount;

    public Long getQqChat() {
        return qqChat;
    }

    public void setQqChat(Long qqChat) {
        this.qqChat = qqChat;
    }

    public Long getSinaWeiBo() {
        return sinaWeiBo;
    }

    public void setSinaWeiBo(Long sinaWeiBo) {
        this.sinaWeiBo = sinaWeiBo;
    }

    public Long getWeiXinMobile() {
        return weiXinMobile;
    }

    public void setWeiXinMobile(Long weiXinMobile) {
        this.weiXinMobile = weiXinMobile;
    }

    public Long getOthers() {
        return others;
    }

    public void setOthers(Long others) {
        this.others = others;
    }

    public Long getViCount() {
        return viCount;
    }

    public void setViCount(Long viCount) {
        this.viCount = viCount;
    }
}
