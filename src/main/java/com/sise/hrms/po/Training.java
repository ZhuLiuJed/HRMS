package com.sise.hrms.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by holyfrans on 2017/3/9.
 * 培训表
 */
@Entity(name = "trainings")
public class Training implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12)
    private String type;
    @Column(columnDefinition = "datetime")
    private Date beginTime;
    @Column(columnDefinition = "datetime")
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public String getFormatBeginTime(){
        if (beginTime != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(beginTime);
        }
        return "";
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getFormatEndTime(){
        if (endTime != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(endTime);
        }
        return  "";
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
