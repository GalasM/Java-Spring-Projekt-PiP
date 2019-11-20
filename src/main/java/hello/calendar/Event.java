package hello.calendar;

import java.util.List;

public class Event extends Object {
    private String id;
    private String title;
    private String start;
    private String end;
    private List<ExtendedProps> ExtendedProps;
    private String type;
    private String color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExtendedProps> getExtendedProps() {
        return ExtendedProps;
    }

    public void setExtendedProps(List<ExtendedProps> extenedProps) {
        extenedProps.forEach(n->{
            if(n.name.equals("type"))
                switch(n.value){
                    case "match":
                        setType("match");
                        setColor("green");
                        break;
                    case "training":
                        setType("training");
                        setColor("yellow");
                        break;
                    default:
                        setColor("pink");
                }

        } );
        ExtendedProps = extenedProps;
    }

    public String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }
}
