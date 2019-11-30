package com.linsu.modular.blog.model;

import java.util.Date;

public class LoginLog {
    private Long oid;

    private String logName;

    private Long logUserId;

    private String logSucceed;

    private String logIp;

    private Date logCreateTime;

    private Date logUpdateTime;

    private String logMessage;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName == null ? null : logName.trim();
    }

    public Long getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(Long logUserId) {
        this.logUserId = logUserId;
    }

    public String getLogSucceed() {
        return logSucceed;
    }

    public void setLogSucceed(String logSucceed) {
        this.logSucceed = logSucceed == null ? null : logSucceed.trim();
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp == null ? null : logIp.trim();
    }

    public Date getLogCreateTime() {
        return logCreateTime;
    }

    public void setLogCreateTime(Date logCreateTime) {
        this.logCreateTime = logCreateTime;
    }

    public Date getLogUpdateTime() {
        return logUpdateTime;
    }

    public void setLogUpdateTime(Date logUpdateTime) {
        this.logUpdateTime = logUpdateTime;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage == null ? null : logMessage.trim();
    }
}