package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class CalendarController {

    private final CalendarJDBCRepository Repo;
    private final TrainingJDBCRepository RepoTraining;

    @Autowired
    public CalendarController(CalendarJDBCRepository Repo, TrainingJDBCRepository repoTraining) {
        this.Repo = Repo;
        RepoTraining = repoTraining;
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @PostMapping("/addEvent")
    public RedirectView addEvent(@ModelAttribute("event") Event event, RedirectAttributes attr) {
        if(event.getType().equals("match")){
            String id = UUID.randomUUID().toString();
            String idT = UUID.randomUUID().toString();
            event.setId(id);
            TrainingBefore training = new TrainingBefore(idT,id);
            Event eventT = new Event();
            eventT.setId(idT);
            eventT.setTitle("Trening przed "+ event.getTitle());
            eventT.setType("training");
            eventT.setStart(event.getTrainingDate());
            Repo.insert(event);
            RepoTraining.insert(training);
            Repo.insert(eventT);

            attr.addFlashAttribute("added","Dodano mecz oraz trening!");
            return new RedirectView("calendar");
        }
        else {
        String id = UUID.randomUUID().toString();
        event.setId(id);
        Repo.insert(event);
            attr.addFlashAttribute("added", "Dodano wydarzenie!");
            return new RedirectView("calendar");
        }
    }

    @PostMapping("/removeEvent")
    public RedirectView removeEvent(@ModelAttribute("event") Event event, RedirectAttributes attr) {
        Event event1 = Repo.findById(event.getId());
        if(event1.getType().equals("match")){
        TrainingBefore x = RepoTraining.getTrainingById(event.getId());
        Repo.deleteById(x.getId());
        Repo.deleteById(event.getId());
        attr.addFlashAttribute("removed","Usunięto mecz oraz zaplanowany trening!");
        return new RedirectView("calendar");
        }
        else {
            Repo.deleteById(event.getId());
            attr.addFlashAttribute("removed", "Usunięto wydarzenie!");
            return new RedirectView("calendar");
        }
    }
}