package com.statystyki;


import javax.persistence.Id;

public class Statystyki {
    @Id
    private String id;
    private String przeciwnik;
    private String bramkiM;
    private String bramkiG;
    private String posiadanieM;
    private String posiadanieG;

    public String getAid() {
        return id;
    }

    public void setAid(String id) {
        this.id = id;
    }

    public String getPrzeciwnik() {
        return przeciwnik;
    }

    public void setPrzeciwnik(String przeciwnik) {
        this.przeciwnik = przeciwnik;
    }

    public String getBramkiM() {
        return bramkiM;
    }

    public void setBramkiM(String bramkiM) {
        this.bramkiM = bramkiM;
    }

    public String getBramkiG() {
        return bramkiG;
    }

    public void setBramkiG(String bramkiG) {
        this.bramkiG = bramkiG;
    }

    public String getPosiadanieM() {
        return posiadanieM;
    }

    public void setPosiadanieM(String posiadanieM) {
        this.posiadanieM = posiadanieM;
    }

    public String getPosiadanieG() {
        return posiadanieG;
    }

    public void setPosiadanieG(String posiadanieG) {
        this.posiadanieG = posiadanieG;
    }

    @Override
    public String toString() {
        return "Statystyki [aid = " + id + ", bramkiM = " + bramkiM + ", bramkiG = " + bramkiG + ", posiadanieM = " + posiadanieM + ", posiadanieG = " + posiadanieG + "]";
    }
}
