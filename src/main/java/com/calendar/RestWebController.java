package com.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/event")
public class RestWebController {

    private final CalendarJDBCRepository Repo;

    @Autowired
    public RestWebController(CalendarJDBCRepository Repo) {
        this.Repo = Repo;
    }

    @GetMapping(value = "/all")
    public String getEvents() {
        String jsonMsg = null;
        try {
            List<Event> ev = Repo.findAll();
            ev.forEach(x ->{
                ExtendedProps type = new ExtendedProps("type",x.getType());
                List<ExtendedProps> list = type.toList();
                x.setExtendedProps(list);
            });
            List<Event> events = new ArrayList<>(ev);
            Event event = new Event();
            event.setTitle("first event");
            event.setStart("2019-11-01");
            ExtendedProps type = new ExtendedProps("type","training");
            List<ExtendedProps> list = type.toList();
            event.setExtendedProps(list);

            events.add(event);

            event = new Event();
            event.setTitle("second event");
            event.setStart("2019-11-11");
            event.setEnd("2019-11-13");
            type = new ExtendedProps("type","match");
            list = type.toList();
            event.setExtendedProps(list);

            events.add(event);

            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }
}