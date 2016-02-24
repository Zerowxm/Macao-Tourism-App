package com.imporoney.ruby.modules;

/**
 * Created by Zero on 1/2/2016.
 */
public class Header {
    Integer Id;
    String Image;
    Integer kind;
    String Title;

    public Header(Integer id, String image, Integer kind, String title) {
        Id = id;
        Image = image;
        this.kind = kind;
        Title = title;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
