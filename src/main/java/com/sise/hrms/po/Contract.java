package com.sise.hrms.po;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by holyfrans on 2017/3/9.
 *
 */
@Entity(name = "contracts")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 14)
    private String type;

    @Column(columnDefinition="datetime")
    private Date timeOfContract;

    @Column(columnDefinition="datetime")
    private Date expirationTime;

    @Column
    @Lob
    private String content;

    @OneToOne(mappedBy = "contract")
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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

    public Date getTimeOfContract() {
        return timeOfContract;
    }

    public void setTimeOfContract(Date timeOfContract) {
        this.timeOfContract = timeOfContract;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getFormattimeOfContract(){
        if (timeOfContract != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(timeOfContract);
        }
        return "";
    }
    public String getFormatexpirationTime(){
        if (expirationTime != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(expirationTime);
        }
        return "";
    }
}
