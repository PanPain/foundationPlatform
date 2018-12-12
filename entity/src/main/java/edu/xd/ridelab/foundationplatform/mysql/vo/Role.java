package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

/** 主要描述：角色表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class Role {

    //角色ID
    private Long roleId;

    //角色名称
    private String roleName;

    //角色描述
    private String roleDesc;

    //创建时间
    private Date createTime;

    //修改时间
    private Date modifyTime;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
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