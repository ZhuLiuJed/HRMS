package com.sise.hrms.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by holyfrans on 2017/3/13.
 */
public class TimeVo {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String id) {
        this.value = id;
    }
    public Date getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(this.value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public Map<String,Date> getTimeMap(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(this.value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String,Date> map = new HashMap<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);
        map.put("minDate",calendar.getTime());
        calendar.setTime(date);
        calendar.add(calendar.DATE,1);
        map.put("maxDate",calendar.getTime());
        return map;

    }
//    public  static  void  main(String[] ada){
//        TimeVo timeVo = new TimeVo();
//        timeVo.setValue("2017-3-11");
//        System.out.println("timeVo.getTime().toString() = " +timeVo.getTime());
//        System.out.printf(timeVo.getTimeMap().get("minDate").toString());
//        System.out.printf(timeVo.getTimeMap().get("maxDate").toString());
//    }
}
