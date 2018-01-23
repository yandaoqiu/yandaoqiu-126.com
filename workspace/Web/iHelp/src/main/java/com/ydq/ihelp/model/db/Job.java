package com.ydq.ihelp.model.db;

public class Job{
    private Integer id;

    private String jobId;

    private String title;

    private String subc;

    private String location;

    private Integer status;

    private String address;

    private String price;

    private String unit;

    private Integer type;

    private String dateTime;

    private Integer needPeople;

    private Integer level;

    private Integer effDay;

    private Integer ageDown;

    private Integer ageUp;

    private Integer gender;

    private String reson;

    private String pic;
    
    private int job_range;
    
    public Integer getJob_range() {
        return job_range;
    }

    public void setJob_range(Integer job_range) {
        this.job_range = job_range;
    }

    
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubc() {
        return subc;
    }

    public void setSubc(String subc) {
        this.subc = subc == null ? null : subc.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime == null ? null : dateTime.trim();
    }

    public Integer getNeedPeople() {
        return needPeople;
    }

    public void setNeedPeople(Integer needPeople) {
        this.needPeople = needPeople;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getEffDay() {
        return effDay;
    }

    public void setEffDay(Integer effDay) {
        this.effDay = effDay;
    }

    public Integer getAgeDown() {
        return ageDown;
    }

    public void setAgeDown(Integer ageDown) {
        this.ageDown = ageDown;
    }

    public Integer getAgeUp() {
        return ageUp;
    }

    public void setAgeUp(Integer ageUp) {
        this.ageUp = ageUp;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson == null ? null : reson.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}