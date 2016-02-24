package com.imporoney.ruby.modules;

/**
 * Created by Zero on 2/2/2016.
 */
public class ThingDetail {
    Image image;
    String description;

    @Override
    public String toString() {
        return "ThingDetail{" +
                "image=" + image +
                ", description='" + description + '\'' +
                '}';
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
