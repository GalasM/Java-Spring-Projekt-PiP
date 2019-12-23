package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public RedirectView addEvent(@ModelAttribute("event") Event event, ModelMap model, RedirectAttributes attr) throws ParseException {

        model.addAttribute("title", event.getTitle());
        model.addAttribute("start", event.getStart());
        model.addAttribute("end", event.getEnd());
        model.addAttribute("type", event.getType());

        if(event.getType().equals("match")){
            String id = UUID.randomUUID().toString();
            String idT = UUID.randomUUID().toString();
            event.setId(id);

            TrainingBefore training = new TrainingBefore(idT,id);
            Event eventT = new Event();
            eventT.setId(idT);
            eventT.setTitle("Trening przed "+ event.getTitle());
            eventT.setType("training");
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(event.getStart());
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DATE, -2);
            date1 = c.getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = formatter.format(date1);
            eventT.setStart(date2);
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