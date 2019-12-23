package com.calendar;

public class TrainingBefore {
    private String id;
    private String idMatch;

    TrainingBefore(String id, String idMatch){
        this.id = id;
        this.idMatch = idMatch;
    }
    TrainingBefore(){

    }

    public void setId(String id) {
        this.id = id;
    }

    void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getId() {
        return id;
    }

    String getIdMatch() {
        return idMatch;
    }
}
