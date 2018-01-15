package com.ydq.ihelp.model.db;

public class Follow {
    private Integer id;

    private String userId;

    private String fUserId;

    private String fTypeId;

    private String fJobId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getfUserId() {
        return fUserId;
    }

    public void setfUserId(String fUserId) {
        this.fUserId = fUserId == null ? null : fUserId.trim();
    }

    public String getfTypeId() {
        return fTypeId;
    }

    public void setfTypeId(String fTypeId) {
        this.fTypeId = fTypeId == null ? null : fTypeId.trim();
    }

    public String getfJobId() {
        return fJobId;
    }

    public void setfJobId(String fJobId) {
        this.fJobId = fJobId == null ? null : fJobId.trim();
    }
}