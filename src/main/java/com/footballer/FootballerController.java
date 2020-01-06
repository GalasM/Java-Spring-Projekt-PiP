package com.footballer;


import com.sklad.SkladJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@Controller
public class FootballerController {

    private final FootballerJDBCRepository uRepo;
    private final SkladJDBCRepository sRepo;

    @Autowired
    public FootballerController(FootballerJDBCRepository uRepo, SkladJDBCRepository sRepo) {
        this.uRepo = uRepo;
        this.sRepo = sRepo;
    }

    @GetMapping("/formFootballer")
    public String greetingForm(Model model) {
        model.addAttribute("footballer", new Footballer());
        return "formFootballer";
    }

    @PostMapping("/addFootballer")
    public RedirectView greetingSubmit(@ModelAttribute ("footballer")Footballer footballer, RedirectAttributes attr, ModelMap model) {
        model.addAttribute("imie", footballer.getImie());
        model.addAttribute("nazwisko", footballer.getNazwisko());
        model.addAttribute("pozycja", footballer.getPozycja());
        model.addAttribute("status", footballer.getStatus());
        String id = UUID.randomUUID().toString();
        footballer.setId(id);
        uRepo.insert(footballer);
        String id2 = UUID.randomUUID().toString();
        sRepo.insertFootballer(id2,null,id,null);
        attr.addFlashAttribute("addedFootballer","Dodano piłkarza!");
        return new RedirectView("footballers");
    }

    @GetMapping("/footballers")
    public String allFootballers(Model model) {
        List<Footballer> list = uRepo.findAll();
        model.addAttribute("allFootballers", list);
        return "footballers";
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam(name="id")String id, RedirectAttributes attr) {
        uRepo.deleteById(id);
        attr.addFlashAttribute("removedFootballer","Usunięto piłkarza!");
        return new RedirectView("footballers");
    }
}


