package com.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }

    @GetMapping(value = "/matches")
    public String getMatches() {
        String jsonMsg = null;
        try {
            List<Event> ev = Repo.findAllMatches();

            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ev);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }

    @GetMapping("/all1")
    public String getEventForFootballer(@RequestParam(name="id", defaultValue = "2")String id) {
        String jsonMsg = null;
        try {
            List<Event> ev = Repo.eventsForFootballer(id);
            ev.forEach(x ->{
                ExtendedProps type = new ExtendedProps("type",x.getType());
                List<ExtendedProps> list = type.toList();
                x.setExtendedProps(list);
            });
            List<Event> events = new ArrayList<>(ev);

            ObjectMapper mapper = new ObjectMapper();
            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;

    }

    @GetMapping("/dupa")
    public String dupa() {
        return "14";
    }
}