package com.sise.hrms.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by holyfrans on 2017/3/6.
 * 用户信息表
 */
@Entity(name = "user_infos")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @Column(nullable = false, length = 2)
    @NotNull
    private String sex;

    @Column(nullable = false)
    @NotNull
    private Integer age;

    @Column(length = 30)
    private String email;

    @Column(length = 20)
    @Size(max = 20)
    private String phone;

    @Column(length = 40)
    @Size(max = 40)
    private String address;

    @Column(length = 10)
    @Size( max = 10)
    private String education;

    @Column(length = 50)
    private String photoUrl;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private Department dept;

    @Column(columnDefinition="datetime")
    private Date joinedDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    /**
     * 使用StatusConstant类来为其赋值
     */
    @Column(length = 10)
    private String status;

    @Column
    private Float salary;

    @JsonIgnore
    @OneToOne(mappedBy = "userInfo")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public String getFormatJoinedDate() {
        if (joinedDate != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(joinedDate);
        }
        return "";
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
