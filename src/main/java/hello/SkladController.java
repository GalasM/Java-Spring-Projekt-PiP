package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SkladController {
    private final FootballerJDBCRepository uRepo;

    @Autowired
    public SkladController(FootballerJDBCRepository uRepo) {
        this.uRepo = uRepo;
    }

    @GetMapping("/sklad")
    public String allFootballersN(Model model) {
        List<Footballer> listN = uRepo.findN();
        List<Footballer> listP = uRepo.findP();
        List<Footballer> listO = uRepo.findO();
        List<Footballer> listBR = uRepo.findBR();
        List<Footballer> listR = uRepo.findR();
        model.addAttribute("allFootballersN", listN);
        model.addAttribute("allFootballersP", listP);
        model.addAttribute("allFootballersO", listO);
        model.addAttribute("allFootballersBR", listBR);
        model.addAttribute("allFootballersR", listR);

        return "sklad";
    }
}