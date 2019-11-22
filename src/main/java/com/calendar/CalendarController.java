package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class CalendarController {

    private final CalendarJDBCRepository Repo;

    @Autowired
    public CalendarController(CalendarJDBCRepository Repo) {
        this.Repo = Repo;
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @PostMapping("/addEvent")
    public RedirectView addEvent(@ModelAttribute("event") Event event, ModelMap model, RedirectAttributes attr) {

            model.addAttribute("title", event.getTitle());
            model.addAttribute("start", event.getStart());
            model.addAttribute("end", event.getEnd());
            model.addAttribute("type", event.getType());
            String id = UUID.randomUUID().toString();
            event.setId(id);
            Repo.insert(event);
            attr.addFlashAttribute("added","Dodano wydarzenie!");
        return new RedirectView("calendar");
    }

    @PostMapping("/removeEvent")
    public RedirectView removeEvent(@ModelAttribute("event") Event event, ModelMap model, RedirectAttributes attr) {
        Repo.deleteById(event.getId());
        attr.addFlashAttribute("removed","Usunięto wydarzenie!");
        return new RedirectView("calendar");
    }
}
