package com.imporoney.ruby.modules;

import java.io.Serializable;

/**
 * Created by Zero on 1/2/2016.
 */
public class Thing implements Serializable {
    Integer id;
    String name;
    String address;
    Image headlogo;
    String phone;

    public Thing(Integer id, String name, String address, Image headlogo, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.headlogo = headlogo;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Image getHeadlogo() {
        return headlogo;
    }

    public void setHeadlogo(Image headlogo) {
        this.headlogo = headlogo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", headlogo=" + headlogo +
                ", phone='" + phone + '\'' +
                '}';
    }
}
