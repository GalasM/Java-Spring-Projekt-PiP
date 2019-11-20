package hello.calendar;

import java.util.Arrays;
import java.util.List;

public class ExtendedProps {
    String name;
    String value;

    public void setName(String name){
        this.name=name;
    }

    public void setValue(String value){
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ExtendedProps(String name,String value){
        this.name=name;
        this.value=value;
    }

    public List<ExtendedProps> toList() {
        List<ExtendedProps> list =Arrays.asList(this);
        return list;
    }
}


