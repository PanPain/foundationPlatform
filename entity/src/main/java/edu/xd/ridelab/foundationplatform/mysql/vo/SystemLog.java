package edu.xd.ridelab.foundationplatform.mysql.vo;

import java.util.Date;

/** 主要描述：系统日志表实体类
 * @author 台恩
 * @date 2018/05/07
 * @since 1.0
 */
public class SystemLog {
    //日志主键
    private Long logId;

    /*日志类型
    1 异常
    2 登录
    3 退出
    4 新增
    5 编辑
    6 删除
    7 查询列表
    8 查看明细
    9 其他
    */
    private Integer logType;

    //业务模块名称
    private String module;

    //控制器名称
    private String contoller;

    //方法名
    private String method;

    //操作人员
    private Long fkUserId;

    //操作时间
    private Date date;

    //日志信息
    private String message;

    //日志详细记录
    private String log;

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
        this.module = module == null ? null : module.trim();
    }

    public String getContoller() {
        return contoller;
    }

    public void setContoller(String contoller) {
        this.contoller = contoller == null ? null : contoller.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
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
        this.message = message == null ? null : message.trim();
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }
}