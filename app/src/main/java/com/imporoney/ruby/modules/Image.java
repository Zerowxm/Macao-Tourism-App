package com.imporoney.ruby.modules;

import java.io.Serializable;

/**
 * Created by Zero on 2/2/2016.
 */
public class Image implements Serializable {
    String url;

    public Image(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
