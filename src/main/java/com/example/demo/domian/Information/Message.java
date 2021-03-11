package com.example.demo.domian.Information;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private Integer code;
    private String state;
    private Map<String,Object> message=new HashMap<>();

    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }

    public static Message success(){
        Message msg=new Message();
        msg.setCode(200);
        msg.setState("success!!!");
        return msg;
    }
    public static Message success(String info){
        Message msg=new Message();
        msg.setCode(200);
        msg.setState(info);
        return msg;
    }
    public static Message error(){
        Message msg=new Message();
        msg.setCode(400);
        msg.setState("error!!!");
        return msg;

    }
    public static Message error(String info){
        Message msg=new Message();
        msg.setCode(400);
        msg.setState(info);
        return msg;
    }

    public Message add(String key,Object value){
        System.out.println(value.toString());
        this.message.put(key,value);
        return this;
    }

}
