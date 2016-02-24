package com.imporoney.ruby.modules;

import java.util.List;

/**
 * Created by Zero on 2/2/2016.
 */
public class ItemDetail {
    Integer id;
    String address;
    String image_explain;
    String name;
    String type_detail;
    Image headlogo;
    String phone;
    List<ThingDetail> thing_details;
    List<Coupon>coupons;

    @Override
    public String toString() {
        return "ItemDetail{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", image_explain='" + image_explain + '\'' +
                ", name='" + name + '\'' +
                ", type_detail='" + type_detail + '\'' +
                ", headlogo=" + headlogo +
                ", phone='" + phone + '\'' +
                ", thing_details=" + thing_details +
                ", coupons=" + coupons +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_explain() {
        return image_explain;
    }

    public void setImage_explain(String image_explain) {
        this.image_explain = image_explain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_detail() {
        return type_detail;
    }

    public void setType_detail(String type_detail) {
        this.type_detail = type_detail;
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

    public List<ThingDetail> getThing_details() {
        return thing_details;
    }

    public void setThing_details(List<ThingDetail> thing_details) {
        this.thing_details = thing_details;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
