package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

/** 主要描述：权限表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class Permission {
    private Long permissionId;

    private String permissionUrl;

    private String permissionDesc;

    private Date createTime;

    private Date modifyTime;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc == null ? null : permissionDesc.trim();
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