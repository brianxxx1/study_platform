package com.wenshuo.commonutils;

import lombok.Data;

import java.util.HashMap;

@Data
public class R {
    private boolean success;
    private Integer code;
    private String message;
    private HashMap<String,Object> data = new HashMap<>();

    private R(){
    }

    public static R ok(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getNum());
        r.setMessage("SUCCESS");
        r.setSuccess(true);
        return r;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R success(boolean success){
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object obj){
        this.data.put(key,obj);
        return this;
    }

    public R data(HashMap<String,Object> map){
        this.setData(map);
        return this;
    }


    public static R error(){
        R r = new R();
        r.setCode(ResultCode.FAIL.getNum());
        r.setMessage("FAIL");
        r.setSuccess(false);
        return r;
    }
}
