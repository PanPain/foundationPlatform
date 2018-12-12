package edu.xd.ridelab.foundationplatform.model;

import edu.xd.ridelab.foundationplatform.mysql.vo.SystemLog;

import java.util.Date;

/**
 * @author 翟佳豪
 * @date 2018/05/22
 * @since Version 1.0
 */
public class SystemLogInfo {

    //日志id
    private Long logId;

    //日志类型
    private Integer logType;

    //模块名称
    private String module;

    //控制器名称
    private String contoller;

    //方法名
    private String method;

    //操作时间
    private Date date;

    //日志信息
    private String message;

    //日志详情
    private String log;

    //操作人姓名
    private String userName;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getContoller() {
        return contoller;
    }

    public void setContoller(String contoller) {
        this.contoller = contoller;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
