package edu.xd.ridelab.foundationplatform.mysql.vo;

/** 主要描述：用户表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class User {
    //用户ID
    private Long userId;

    //用户账户名
    private String userAccount;

    //用户密码
    private String userPassword;

    //外键，所属单位ID
    private Long fkPoliceOfficeId;

    //用户电话号码
    private String userPhone;

    //用户真实姓名
    private String userRealName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Long getFkPoliceOfficeId() {
        return fkPoliceOfficeId;
    }

    public void setFkPoliceOfficeId(Long fkPoliceOfficeId) {
        this.fkPoliceOfficeId = fkPoliceOfficeId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }
}