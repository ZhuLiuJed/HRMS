package com.sise.hrms.po;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by holyfrans on 2017/3/6.
 * 档案表
 */
@Entity(name = "profiles")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    @Size(max = 20)
    private String profileName;

    @Column
    @Lob
    private String profileContent;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileContent() {
        return profileContent;
    }

    public void setProfileContent(String profileContent) {
        this.profileContent = profileContent;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
