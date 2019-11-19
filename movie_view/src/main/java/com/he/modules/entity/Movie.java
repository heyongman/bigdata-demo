package com.he.modules.entity;

public class Movie {
    private int id;
    /**
     * 名字
     */
    private String name;
    /**
     * 票房
     */
    private double bo;

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

    public double getBo() {
        return bo;
    }

    public void setBo(double bo) {
        this.bo = bo;
    }
}
