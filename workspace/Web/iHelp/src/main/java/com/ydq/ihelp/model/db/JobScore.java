package com.ydq.ihelp.model.db;

public class JobScore {
    private Integer id;

    private String jobId;

    private Integer start0;

    private Integer start1;

    private Integer start2;

    private Integer start3;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public Integer getStart0() {
        return start0;
    }

    public void setStart0(Integer start0) {
        this.start0 = start0;
    }

    public Integer getStart1() {
        return start1;
    }

    public void setStart1(Integer start1) {
        this.start1 = start1;
    }

    public Integer getStart2() {
        return start2;
    }

    public void setStart2(Integer start2) {
        this.start2 = start2;
    }

    public Integer getStart3() {
        return start3;
    }

    public void setStart3(Integer start3) {
        this.start3 = start3;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}