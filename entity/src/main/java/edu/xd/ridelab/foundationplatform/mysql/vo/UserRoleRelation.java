package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

/** 主要描述：用户角色关系表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class UserRoleRelation {
    //主键
    private Long relationId;

    //外键，用户ID
    private Long fkUserId;

    //外键，角色ID
    private Long fkRoleId;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}