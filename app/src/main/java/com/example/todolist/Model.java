package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

public class Model {
    String title;
    String detail;
    String Date;
    Integer id;
    public Model() {
        this.title = title;
        this.detail = detail;
       this. Date = Date;
       this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

