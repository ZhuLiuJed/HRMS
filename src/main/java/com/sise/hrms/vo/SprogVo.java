package com.sise.hrms.vo;

/**
 * Created by holyfrans on 2017/3/13.
 */
public class SprogVo {
    private Integer id;

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    private Integer deptid;
    private Integer positionid;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
