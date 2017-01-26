package com.jobcenter.campus.entity;

import java.util.Date;

public class GmPackageHistory {
    private Integer id;

    private Integer gameId;

    private Integer platformId;

    private Integer packageId;

    private String name;

    private Integer versionCode;

    private String versionName;

    private String md5;

    private String downUrl;

    private Integer fileSize;

    private Byte type;

    private String dataPackageIds;

    private Integer chId;

    private Byte modifyType;

    private Date createTime;

    private Date modifyTime;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName == null ? null : versionName.trim();
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl == null ? null : downUrl.trim();
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDataPackageIds() {
        return dataPackageIds;
    }

    public void setDataPackageIds(String dataPackageIds) {
        this.dataPackageIds = dataPackageIds == null ? null : dataPackageIds.trim();
    }

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }

    public Byte getModifyType() {
        return modifyType;
    }

    public void setModifyType(Byte modifyType) {
        this.modifyType = modifyType;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}