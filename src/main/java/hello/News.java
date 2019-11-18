package hello;


import javax.persistence.Id;

public class News {
    @Id
    private int aid;
    private String tytul;
    private String tresc;


    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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
        return "News [aid = " + aid + ", tytul = " + tytul + ", tresc = " + tresc + "]";
    }
}
