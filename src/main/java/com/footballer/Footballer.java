package com.footballer;

import java.io.Serializable;

public class Footballer implements Serializable {
    private String id;
    private String imie;
    private String nazwisko;
    private String pozycja;

    public Footballer() {
        super();
    }

    public Footballer(String id, String imie, String nazwisko, String pozycja, String status) {
        super();
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pozycja = pozycja;
    }


    public Footballer(String imie, String nazwisko, String pozycja, String status) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pozycja = pozycja;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPozycja() {
        return pozycja;
    }

    public void setPozycja(String pozycja) {
        this.pozycja = pozycja;
    }


    @Override
    public String toString() {
        return String.format("Footballer [id = " + id + ", imie = " + imie + ", nazwisko = " + nazwisko + ", pozycja = " + pozycja + "]");
    }
}
