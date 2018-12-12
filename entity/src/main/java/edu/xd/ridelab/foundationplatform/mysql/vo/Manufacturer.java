package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.io.Serializable;

/** 主要描述：厂商表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class Manufacturer implements Serializable{
    private static final long serialVersionUID = 1L;
    //主键
    private Long mfId;

    //厂商名称
    private String mfName;

    //厂商组织机构代码
    private String mfOrgNo;

    //厂商地址
    private String mfAddress;

    //厂商联系人
    private String mfContact;

    //联系电话
    private String phone;

    //电子邮件
    private String email;

    //备注
    private String remark;

    public Long getMfId() {
        return mfId;
    }

    public void setMfId(Long mfId) {
        this.mfId = mfId;
    }

    public String getMfName() {
        return mfName;
    }

    public void setMfName(String mfName) {
        this.mfName = mfName == null ? null : mfName.trim();
    }

    public String getMfOrgNo() {
        return mfOrgNo;
    }

    public void setMfOrgNo(String mfOrgNo) {
        this.mfOrgNo = mfOrgNo == null ? null : mfOrgNo.trim();
    }

    public String getMfAddress() {
        return mfAddress;
    }

    public void setMfAddress(String mfAddress) {
        this.mfAddress = mfAddress == null ? null : mfAddress.trim();
    }

    public String getMfContact() {
        return mfContact;
    }

    public void setMfContact(String mfContact) {
        this.mfContact = mfContact == null ? null : mfContact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
