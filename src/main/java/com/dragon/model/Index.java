package com.dragon.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by osetskiy on 24.01.2017.
 */
public  abstract class Index extends TableRow {
    @Id
    private String date;
    private String last;

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Index{" +
                "date='" + date + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
