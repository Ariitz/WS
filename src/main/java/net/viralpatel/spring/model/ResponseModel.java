package net.viralpatel.spring.model;

public class ResponseModel {

    public  Object response;
    public  Integer code;

    public ResponseModel(Object response, Integer code) {
        this.response = response;
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
