package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.io.Serializable;
import java.util.Date;

/** 主要描述：布控规则表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class Rule implements Serializable{
    private static final long serialVersionUID = 1L;
    //布控规则ID
    private Long ruleId;

    //布控规则名称

    private String ruleName;

    //布控区域
    private String ruleArea;

    //创建时间
    private Date ruleCreateTime;

    //过期时间
    private Date ruleExpireTime;

    //布控类型
    private String ruleType;

    //布控目标MAC
    private String targetMac;

    //状态：00 - 有效，01 - 无效
    private String status;

    //用户ID
    private Long userId;

    //接受手机
    private String phone;

    //备注
    private String remark;


    public Date getRuleExpireTime() {
        return ruleExpireTime;
    }

    public void setRuleExpireTime(Date ruleExpireTime) {
        this.ruleExpireTime = ruleExpireTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getRuleArea() {
        return ruleArea;
    }

    public void setRuleArea(String ruleArea) {
        this.ruleArea = ruleArea == null ? null : ruleArea.trim();
    }

    public Date getRuleCreateTime() {
        return ruleCreateTime;
    }

    public void setRuleCreateTime(Date ruleCreateTime) {
        this.ruleCreateTime = ruleCreateTime;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public String getTargetMac() {
        return targetMac;
    }

    public void setTargetMac(String targetMac) {
        this.targetMac = targetMac == null ? null : targetMac.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}