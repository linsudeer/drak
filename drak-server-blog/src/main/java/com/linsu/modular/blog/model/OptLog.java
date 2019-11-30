package com.linsu.modular.blog.model;

import java.util.Date;

public class OptLog {
    private Long oid;

    private String logType;

    private String logName;

    private Long logUserId;

    private String logClassname;

    private String logMethod;

    private Date logCreateTime;

    private String logSucceed;

    private String logMessage;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
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

    public String getLogClassname() {
        return logClassname;
    }

    public void setLogClassname(String logClassname) {
        this.logClassname = logClassname == null ? null : logClassname.trim();
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod == null ? null : logMethod.trim();
    }

    public Date getLogCreateTime() {
        return logCreateTime;
    }

    public void setLogCreateTime(Date logCreateTime) {
        this.logCreateTime = logCreateTime;
    }

    public String getLogSucceed() {
        return logSucceed;
    }

    public void setLogSucceed(String logSucceed) {
        this.logSucceed = logSucceed == null ? null : logSucceed.trim();
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage == null ? null : logMessage.trim();
    }
}