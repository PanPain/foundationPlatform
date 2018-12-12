package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

/** 主要描述：用户-角色关联表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class RolePermissionRelation {
    //主键
    private Long relationId;

    //用户ID
    private Long fkRoleId;

    //角色ID
    private Long fkPermissionId;

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

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Long getFkPermissionId() {
        return fkPermissionId;
    }

    public void setFkPermissionId(Long fkPermissionId) {
        this.fkPermissionId = fkPermissionId;
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