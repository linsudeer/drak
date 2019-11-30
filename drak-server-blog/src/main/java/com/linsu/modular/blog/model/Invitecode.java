package com.linsu.modular.blog.model;

import java.util.Date;

public class Invitecode {
    private Long oid;

    private String invitecodeCode;

    private Long invitecodeGeneratorId;

    private Long invitecodeUserId;

    private Date invitecodeUseTime;

    private Integer invitecodeStatus;

    private Date invitecodeCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getInvitecodeCode() {
        return invitecodeCode;
    }

    public void setInvitecodeCode(String invitecodeCode) {
        this.invitecodeCode = invitecodeCode == null ? null : invitecodeCode.trim();
    }

    public Long getInvitecodeGeneratorId() {
        return invitecodeGeneratorId;
    }

    public void setInvitecodeGeneratorId(Long invitecodeGeneratorId) {
        this.invitecodeGeneratorId = invitecodeGeneratorId;
    }

    public Long getInvitecodeUserId() {
        return invitecodeUserId;
    }

    public void setInvitecodeUserId(Long invitecodeUserId) {
        this.invitecodeUserId = invitecodeUserId;
    }

    public Date getInvitecodeUseTime() {
        return invitecodeUseTime;
    }

    public void setInvitecodeUseTime(Date invitecodeUseTime) {
        this.invitecodeUseTime = invitecodeUseTime;
    }

    public Integer getInvitecodeStatus() {
        return invitecodeStatus;
    }

    public void setInvitecodeStatus(Integer invitecodeStatus) {
        this.invitecodeStatus = invitecodeStatus;
    }

    public Date getInvitecodeCreateTime() {
        return invitecodeCreateTime;
    }

    public void setInvitecodeCreateTime(Date invitecodeCreateTime) {
        this.invitecodeCreateTime = invitecodeCreateTime;
    }
}