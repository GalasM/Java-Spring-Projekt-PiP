package com.calendar;

import com.footballer.Footballer;
import com.footballer.FootballerJDBCRepository;
import com.sklad.Sklad;
import com.sklad.SkladJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@Controller
public class CalendarController {

    private final CalendarJDBCRepository Repo;
    private final TrainingJDBCRepository RepoTraining;
    private final SkladJDBCRepository sRepo;
    private final FootballerJDBCRepository fRepo;

    @Autowired
    public CalendarController(CalendarJDBCRepository Repo, TrainingJDBCRepository repoTraining, SkladJDBCRepository sRepo, FootballerJDBCRepository fRepo) {
        this.Repo = Repo;
        this.RepoTraining = repoTraining;
        this.sRepo = sRepo;
        this.fRepo = fRepo;
    }

    @GetMapping("/calendar")
    public String calendar(@RequestParam(name = "id", defaultValue = "1") String footballer, Model model) {
            List<Sklad> allTeams = sRepo.findAllTeams();
            List<Footballer> allFootballers = fRepo.findAll();
            Footballer currentFootballer = fRepo.findById(footballer);
            model.addAttribute("allTeams", allTeams);
            model.addAttribute("allFootballers", allFootballers);
            model.addAttribute("currentFootballer", currentFootballer);
        return "calendar";
    }


    @PostMapping("/addEvent")
    public RedirectView addEvent(@ModelAttribute("event") Event event, RedirectAttributes attr) {
        if(event.getType().equals("match")) {
            try {
                List<Footballer> x = sRepo.findById(event.getSklad()).getAllFootballers();

            if (x.size() < 11) {
                attr.addFlashAttribute("info", "Ten skład nie ma 11 zawodników");
                return new RedirectView("sklad?id="+event.getSklad());
            } else {
                List<Footballer> busy = new ArrayList<>();
                x.forEach(f -> {
                    if (fRepo.hasEvent(f.getId(), event.getStart())) {
                        busy.add(f);
                    }
                });
                if (!busy.isEmpty()) {
                    String b = busy.stream()
                            .map(footballer -> footballer.getNazwisko() + " " + footballer.getImie())
                            .collect(Collectors.joining(", "));
                    attr.addFlashAttribute("info", "Następujący zawodnicy maja juz tego dnia mecz: " + b);
                    return new RedirectView("sklad?id=" + event.getSklad());
                }
                String id = UUID.randomUUID().toString();
                String idT = UUID.randomUUID().toString();
                event.setId(id);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date = formatter.parse(event.getStart());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MINUTE, 90);
                date = c.getTime();
                String dateS = formatter.format(date);
                event.setEnd(dateS);

                TrainingBefore training = new TrainingBefore(idT, id);
                Event eventT = new Event();
                eventT.setId(idT);
                eventT.setTitle("Trening przed " + event.getTitle());
                eventT.setType("training");
                eventT.setStart(event.getTrainingDate());
                eventT.setSklad("0");
                Repo.insert(event);
                RepoTraining.insert(training);
                Repo.insert(eventT);

                attr.addFlashAttribute("added", "Dodano mecz oraz trening!");
                return new RedirectView("calendar");
            }
            }catch (EmptyResultDataAccessException e){
                attr.addFlashAttribute("info", "Ten skład nie ma 11 zawodników");
                return new RedirectView("sklad?id="+event.getSklad());
            }
            catch (ParseException e){
                e.printStackTrace();
                return new RedirectView("calendar");
            }
        }

        else{
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
            if(RepoTraining.exist(event.getId())) {
                TrainingBefore x = RepoTraining.getTrainingById(event.getId());
                Repo.deleteById(x.getId());
                Repo.deleteById(event.getId());
                attr.addFlashAttribute("removed", "Usunięto mecz oraz zaplanowany trening!");
            }
            else{
                Repo.deleteById(event.getId());
                attr.addFlashAttribute("removed", "Usunięto mecz!");
            }
            return new RedirectView("calendar");
        }
        else {
            Repo.deleteById(event.getId());
            attr.addFlashAttribute("removed", "Usunięto wydarzenie!");
            return new RedirectView("calendar");
        }
    }
}