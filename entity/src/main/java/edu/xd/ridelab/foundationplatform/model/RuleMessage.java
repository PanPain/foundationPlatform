package edu.xd.ridelab.foundationplatform.model;

import edu.xd.ridelab.foundationplatform.mysql.vo.Rule;

/**
 * @author :  zf
 * @date :  2018/5/16
 * @since :  Version 1.0
 */
public class RuleMessage {

    //类型 insert：新增，modify：修改，delete：删除
    String modifyType;

    Rule rule;

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }
}
