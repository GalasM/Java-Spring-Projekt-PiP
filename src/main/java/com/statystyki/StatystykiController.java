package com.statystyki;

import com.calendar.CalendarJDBCRepository;
import com.calendar.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@Controller
public class StatystykiController {

    private final StatystykiJDBCRespository sRepo;
    private final CalendarJDBCRepository cRepo;

    public StatystykiController(StatystykiJDBCRespository sRepo, CalendarJDBCRepository cRepo) {
        this.sRepo = sRepo;
        this.cRepo = cRepo;
    }


    @PostMapping("/addMatch")
    public RedirectView greetingSubmit(@ModelAttribute("footballer") Statystyki stats, RedirectAttributes attr, ModelMap model) {
        model.addAttribute("przeciwnik", stats.getPrzeciwnik());
        model.addAttribute("bramkiM", stats.getBramkiM());
        model.addAttribute("bramkiG", stats.getBramkiG());
        model.addAttribute("posiadanieM", stats.getPosiadanieM());
        model.addAttribute("posiadanieG", stats.getPosiadanieG());
        String id = UUID.randomUUID().toString();
        stats.setAid(id);

        sRepo.insert(stats);
        attr.addFlashAttribute("addedStats","Dodano Statystyki!");
        return new RedirectView("statystyki");
    }

    @GetMapping("/statystyki")
    public String index(Model model) {
        List<Statystyki> list = sRepo.findAll();
        List<Event> oldList = cRepo.findOldMatch();
        model.addAttribute("allStats", list);
        model.addAttribute("oldMatches", oldList);
        return "statystyki";
    }
}



