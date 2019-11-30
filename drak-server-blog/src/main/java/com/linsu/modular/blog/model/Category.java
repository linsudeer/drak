package com.linsu.modular.blog.model;

import java.util.Date;

public class Category {
    private Long oid;

    private String categoryName;

    private Integer categoryStatus;

    private String categoryIcon;

    private Date categoryCreateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon == null ? null : categoryIcon.trim();
    }

    public Date getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(Date categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }
}