package com.am.carly.data.model;

public class City {
    private int id;
    private String name;
    private String imgPath;

        public City(int id, String name, String imgPath) {
        this.id = id;
        this.name = name;
        this.imgPath = imgPath;
    }public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
