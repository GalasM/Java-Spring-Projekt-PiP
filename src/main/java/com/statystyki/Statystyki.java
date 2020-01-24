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
    private String strzalyM;
    private String strzalyG;
    private String zolteKartkiM;
    private String zolteKartkiG;
    private String czerwoneKartkiM;
    private String czerwoneKartkiG;
    private String fauleM;
    private String fauleG;
    private String spaloneM;
    private String spaloneG;

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

    public String getStrzalyM() {
        return strzalyM;
    }

    public void setStrzalyM(String strzalyM) {
        this.strzalyM = strzalyM;
    }

    public String getStrzalyG() {
        return strzalyG;
    }

    public void setStrzalyG(String strzalyG) {
        this.strzalyG = strzalyG;
    }

    public String getZolteKartkiM() {
        return zolteKartkiM;
    }

    public void setZolteKartkiM(String zolteKartkiM) {
        this.zolteKartkiM = zolteKartkiM;
    }

    public String getZolteKartkiG() {
        return zolteKartkiG;
    }

    public void setZolteKartkiG(String zolteKartkiG) {
        this.zolteKartkiG = zolteKartkiG;
    }

    public String getCzerwoneKartkiM() {
        return czerwoneKartkiM;
    }

    public void setCzerwoneKartkiM(String czerwoneKartkiM) {
        this.czerwoneKartkiM = czerwoneKartkiM;
    }

    public String getCzerwoneKartkiG() {
        return czerwoneKartkiG;
    }

    public void setCzerwoneKartkiG(String czerwoneKartkiG) {
        this.czerwoneKartkiG = czerwoneKartkiG;
    }

    public String getFauleM() {
        return fauleM;
    }

    public void setFauleM(String fauleM) {
        this.fauleM = fauleM;
    }

    public String getFauleG() {
        return fauleG;
    }

    public void setFauleG(String fauleG) {
        this.fauleG = fauleG;
    }

    public String getSpaloneM() {
        return spaloneM;
    }

    public void setSpaloneM(String spaloneM) {
        this.spaloneM = spaloneM;
    }

    public String getSpaloneG() {
        return spaloneG;
    }

    public void setSpaloneG(String spaloneG) {
        this.spaloneG = spaloneG;
    }

    @Override
    public String toString() {
        return "Statystyki [aid = " + id + ", bramkiM = " + bramkiM + ", bramkiG = " + bramkiG + ", posiadanieM = " + posiadanieM + ", posiadanieG = " + posiadanieG + "]";
    }
}
