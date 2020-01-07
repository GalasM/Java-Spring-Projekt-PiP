package com.news;


import javax.persistence.Id;

public class News {
    @Id
    private String id;
    private String tytul;
    private String tresc;


    public String getAid() {
        return id;
    }

    public void setAid(String id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }


    @Override
    public String toString() {
        return "News [aid = " + id + ", tytul = " + tytul + ", tresc = " + tresc + "]";
    }
}
