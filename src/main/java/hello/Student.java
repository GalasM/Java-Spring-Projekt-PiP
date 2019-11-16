package hello;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String passportNumber;
    public Student() {
        super();
    }
    public Student(String id, String name, String passportNumber) {
        super();
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
    }
    public Student(String name, String passportNumber) {
        super();
        this.name = name;
        this.passportNumber = passportNumber;
    }
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
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    @Override
    public String toString() {
        return String.format("Student [id=%s, name=%s, passportNumber=%s]", id, name, passportNumber);
    }
}
