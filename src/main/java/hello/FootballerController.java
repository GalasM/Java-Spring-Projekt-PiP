package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@Controller
public class FootballerController {

    private final FootballerJDBCRepository uRepo;

    @Autowired
    public FootballerController(FootballerJDBCRepository uRepo) {
        this.uRepo = uRepo;
    }

    @GetMapping("/formFootballer")
    public String greetingForm(Model model) {
        model.addAttribute("footballer", new Footballer());
        return "formFootballer";
    }

    @PostMapping("/addFootballer")
    public RedirectView greetingSubmit(@ModelAttribute ("footballer")Footballer footballer, BindingResult result, ModelMap model) {
        model.addAttribute("imie", footballer.getImie());
        model.addAttribute("nazwisko", footballer.getNazwisko());
        model.addAttribute("pozycja", footballer.getPozycja());
        String id = UUID.randomUUID().toString();
        footballer.setId(id);
        uRepo.insert(footballer);
        return new RedirectView("footballers");
    }

    @GetMapping("/footballers")
    public String allFootballers(Model model) {
        List<Footballer> list = uRepo.findAll();
        model.addAttribute("allFootballers", list);
        return "footballers";
    }
}