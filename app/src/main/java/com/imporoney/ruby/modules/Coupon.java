package com.imporoney.ruby.modules;

/**
 * Created by Zero on 2/2/2016.
 */
public class Coupon {
    Integer id;
    String code;

    public Coupon(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
