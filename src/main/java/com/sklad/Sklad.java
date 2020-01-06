package com.sklad;

import com.footballer.Footballer;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

public class Sklad {
    private String id;
    private String name;
    private String formation;
    @Max(4)
    private List<Footballer> listN;
    @Max(4)
    private List<Footballer> listP;
    @Max(4)
    private List<Footballer> listO;
    @Max(1)
    private List<Footballer> listBR;
    @Max(7)
    private List<Footballer> listR;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public List<Footballer> getListN() {
        return listN;
    }

    public void setListN(List<Footballer> listN) {
        this.listN = listN;
    }

    public List<Footballer> getListP() {
        return listP;
    }

    public void setListP(List<Footballer> listP) {
        this.listP = listP;
    }

    public List<Footballer> getListO() {
        return listO;
    }

    public void setListO(List<Footballer> listO) {
        this.listO = listO;
    }

    public List<Footballer> getListBR() {
        return listBR;
    }

    public void setListBR(List<Footballer> listBR) {
        this.listBR = listBR;
    }

    public List<Footballer> getListR() {
        return listR;
    }

    public void setListR(List<Footballer> listR) {
        this.listR = listR;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String formation;
        @Max(4)
        private List<Footballer> listN = new ArrayList<>();
        @Max(4)
        private List<Footballer> listP = new ArrayList<>();
        @Max(4)
        private List<Footballer> listO = new ArrayList<>();
        @Max(1)
        private List<Footballer> listBR = new ArrayList<>();
        @Max(7)
        private List<Footballer> listR = new ArrayList<>();

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder formation(String formation){
            this.formation = formation;
            return this;
        }

        public Builder listN(List<Footballer> listN){
            this.listN = listN;
            return this;
        }

        public Builder addN(Footballer f) {
            this.listN.add(f);
            return this;
        }

        public Builder listP(List<Footballer> listP){
            this.listP = listP;
            return this;
        }

        public Builder addP(Footballer f) {
            this.listP.add(f);
            return this;
        }

        public Builder listO(List<Footballer> listO){
            this.listO = listO;
            return this;
        }

        public Builder addO(Footballer f) {
            this.listO.add(f);
            return this;
        }

        public Builder listBR(List<Footballer> listBR){
            this.listBR = listBR;
            return this;
        }

        public Builder addBR(Footballer f) {
            this.listBR.add(f);
            return this;
        }

        public Builder listR(List<Footballer> listR){
            this.listR = listR;
            return this;
        }

        public Builder addR(Footballer f) {
            this.listR.add(f);
            return this;
        }

        public Sklad build() {
            if(name.isEmpty()){
                throw new IllegalStateException("Name cannot be empty");
            }

            Sklad sklad = new Sklad();
            sklad.id = this.id;
            sklad.name = this.name;
            sklad.formation = this.formation;
            sklad.listN = this.listN;
            sklad.listP = this.listP;
            sklad.listO = this.listO;
            sklad.listBR = this.listBR;
            sklad.listR = this.listR;
            return sklad;
        }
    }
}
