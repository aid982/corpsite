package com.dragon.model.Excel;

/**
 * Created by osetskiy on 23.01.2017.
 */
public class Field {
    String name;
    String type;
    Integer position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Field(String name, String type, Integer position) {
        this.name = name;
        this.type = type;
        this.position = position;
    }
}
