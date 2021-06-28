package com.pex.springbatch.model;

public class CustomPojo {

    private String url;

    private String color1;

    private String color2;

    private String color3;

    public CustomPojo() {

    }

    public CustomPojo(String[] line) {
        this.setUrl(line[0]);
        this.setColor1(line[1]);
        this.setColor2(line[2]);
        this.setColor3(line[3]);
    }

    @Override
    public String toString() {
        return "CustomPojo{" +
                "url='" + url + '\'' +
                ", color1='" + color1 + '\'' +
                ", color2='" + color2 + '\'' +
                ", color3='" + color3 + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }
}
