package com.imporoney.ruby.modules;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zero on 2/2/2016.
 */

public class Test implements Serializable {
    String title;
    Integer id;
    Image image;

    public Test(List<Thing> things, String title, Integer id, Image image) {
        this.title = title;
        this.id = id;
        this.image = image;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
