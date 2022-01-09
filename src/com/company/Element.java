package com.company;

public class Element {
    private String id;

    public Element(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int compareTo(Element element) {
        int res = 0;
        if (this.id.compareTo(element.getId()) < 0) {
            res =- 1;
        }
        if (this.id.compareTo(element.getId()) > 0) {
            res = 1;
        }
        return res;
    }
}
