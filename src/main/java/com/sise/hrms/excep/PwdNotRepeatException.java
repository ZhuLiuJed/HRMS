package com.sise.hrms.excep;

/**
 * Created by Administrator on 2017/3/9.
 */
public class PwdNotRepeatException extends Exception {
    public PwdNotRepeatException(String msg){
        super(msg);
    }
}
